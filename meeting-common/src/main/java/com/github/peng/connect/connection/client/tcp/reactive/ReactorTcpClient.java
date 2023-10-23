package com.github.peng.connect.connection.client.tcp.reactive;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.github.peng.connect.connection.ConnectionConstants;
import com.github.peng.connect.connection.client.ClientLifeStyle;
import com.github.peng.connect.connection.client.ReactiveClientAction;
import com.github.peng.connect.connection.server.ServerToolkit;
import com.github.peng.connect.model.proto.Account;
import com.github.peng.connect.proto.ProtoParseUtil;
import com.github.peng.connect.spi.ReactiveHandlerSPI;
import com.google.protobuf.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.logging.LogLevel;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.tcp.TcpClient;
import reactor.util.retry.Retry;

import java.net.InetSocketAddress;
import java.time.Duration;
import java.util.concurrent.Callable;

/**
 * reactor 实现客户端
 * @author pengpeng
 * @date 2023/1/8
 */
@Slf4j
//@Component
public class ReactorTcpClient implements ClientLifeStyle, ReactiveClientAction {

    private Connection connection = null;
    private InetSocketAddress address;
    private TcpClient client;

    @Override
    public void config(InetSocketAddress address) {
        this.address = address;
        client = TcpClient
                    .create()
                    .wiretap("tcp-client", LogLevel.INFO)
                    .host(this.address.getHostString())
                    .port(this.address.getPort())
                    .doOnConnected(ReactiveHandlerSPI.wiredSpiHandler())
                    .doOnDisconnected(con -> {
                        Account.AccountInfo accountInfo = con.channel().attr(ConnectionConstants.BING_ACCOUNT_KEY).get();
                        if (accountInfo == null){
                            return;
                        }
                        log.warn("the netty connection  is disconnect");
                        ServerToolkit.contextAction().closeAndRmConnection(accountInfo.getAccount());
                    })
                ;
    }

    @Override
    public Boolean connect(InetSocketAddress address)    {
        config(address);
        try{
            connection = client.connectNow();
        }catch (Exception exception){
            log.error("connect server {}  port {} encounter error , stack is \n {}",address.getHostString(),address.getPort(), ExceptionUtil.stacktraceToString(exception));
            throw new IllegalArgumentException("remote server is invalid!");
        }
        return Boolean.TRUE;
    }


    @Override
    public Mono<Void> sendString(String message) {
        if (isAlive()) {
            return connection.outbound().sendString(Mono.just(message)).then();
        }
        throw new IllegalArgumentException("The connection is disConnect!");
    }

    @Override
    public Boolean connect() throws IllegalArgumentException {
        return connect(address);
    }

    @Override
    public Boolean reTryConnect() throws IllegalArgumentException {
        Callable<Boolean> callable = () -> {
            if (isAlive()){
                return Boolean.TRUE;
            }
            return connect();
        };
        return Flux.from(
                    Mono.fromCallable(callable))
                    .retryWhen(
                        Retry
                        .backoff(3, Duration.ofSeconds(1)).jitter(0.3d)
                        .filter(throwable -> throwable instanceof IllegalArgumentException)
                        .onRetryExhaustedThrow((spec, rs) -> new IllegalArgumentException("remote server is invalid !pls retry later!")))
                .onErrorResume(throwable -> Mono.just(Boolean.FALSE))
                .blockFirst();
    }

    @Override
    public void releaseChannel() {
        connection.onDispose().subscribe();
    }

    @Override
    public Boolean isAlive() {
        return connection != null && !connection.isDisposed() && connection.channel().isActive();
    }


    @Override
    public Mono<Void> sendMessage(Message message) {
        if (isAlive()){
            ByteBufAllocator alloc = connection.channel().alloc();

            ByteBuf byteBuf = ProtoParseUtil.parseMessage2ByteBuf(message, alloc.buffer());

            return connection.outbound().send(Mono.just(byteBuf)).then();
        }

        if (reTryConnect()){
            return sendMessage(message);
        }

        throw new IllegalArgumentException("connection is invalid !");
    }



    private enum SingleInstance{
        INSTANCE;
        private final ReactorTcpClient instance;
        SingleInstance(){
            instance = new ReactorTcpClient();
        }
        private ReactorTcpClient getInstance(){
            return instance;
        }
    }
    public static ReactorTcpClient getInstance(){
        return SingleInstance.INSTANCE.getInstance();
    }


}
