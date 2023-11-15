package com.github.peng.connect.connection.server.context;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.peng.connect.connection.ConnectionConstants;
import com.github.peng.util.ValidatorUtil;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import lombok.extern.slf4j.Slf4j;
import reactor.netty.Connection;

/**
 * connection 容器
 * @author pengpeng
 * @description
 * @date 2023/3/6
 */
@Slf4j
public class IConnectContext implements IConnectContextAction {


    private final Cache<String, IConnection> connectionCache = Caffeine.newBuilder()
            .maximumSize(1000)
            .build();


    /***
     * connection group used to route info to group
     */
    private final Cache<String, ConnectionGroupRoom> connectionGroup = Caffeine.newBuilder()
            .maximumSize(1000)
            .build();



    private enum SingleInstance{
        INSTANCE;
        private final IConnectContext instance;
        SingleInstance(){
            instance = new IConnectContext();
        }
        private IConnectContext getInstance(){
            return instance;
        }
    }
    public static IConnectContextAction getInstance(){
        return SingleInstance.INSTANCE.getInstance();
    }



    @Override
    public IConnection applyConnection(String account) {
        return connectionCache.getIfPresent(account);

    }

    @Override
    public ConnectionGroupRoom applyConnectionGroup(String roomKey) {
        return connectionGroup.getIfPresent(roomKey);
    }

    @Override
    public void putConnection(IConnection connection) {
        ValidatorUtil.validateThrows(connection, IConnection.Create.class);
        String account = connection.accountInfo().getAccount();
        connectionCache.put(account,connection);
    }

    @Override
    public void closeAndRmConnection(String account) {
        IConnection connection = applyConnection(account);
        if (connection != null){
            connection.close();
        }
    }

    @Override
    public void addToGroup(Connection connection) {

         connection.channel().attr(ConnectionConstants.ROOM_KEY).set("group");

        String roomKey = connection.channel().attr(ConnectionConstants.ROOM_KEY).get();
        log.debug("add room key {} ",roomKey);
        ReactorConnection build = ReactorConnection.builder()
                .group(roomKey)
                .connection(connection)
                .channel(connection.channel())
                .connection(connection)
                .build();

        ConnectionGroupRoom data = connectionGroup.get(roomKey, (key) ->{
            ConnectionGroupRoom  connectionGroupRoom = new ConnectionGroupRoom();
                connectionGroupRoom.setConnection(build);
                connectionGroupRoom.setByteFlux(connection.inbound().receive().asByteArray());
                connectionGroupRoom.setRoomKey(key);
                connectionGroupRoom.replayCache();
            return connectionGroupRoom;
        });
    }


    private byte[] getData (ByteBuf byteBuf){
        byte[] bytes = null;
        if (byteBuf.hasArray()) {  //  jvm  heap byteBuf 处理

            bytes = byteBuf.array();

        } else {  //  memory  byteBuf 处理
            int length = byteBuf.readableBytes();
            bytes = new byte[length];

            byteBuf.getBytes(byteBuf.readerIndex(),bytes);

        }
        return bytes;

    }
    //    @Override
//    public void close(Connection connection) {
//        Account.AccountInfo accountInfo = connection.channel().attr(ConnectionConstants.BING_ACCOUNT_KEY).get();
//        if (accountInfo == null){
//            log.info("Account is null , remove IConnection is unnecessary!");
//            return;
//        }
//        closeAndRmConnection(accountInfo.getAccount());
//    }


}
