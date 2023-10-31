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


    /**
     * 发送消息
     * @param connection 连接
     * @param message 消息
     * @return Mono<Void>
     * @throws ConnectException 连接异常
     */
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
