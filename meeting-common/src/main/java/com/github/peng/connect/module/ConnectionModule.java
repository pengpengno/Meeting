package com.github.peng.connect.module;

import com.github.peng.connect.connection.ConnectionConsumer;
import com.github.peng.connect.connection.server.ReactiveConnectionConsumer;
import com.github.peng.connect.connection.server.ReactiveServer;
import com.github.peng.connect.connection.server.context.IConnectContext;
import com.github.peng.connect.connection.server.context.IConnectContextAction;
import com.github.peng.connect.connection.server.context.IConnection;
import com.github.peng.connect.connection.server.context.ReactorConnection;
import com.github.peng.connect.connection.server.tcp.ReactorTcpServer;
import com.github.peng.connect.model.proto.Account;
import com.github.peng.connect.model.proto.Chat;
import com.github.peng.cv.ScreenGrabber;
import com.google.inject.*;
import com.google.inject.name.Names;
import com.google.protobuf.MessageLite;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

/**
 * @author pengpeng
 * @description
 * @date 2023/3/6
 */
public class ConnectionModule extends AbstractModule {


    @Override
    protected void configure() {

        bind(ProtobufEncoder.class).toInstance(new ProtobufEncoder());

        bind(MessageLite.class).toProvider(Account.AccountInfo::getDefaultInstance).in(Scopes.NO_SCOPE);

        bind(MessageLite.class).annotatedWith(Names.named("Authenticate")).toInstance(Account.Authenticate.getDefaultInstance());

        bind(MessageLite.class).annotatedWith(Names.named("ChatMessage")).toInstance(Chat.ChatMessage.getDefaultInstance());

//        bind(MessageLite.class).to(Account.Authenticate.class);

//        bind(MessageLite.class).to(Chat.ChatMessage.class);

//        bind(MessageLite.class).to().toInstance(ReactorTcpServer.getInstance());

//        binder().bind(new TypeLiteral<MessageLite>(){}).toInstance(Account.AccountInfo.getDefaultInstance());

//        bind(MessageLite.class).toInstance(Account.Authenticate.getDefaultInstance());
//
//        bind(MessageLite.class).toInstance(Chat.ChatMessage.getDefaultInstance());

        bind(ConnectionConsumer.class).to(ReactiveConnectionConsumer.class).in(Singleton.class);

        bind(IConnectContextAction.class).to(IConnectContext.class).in(Singleton.class);

        bind(IConnection.class).to(ReactorConnection.class);

        bind(ScreenGrabber.class).in(Singleton.class);

        bind(ReactiveServer.class).annotatedWith(Names.named("ReactorTcpServer")).toInstance(ReactorTcpServer.getInstance());
    }
}
