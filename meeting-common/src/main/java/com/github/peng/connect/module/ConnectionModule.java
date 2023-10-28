package com.github.peng.connect.module;

import com.github.peng.connect.connection.ConnectionConsumer;
import com.github.peng.connect.connection.server.ReactiveConnectionConsumer;
import com.github.peng.connect.connection.server.ReactiveServer;
import com.github.peng.connect.connection.server.context.IConnectContext;
import com.github.peng.connect.connection.server.context.IConnectContextAction;
import com.github.peng.connect.connection.server.context.IConnection;
import com.github.peng.connect.connection.server.context.ReactorConnection;
import com.github.peng.connect.connection.server.tcp.ReactorTcpServer;
import com.github.peng.cv.ScreenGrabber;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

/**
 * @author pengpeng
 * @description
 * @date 2023/3/6
 */
public class ConnectionModule extends AbstractModule {


    @Override
    protected void configure() {

        bind(ConnectionConsumer.class).to(ReactiveConnectionConsumer.class).in(Singleton.class);

        bind(IConnectContextAction.class).to(IConnectContext.class).in(Singleton.class);

        bind(IConnection.class).to(ReactorConnection.class);

        bind(ScreenGrabber.class).in(Singleton.class);

        bind(ReactiveServer.class).annotatedWith(Names.named("ReactorTcpServer")).toInstance(ReactorTcpServer.getInstance());
    }
}
