package com.github.peng.connect.handler;

import com.github.peng.connect.connection.server.ProtoBufProcess;
import com.github.peng.connect.connection.server.ServerToolkit;
import com.github.peng.connect.connection.server.context.IConnectContextAction;
import com.github.peng.connect.enums.ProtocolMessageMapEnum;
import com.github.peng.connect.model.proto.Chat;
import com.google.protobuf.Any;
import com.google.protobuf.Message;
import org.springframework.stereotype.Component;
import reactor.netty.Connection;

/**
 * chat message process
 */
@Component
public class ChatProcessService implements ProtoBufProcess {

    @Override
    public ProtocolMessageMapEnum type() {
        return ProtocolMessageMapEnum.CHAT;
    }

    @Override
    public void process(Connection con, Message message) {

            Chat.ChatMessage chatMessage = (Chat.ChatMessage) message;

            Any chat = chatMessage.getChat();

            IConnectContextAction contextAction = ServerToolkit.contextAction();

    }
}
