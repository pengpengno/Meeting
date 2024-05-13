package com.github.meeting.common.connect.connection.server.context;

//import com.github.benmanes.caffeine.cache.Cache;
//import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.meeting.common.util.ValidatorUtil;
import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * connection 容器
 * @author pengpeng
 * @description
 * @date 2023/3/6
 */
@Slf4j
public class IConnectContext implements IConnectContextAction {


    private final ConcurrentHashMap<String, IConnection> connectionCache =  new ConcurrentHashMap<String,IConnection>();
//
//            Caffeine.newBuilder()
//            .maximumSize(1000)
//            .build();


    /***
     * connection group used to route info to group
     */
    private final ConcurrentHashMap<String, ConnectionGroupRoom> connectionGroup =
            new ConcurrentHashMap<String,ConnectionGroupRoom>();

//            Caffeine.newBuilder()
//            .maximumSize(1000)
//            .build();



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
        return connectionCache.get(account);

    }

    @Override
    public ConnectionGroupRoom applyConnectionGroup(String roomKey) {
        return connectionGroup.get(roomKey);
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
    public ConnectionGroupRoom getOrSupplier(String roomKey, Function<String, Supplier<ConnectionGroupRoom>> connectionFactor) {
        return connectionGroup.computeIfAbsent(roomKey, (key) -> connectionFactor.apply(key).get());
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
