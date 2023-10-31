package com.github.peng.connect.handler.proto;

import com.github.peng.connect.module.ConnectionModule;
import com.google.inject.Guice;
import com.google.inject.Stage;
import com.google.inject.TypeLiteral;
import com.google.protobuf.MessageLite;
import java.util.Collection;

public class ProtoBufMessageLiteScanner {


    /**
     * 扫描所有的 messageLite
     * @return
     */
    public static Collection<? extends MessageLite> scanAllMessageLite(){
        var injector = Guice.createInjector(Stage.TOOL , new ConnectionModule());
        return injector.findBindingsByType(TypeLiteral.get(MessageLite.class))
                .stream().map(e-> e.getProvider().get().getDefaultInstanceForType())
                .toList();


    }

}
