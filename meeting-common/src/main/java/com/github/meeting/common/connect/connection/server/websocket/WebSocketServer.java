package com.github.meeting.common.connect.connection.server.websocket;

import com.github.meeting.common.connect.connection.ConnectionConstants;
import com.github.meeting.common.connect.connection.server.ReactiveServer;
import com.github.meeting.common.connect.connection.server.tcp.ReactorTcpServer;
import com.github.meeting.common.connect.handler.server.RtspServerHandler;
import com.github.meeting.common.connect.spi.ReactiveHandlerSPI;
import com.google.inject.Singleton;
import io.netty.handler.codec.rtsp.RtspDecoder;
import io.netty.handler.codec.rtsp.RtspEncoder;
import io.netty.handler.logging.LogLevel;
import lombok.extern.slf4j.Slf4j;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;
import reactor.netty.tcp.TcpServer;

import java.net.InetSocketAddress;


@Slf4j
@Singleton
public class WebSocketServer implements ReactiveServer  {


    @Override
    public ReactiveServer getInstance(ReactorTcpServer tcpServer) {
        return ReactiveServer.super.getInstance(tcpServer);
    }



    @Override
    public ReactiveServer init(InetSocketAddress address) {


        DisposableServer server = HttpServer.create()
                .port(address.getPort())
                .route(routes -> routes.ws("/websocket", (inbound, outbound) -> {
                    // 当有新的 WebSocket 连接时，打印日志
                    System.out.println("New WebSocket connection established");

                    // 处理接收到的消息，并原样返回
                    return outbound.sendString(
                            inbound.receive()
                                    .asString()
                                    .doOnNext(message -> System.out.println("Received message: " + message))
                    );
                }))
                .bindNow();

        // 等待服务器关闭
        server.onDispose()
                .block();

        return null;
    }

    @Override
    public ReactiveServer start() {


        return null;
    }

    @Override
    public void stop() {

    }
}
