package com.github.peng.server;

import com.github.peng.connect.connection.server.ReactiveServer;
import com.github.peng.connect.connection.server.tcp.ReactorTcpServer;
import io.netty.handler.codec.rtsp.RtspMethods;
import io.netty.handler.codec.rtsp.RtspResponseStatuses;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

import java.net.InetSocketAddress;

public class Application {

    @SneakyThrows
    public static void main(String[] args) {

        var instance = ReactorTcpServer.getInstance();
        instance
            .init(new InetSocketAddress("localhost", 8080))
            .start();
    }

}
