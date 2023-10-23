package com.github.peng.connect.connection.server.context;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.peng.connect.connection.ConnectionConstants;
import com.github.peng.connect.model.proto.Account;
import com.github.peng.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import reactor.netty.Connection;

/**
 * connection 容器
 * @author pengpeng
 * @description
 * @date 2023/3/6
 */
@Slf4j
public class IConnectContext implements IConnectContextAction {


    private final Cache<String, IConnection> connectionCache = Caffeine.newBuilder()
            .maximumSize(1000)
            .build();

    private enum SingleInstance{
        INSTANCE;
        private final IConnectContext instance;
        SingleInstance(){
            instance = new IConnectContext();
        }
        private IConnectContext getInstance(){
            return instance;
        }
    }
    public static IConnectContextAction getInstance(){
        return SingleInstance.INSTANCE.getInstance();
    }



    @Override
    public IConnection applyConnection(String account) {
        return connectionCache.getIfPresent(account);

    }


    @Override
    public IConnection putConnection(IConnection connection) {
        ValidatorUtil.validateThrows(connection, IConnection.Create.class);
        String account = connection.accountInfo().getAccount();
        connectionCache.put(account,connection);
        return connection;
    }

    @Override
    public Boolean closeAndRmConnection(String account) {
        IConnection connection = applyConnection(account);
        if (connection != null){
            connection.close();
        }
        return Boolean.TRUE;
    }

    @Override
    public void close(Connection connection) {
        Account.AccountInfo accountInfo = connection.channel().attr(ConnectionConstants.BING_ACCOUNT_KEY).get();
        if (accountInfo == null){
            log.info("Account is null , remove IConnection is unnecessary!");
            return;
        }
        closeAndRmConnection(accountInfo.getAccount());
    }
}
