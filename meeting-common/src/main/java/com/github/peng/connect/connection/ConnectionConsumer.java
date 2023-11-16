package com.github.peng.connect.connection;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.NettyInbound;
import reactor.netty.NettyOutbound;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import static reactor.netty.ReactorNetty.format;

/**
 * reactor connection consumer
 * @author pengpeng
 * @description
 * @date 2023/3/13
 */
@Slf4j
public abstract class ConnectionConsumer implements Consumer<Connection> {


    final BiFunction<? super NettyInbound, ? super NettyOutbound, ? extends Publisher<Void>> handler;

    public ConnectionConsumer(BiFunction<? super NettyInbound, ? super NettyOutbound, ? extends Publisher<Void>> handler) {
        this.handler = handler;
    }

    public  BiFunction<? super NettyInbound, ? super NettyOutbound, ? extends Publisher<Void>> handler(){
        if (handler == null){
            return DefaultConnectionConsumer.handler;
        }
        return handler;
    }

    /***
     * default connection consumer
     */
    public static final class DefaultConnectionConsumer extends ConnectionConsumer {

        static final BiFunction<? super NettyInbound, ? super NettyOutbound, ? extends Publisher<Void>> handler = (nettyInbound, nettyOutbound) -> {
            log.info(" The Default Handler is Active ! There is no logic to do ");

            return Mono.never();
        };


        public DefaultConnectionConsumer(){
            super(handler);
        }


        @Override
        public void accept(Connection c) {
            super.accept(c);
        }
    }

    @Override
    public void accept(Connection c) {
        if (log.isDebugEnabled()) {
            log.debug(format(c.channel(), "Handler is being applied: {}"), handler);
        }
        Mono.fromDirect(handler.apply((NettyInbound) c, (NettyOutbound) c))
                .subscribe(e-> {
                    log.debug("define default dispose");
                    c.disposeSubscriber()  ;
                });
    }
}
