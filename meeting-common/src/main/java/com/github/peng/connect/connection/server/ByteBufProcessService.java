package com.github.peng.connect.connection.server;

import cn.hutool.core.collection.CollectionUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.peng.connect.connection.ConnectionConstants;
import com.github.peng.connect.connection.server.context.ConnectionGroupRoom;
import com.github.peng.connect.connection.server.context.IConnectContextAction;
import com.github.peng.connect.connection.server.context.ReactorConnection;
import com.github.peng.connect.handler.MessageParser;
import com.google.common.cache.CacheBuilder;
import com.google.protobuf.Message;
import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * message process
 * @author pengpeng
 * @description 根据网络传输过来的 Message 处理指定的业务
 * @date 2023/3/16
 */
@Slf4j
public class ByteBufProcessService implements ApplicationContextAware ,ByteBufProcess {

    public Map<Class<? extends Message> , ProtoBufProcess> processMap;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        Map<String, ProtoBufProcess> protoBufProcessMap = applicationContext.getBeansOfType(ProtoBufProcess.class);

        if (CollectionUtil.isNotEmpty(protoBufProcessMap)){
            processMap =  protoBufProcessMap
                        .values()
                        .stream()
                        .filter(e->e.type()!=null)
                        .collect(Collectors.toMap(e -> e.type()
                                .getMessageClass(), e ->e, (e1, e2) -> e1));
        }

    }


    private final Cache<String, Connection> connectionCache = Caffeine.newBuilder()
            .build();

    @Override
    public void process(Connection con, ByteBuf byteBuf)  {
        String roomKey = con.channel().attr(ConnectionConstants.ROOM_KEY).get();

        IConnectContextAction connectContext = ServerToolkit.contextAction();

//        ConnectionGroupRoom orSupplier = connectContext.getOrSupplier(roomKey, e -> () -> {
//
//            ReactorConnection reactorConnection = ReactorConnection.builder()
//                    .group(roomKey)
//                    .connection(con)
//                    .channel(con.channel())
//                    .build();
//
//            ConnectionGroupRoom connectionGroupRoom = ConnectionGroupRoom.builder()
//                    .roomKey(roomKey)
//                    .connection(reactorConnection)
//                    .build();
//            return connectionGroupRoom;
//        });
//
//        orSupplier.offerByteBuf(byteBuf);
    }


    private enum SingleInstance{
        INSTANCE;
        private final ByteBufProcessService instance;
        SingleInstance(){
            instance = new ByteBufProcessService();
        }
        private ByteBufProcessService getInstance(){
            return instance;
        }
    }
    public static ByteBufProcessService getInstance(){
        return SingleInstance.INSTANCE.getInstance();
    }
    private ByteBufProcessService(){}




}
