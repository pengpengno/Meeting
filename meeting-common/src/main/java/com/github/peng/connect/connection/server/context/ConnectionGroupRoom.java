package com.github.peng.connect.connection.server.context;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import reactor.core.publisher.Flux;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.DelayQueue;

/***
 * 链接分组
 */

@Data
public class ConnectionGroupRoom {

    private String roomKey ;

    private List<IConnection> connections ;


    Queue<byte[]> data ; // 用于存储数据

    private IConnection connection ;

    public ByteBuf byteBuf;

    public Object object = new Object();


    private Flux<byte[]> byteFlux ;

    public void addConnection(IConnection connection){
        if (connections == null){
            synchronized (object){
                if (connections == null){
                    connections = new ArrayList<>();
                }
            }
        }
        connections.add(connection);

    }

    public void addData(byte[] bytes){

        if (data == null){
            synchronized (object){
                if (data == null){
                    data = new ArrayDeque<>();
                }
            }
        }
        data.add(bytes);
    }


    public byte[] offerFrameData () {

        return byteFlux.blockFirst();
//        if (data == null){
//            return null;
//        }
//        return data.poll();
    }

//    public

//    public void Byte


}
