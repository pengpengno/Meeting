package com.github.peng.connect.connection.server;

import com.github.peng.connect.connection.server.context.IConnection;
import com.google.protobuf.Message;
import reactor.core.publisher.Mono;

import java.net.ConnectException;

/**
 *
 * @author pengpeng
 * @description
 * @date 2023/3/7
 */
public interface ReactiveServerAction {


    Mono<Void>  sendString(IConnection connection, String message) throws ConnectException;

    Mono<Void>  sendString(String account,String  message) throws ConnectException;


    /***
     *
     * @param account
     * @param message
     * @return
     */
    Mono<Void>  sendMessage(String account , Message message) throws ConnectException;









}
