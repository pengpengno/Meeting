package com.github.meeting.common.connect.connection.server;


import com.github.meeting.common.connect.connection.server.context.IConnectContext;
import com.github.meeting.common.connect.connection.server.context.IConnectContextAction;
import com.github.meeting.common.connect.connection.server.tcp.ReactorTcpAction;
import com.github.meeting.common.connect.connection.server.tcp.ReactorTcpServer;

/**
 * server 工具包
 * @author pengpeng
 * @description
 * @date 2023/3/6
 */
public class ServerToolkit {


    public static IConnectContextAction contextAction(){
        return IConnectContext.getInstance();
    }

    public static ReactiveServerAction reactorTcpServerAction(){
        return ReactorTcpAction.getInstance();
    }


    public static ReactiveServer reactiveServer(){
        return ReactorTcpServer.getInstance();
    }

}
