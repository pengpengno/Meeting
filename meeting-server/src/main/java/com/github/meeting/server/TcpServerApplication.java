package com.github.meeting.server;

import com.github.meeting.common.connect.connection.server.tcp.ReactorTcpServer;
import lombok.SneakyThrows;
import java.net.InetSocketAddress;

public class TcpServerApplication {

    @SneakyThrows
    public static void main(String[] args) {

        var instance = ReactorTcpServer.getInstance();
        instance
            .init(new InetSocketAddress("localhost", 8080))
            .start();
    }

}
