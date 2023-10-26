package com.github.peng.server;

import com.github.peng.connect.connection.server.ReactiveServer;
import com.github.peng.connect.connection.server.tcp.ReactorTcpServer;
import lombok.SneakyThrows;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

import java.net.InetSocketAddress;

public class Application {

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("Hello World!");

        var instance = ReactorTcpServer.getInstance();

        instance
                .init(new InetSocketAddress("localhost", 8080))
                .start();
//
//        DisposableServer server =
//                HttpServer.create()
//
//                        .route(routes ->
//                                routes.get("/hello",
//                                                (request, response) -> response.sendString(Mono.just("Hello World!")))
//                                        .post("/echo",
//                                                (request, response) -> response.send(request.receive().retain()))
//                                        .get("/path/{param}",
//                                                (request, response) -> response.sendString(Mono.just(request.param("param"))))
//                                        .ws("/ws",
//                                                (wsInbound, wsOutbound) -> wsOutbound.send(wsInbound.receive().retain())))
//                        .bindNow();
//
//        server.onDispose()
//                .block();
    }

}
