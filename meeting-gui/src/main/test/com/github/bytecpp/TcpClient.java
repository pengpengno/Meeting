package com.github.bytecpp;

import com.github.peng.connect.connection.client.ClientLifeStyle;
import com.github.peng.connect.connection.client.ClientToolkit;
import com.github.peng.connect.connection.server.ReactiveServer;
import com.github.peng.connect.connection.server.ServerToolkit;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;

/**
 * @author pengpeng
 * @description
 * @date 2023/11/2
 */
public class TcpClient {



    @Test
    public void test () {
        ReactiveServer start = ServerToolkit.reactiveServer()
                .init(new InetSocketAddress(8080))
                .start();

    }

    @Test
    public void client () {
        ClientLifeStyle connect = ClientToolkit.clientLifeStyle()
                .connect(new InetSocketAddress(8080));



    }
}
