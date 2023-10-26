package com.github.peng.connect.handler.proto;

import com.github.peng.connect.connection.server.ServerToolkit;
import com.github.peng.connect.connection.server.context.IConnectContextAction;
import com.github.peng.connect.connection.server.context.IConnection;
import com.github.peng.connect.model.proto.Account;
import com.google.protobuf.Message;


/**
 * @author pengpeng
 * @description
 * @date 2023/3/14
 */
public class AccountInfoHandler {

    private IConnectContextAction contextAction = ServerToolkit.contextAction();
    public static void process(IConnection connection, Message message){
        if (message.getClass() == Account.AccountInfo.class){
            Account.AccountInfo accountInfo = (Account.AccountInfo) message;
            if (null == connection){

            }
//            contextAction.putConnection()

        }

    }
}
