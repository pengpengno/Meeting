package com.github.peng.connect.connection.server.context;

import reactor.netty.Connection;
import java.net.ConnectException;

/**
 *
 * connection 容器
 * @author pengpeng
 * @description
 * @date 2023/3/6
 */
public interface IConnectContextAction {



    /***
     * 获取指定 account 对应的connection
     * @param account
     * @return  返回指定账户的 connection 如果不存在则返回空
     */
    public IConnection applyConnection(String account) ;



    public IConnection putConnection(IConnection connection);

    /***
     * 关闭指定账户的连接
     * @param account
     * @return
     */
    public Boolean closeAndRmConnection(String account) throws ConnectException;


    public void close(Connection connection);



}
