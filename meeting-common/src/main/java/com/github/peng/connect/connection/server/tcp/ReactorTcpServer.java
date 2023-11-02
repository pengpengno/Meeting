package com.github.peng.connect.connection.server.tcp;

import com.github.peng.connect.connection.server.ReactiveServer;
import com.github.peng.connect.handler.proto.ProtoBufMessageLiteScanner;
import com.github.peng.connect.model.proto.Account;
import com.github.peng.connect.spi.ReactiveHandlerSPI;
import com.google.inject.Singleton;
import com.google.protobuf.MessageLiteOrBuilder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.rtsp.RtspDecoder;
import io.netty.handler.codec.rtsp.RtspEncoder;
import io.netty.handler.logging.LogLevel;
import lombok.extern.slf4j.Slf4j;
import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpServer;

import java.net.InetSocketAddress;

/**
 * 响应式 tcp 链接
 * @author pengpeng
 * @description
 * @date 2023/3/3
 */
@Slf4j
@Singleton
public class ReactorTcpServer implements ReactiveServer {


    private InetSocketAddress address;
    private enum SingleInstance{
        INSTANCE;
        private final ReactiveServer instance;
        SingleInstance(){
            instance = new ReactorTcpServer();
        }
        private ReactiveServer getInstance(){
            return instance;
        }
    }
    public static ReactiveServer getInstance(){
        return SingleInstance.INSTANCE.getInstance();
    }


    private TcpServer server;

    private DisposableServer disposableServer;

    private ReactorTcpServer(){
    }

    @Override
    public void stop() {
        disposableServer.disposeNow();
    }


    public ReactiveServer init(InetSocketAddress address){
        this.address = address;
        server = TcpServer
                .create()
                .wiretap("tcp-server", LogLevel.INFO)
                .port(address.getPort())
                .doOnConnection(connection -> {
//                    log.debug("connection has been established ");

//                    connection.addHandlerLast(new ProtobufEncoder());
//
//                    ProtoBufMessageLiteScanner.protobufDecoders()
//                                    .forEach(connection::addHandlerLast);
//                    connection
//                            .addHandlerLast(new RtspEncoder())
//                            .addHandlerLast(new RtspDecoder())
//                            ;
                })
                .handle(ReactiveHandlerSPI.wiredSpiHandler().handler())
        ;

        log.info("startup netty  on port {}",address.getPort());

        return this;
    }


    public ReactiveServer start(){
        log.info("start netty server on port {}",address.getPort());
        disposableServer = server.bindNow();
        disposableServer.onDispose().block();
        return this;
    }



}
