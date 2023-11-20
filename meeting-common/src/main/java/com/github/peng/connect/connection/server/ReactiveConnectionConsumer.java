package com.github.peng.connect.connection.server;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.github.peng.connect.connection.ConnectionConsumer;
import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.HttpObjectDecoder;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.rtsp.RtspDecoder;
import io.netty.handler.codec.rtsp.RtspEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.netty.Connection;
import reactor.netty.NettyOutbound;

/**
 * 响应式 服务处理 Handler
 * @author pengpeng
 * @description
 * @date 2023/3/14
 */
@Slf4j
public class ReactiveConnectionConsumer extends ConnectionConsumer {


    @SneakyThrows
    public ReactiveConnectionConsumer(){
        super((nettyInbound, nettyOutbound) -> {

            Flux<byte[]> handle = nettyInbound.receive().handle((byteBuf, sink) ->

                nettyInbound.withConnection(connection -> {

//                connection.add
                log.debug("receive data ");

                int i = byteBuf.readableBytes();

                    if (i > 0) {

                    try{

//                        ByteBufProcessService.getInstance().process(connection,byteBuf);
                        DataFrameProcessService.getInstance().process(connection,byteBuf);

                    }catch (Exception exception){

                        log.error("reactor netty occur error {} ", ExceptionUtil.stacktraceToString(exception));

                        sink.next(("occur error {} " + ExceptionUtil.stacktraceToString(exception)).getBytes());

                    }

                }

                sink.next("receive the data from client".getBytes());

            }));

            var outbound = nettyOutbound.sendByteArray(Flux.concat(handle));

            return outbound.then();

        });

        log.debug("The ReactorConnectionConsumer SPI FxReactiveClientHandler service provider has load ! ");
    }



    @Override
    public void accept(Connection c) {

        super.accept(c);

    }
}
