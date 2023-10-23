package com.github.peng.connect.connection.server.tcp;

import com.github.peng.connect.connection.server.ReactiveServer;
import com.github.peng.connect.spi.ReactiveHandlerSPI;
import com.google.inject.Singleton;
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


    public  void init(InetSocketAddress address){
        this.address = address;
        server = TcpServer
                .create()
                .wiretap("tcp-server", LogLevel.INFO)
//                .host(address.getHostName())
                .port(address.getPort())
                .handle(ReactiveHandlerSPI.wiredSpiHandler().handler())
        ;
        log.info("startup netty  on port {}",address.getPort());

    }


    public void start(){
        disposableServer = server.bindNow();
        disposableServer.onDispose().block();
    }



}
