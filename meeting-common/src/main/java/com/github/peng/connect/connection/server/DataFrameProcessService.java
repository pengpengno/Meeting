package com.github.peng.connect.connection.server;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.peng.connect.connection.ConnectionConstants;
import com.github.peng.connect.connection.server.context.ConnectionGroupRoom;
import com.github.peng.connect.connection.server.context.IConnectContextAction;
import com.github.peng.connect.connection.server.context.ReactorConnection;
import com.github.peng.connect.model.proto.ProtocolType;
import com.github.peng.connect.utils.ByteBufUtils;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import reactor.netty.Connection;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Data frame save
 * @author pengpeng
 * @description 根据网络传输过来的 Message 处理指定的业务
 * @date 2023/3/16
 */
@Slf4j
public class DataFrameProcessService implements ApplicationContextAware ,ByteBufProcess {

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
//        con.inbound().receive().asString().
        ConnectionGroupRoom orSupplier = connectContext.getOrSupplier(roomKey, e -> () -> {

            ReactorConnection reactorConnection = ReactorConnection.builder()
                    .group(roomKey)
                    .connection(con)
                    .channel(con.channel())
                    .build();

            ConnectionGroupRoom connectionGroupRoom = ConnectionGroupRoom.builder()
                    .roomKey(roomKey)
                    .connection(reactorConnection)
                    .build();
            return connectionGroupRoom;

        });

        orSupplier.offerByteBuf(byteBuf);
//
//        byte[] bytes = ByteBufUtils.readByteBuf2Array(byteBuf);


    }



    private enum SingleInstance{
        INSTANCE;
        private final DataFrameProcessService instance;
        SingleInstance(){
            instance = new DataFrameProcessService();
        }
        private DataFrameProcessService getInstance(){
            return instance;
        }
    }
    public static DataFrameProcessService getInstance(){
        return SingleInstance.INSTANCE.getInstance();
    }
    private DataFrameProcessService(){}




}
