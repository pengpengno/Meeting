package com.github.peng.connect.spi;

import com.github.peng.connect.connection.ConnectionConsumer;
import com.github.peng.connect.connection.server.ReactiveConnectionConsumer;
import com.github.peng.connect.module.ConnectionModule;
import com.github.peng.connect.module.GuiceModuleInjector;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 执行 handler spi 注入
 * @author pengpeng
 * @description
 * @date 2023/3/13
 */
@Slf4j
@AllArgsConstructor
public class ReactiveHandlerSPI {




    /**
     * 注入 reactor 执行 handler spi
     * @return connection 处理 consumer
     */
    public static ConnectionConsumer wiredSpiHandler()  {

        ServiceLoader<ConnectionConsumer> load = ServiceLoader.load(ConnectionConsumer.class);

        Iterator<ConnectionConsumer> iterator = load.iterator();

        if (iterator.hasNext()){

            ConnectionConsumer next = iterator.next();

            log.info("ReactiveHandlerSPI wired class = {}", next.getClass().getName());

            return next;
        }
        log.info("ReactiveHandlerSPI could not found handlerSPI , it will use  default handler!");

        return GuiceModuleInjector.injector.getInstance(ConnectionConsumer.class);
//        return new ConnectionConsumer.DefaultConnectionConsumer();
    }





}
