package com.github.peng.connect.connection.client;


import com.github.peng.connect.config.ConnectionDefaultValue;
import com.github.peng.connect.connection.client.tcp.ReactorTcpClient;
import com.github.peng.connect.enums.ConnectTypeEnums;

/**
 * IM系统连接获取客户端实现
 * @description IM系统工具箱
 * @author pengpeng
 * @date 2023/1/9
 */
public  class ClientToolkit {

    /***
     * 获取默认的客户端 lifestyle
     * @return ReactorTcpClient.getInstance()
     */
    public static  ClientLifeStyle clientLifeStyle(){
        ConnectTypeEnums defaultValue = ConnectionDefaultValue.getDefaultConnectType();
        if (defaultValue == ConnectTypeEnums.TCP){
            return  ReactorTcpClient.getInstance();
        }
        return  ReactorTcpClient.getInstance();
    }


    public static ReactiveClientAction reactiveClientAction(){
        ConnectTypeEnums defaultValue = ConnectionDefaultValue.getDefaultConnectType();
        if (defaultValue == ConnectTypeEnums.TCP){
            return  ReactorTcpClient.getInstance();
        }
        return  ReactorTcpClient.getInstance();
    }



}
