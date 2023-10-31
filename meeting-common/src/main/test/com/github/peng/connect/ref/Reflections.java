package com.github.peng.connect.ref;

import com.github.peng.connect.handler.proto.ProtoBufMessageLiteScanner;
import com.google.protobuf.MessageLite;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

@Slf4j
public class Reflections {



    @Test
    public void reflections() {

        Collection<? extends MessageLite> messageLites = ProtoBufMessageLiteScanner.scanAllMessageLite();

        Assertions.assertNotNull(messageLites);
    }

}
