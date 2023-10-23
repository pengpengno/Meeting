module meeting.common {
    exports com.github.peng.model.account;
    exports com.github.peng.util;
    exports com.github.peng.model;
    uses com.github.peng.connect.connection.ConnectionConsumer;

    requires static lombok;
    requires reactor.netty.core;
    requires org.reactivestreams;
    requires reactor.core;
    requires hutool.all;
    requires com.google.protobuf;
    requires org.slf4j;
    requires com.github.benmanes.caffeine;

    requires io.netty.common;
    requires io.netty.buffer;
    requires com.google.common;
    requires io.netty.transport;
    requires com.google.guice;
    requires io.netty.handler;
    requires jakarta.annotation;
    requires javax.inject;
    requires spring.boot.starter.validation;
    requires org.mapstruct;
    requires jakarta.validation;
}