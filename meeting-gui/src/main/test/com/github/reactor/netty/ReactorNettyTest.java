package com.github.reactor.netty;

import io.netty.handler.logging.LogLevel;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;
import reactor.netty.Connection;
import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpClient;
import reactor.netty.tcp.TcpServer;
import reactor.netty.tcp.TcpSslContextSpec;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 异步线程处理
 * @author pengpeng
 * @date 2023/1/6
 */
@Slf4j
public class ReactorNettyTest {


    public  static String  HOST = "127.0.0.1";
    public  static Integer  PORT = 9087;
    public static String TCPLogger = "TCPLogger";
    @Test
    public void createServer(){
        DisposableServer server =
            TcpServer.create()
                    .port(PORT)
                    .wiretap("SLF4J",LogLevel.INFO)
                    .handle((inbound, outbound) ->{
                        inbound.receive().subscribe(k-> {
                            log.info("接受到了数据 {}",k.toString(Charset.defaultCharset()));
                        });
                        return outbound.sendString(Mono.just("hello"));
                            })
                    .doOnUnbound(disposableServer -> log.info("端口 {}",disposableServer.address()))
                    .bindNow()
                    ;
//        server.dispose();
        server.onDispose()
                .block();
        log.info("sss");
    }

    @Test
    public  void localClient() {
        TcpClient client =
                TcpClient.create()
                        .port(PORT)
//                        .wiretap(WIRETAP)
                ;

            TcpSslContextSpec tcpSslContextSpec =
                    TcpSslContextSpec.forClient()
                            .configure(builder -> builder.trustManager(InsecureTrustManagerFactory.INSTANCE));
            client = client.secure(spec -> spec.sslContext(tcpSslContextSpec));

        Connection connection =
                client.handle((in, out) -> out.send(Flux.concat(ByteBufFlux.fromString(Mono.just("echo")),
                                in.receive().retain())))
                        .connectNow();

        connection.onDispose()
                .block();
    }
}
