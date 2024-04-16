package com.github.meeting.server;

import com.github.meeting.common.connect.connection.server.websocket.WebSocketServer;
import com.github.meeting.common.connect.module.GuiceModuleInjector;

import java.net.InetSocketAddress;

public class WebSocketServerApplication {


    public static void main(String[] args) {
        var instance = GuiceModuleInjector
                .getInstance(WebSocketServer.class);
        instance.init(new InetSocketAddress(8081));
//        WebSocketServer.init(new InetSocketAddress(8080)).start();
    }
}
