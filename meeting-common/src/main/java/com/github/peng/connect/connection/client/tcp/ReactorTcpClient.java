package com.github.peng.connect.connection.client.tcp;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.github.peng.connect.connection.client.ClientLifeStyle;
import com.github.peng.connect.connection.client.ReactiveClientAction;
import com.github.peng.connect.handler.client.ClientInboundHandler;
import com.github.peng.connect.handler.proto.ProtoBufMessageLiteScanner;
import com.google.protobuf.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.rtsp.*;
import io.netty.handler.logging.LogLevel;
import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.NettyOutbound;
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
public class ReactorTcpClient implements ClientLifeStyle, ReactiveClientAction {

    private Connection connection = null;

    private Disposable disposable = null;

    private InetSocketAddress address;

    private TcpClient client;

    @Override
    public ClientLifeStyle config(InetSocketAddress address) {
        this.address = address;
        client = TcpClient
                    .create()
                    .wiretap("tcp-client", LogLevel.INFO)
                    .host(this.address.getHostString())
                    .port(this.address.getPort())
                    .doOnChannelInit((connectionObserver, channel, remoteAddress) -> {
                        log.debug("init channel pipeline ");
                        ChannelPipeline pipeline = channel.pipeline();
                        pipeline.addFirst(new ClientInboundHandler("jwttttttttt"));
//                        pipeline.addLast(ProtoBufMessageLiteScanner.protobufEncoder());
                        ProtoBufMessageLiteScanner.protobufDecoders()
                                .forEach(handler -> pipeline.addLast(handler));
                        pipeline.addLast(new RtspDecoder());
                        pipeline.addLast(new RtspEncoder());
                    })
                ;
        return this;
    }

    @Override
    public ClientLifeStyle connect(InetSocketAddress address)    {
        config(address);

        try{
            connection = client.connectNow();
//            var headers = new DefaultFullHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.OPTIONS, "/live");
//            headers.set(RtspHeaders.Names.CSEQ, 1);
//            headers.set(RtspHeaders.Names.METHOD, RtspMethods.OPTIONS);
//            ByteBuf content = Unpooled.EMPTY_BUFFER;
//            FullHttpRequest request = new DefaultFullHttpRequest(RtspVersions.RTSP_1_0, headers, content);
//            connection.outbound().sendObject(Mono.just(headers)).var
            connection.inbound().withConnection((con)-> {
                connection.inbound().receive().asString().doOnNext(log::info)
                        .subscribe();
            });


        }catch (Exception exception){
            log.error("connect server {}  port {} encounter error , stack is \n {}",address.getHostString(),address.getPort(), ExceptionUtil.stacktraceToString(exception));
            throw new IllegalArgumentException("remote server is invalid!");
        }

        return this;
    }


    @Override
    public Mono<Void> sendObject(Object message) {
        if (isAlive()) {

            NettyOutbound nettyOutbound = connection.outbound().sendObject(Mono.just(message));

            return nettyOutbound.then();

        }
        throw new IllegalArgumentException("The connection is disConnect!");

    }

    @Override
    public Mono<Void>    sendString(String message) {
        if (isAlive()) {

            NettyOutbound nettyOutbound = connection.outbound().sendString(Mono.just(message));

            return nettyOutbound.then();

        }
        throw new IllegalArgumentException("The connection is disConnect!");
    }

    @Override
    public ClientLifeStyle connect() throws IllegalArgumentException {
        return connect(address);
    }

    @Override
    public Boolean reTryConnect() throws IllegalArgumentException {
        Callable<Boolean> callable = () -> {
            if (isAlive()){
                return Boolean.TRUE;
            }
            ClientLifeStyle connect = connect();
            return true;
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
        return connection != null
                && !connection.isDisposed()
                && connection.channel().isActive();
    }


    @Override
    public Mono<Void> sendMessage(Message message) {
        if (isAlive()){

//            ByteBufAllocator alloc = connection.channel().alloc();

//            ByteBuf byteBuf = ProtoParseUtil.parseMessage2ByteBuf(message, alloc.buffer());

//            return connection.outbound().send(Mono.just(byteBuf)).then();

            NettyOutbound nettyOutbound = connection.outbound().sendObject(Mono.just(message));

            return nettyOutbound.then();
        }

//        if (reTryConnect()){
//            return sendMessage(message);
//        }

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
