package com.github.peng.server;

import com.github.peng.connect.connection.server.tcp.ReactorTcpServer;
import lombok.SneakyThrows;
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
