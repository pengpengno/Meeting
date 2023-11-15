package com.github.peng.connect.connection.server.context;


import com.github.peng.connect.connection.ConnectionConstants;
import com.github.peng.connect.enums.ConnectionStatus;
import com.github.peng.connect.model.proto.Account;
import io.netty.channel.Channel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.netty.Connection;

/**
 * 链接 connection
 * @author pengpeng
 * @description
 * @date 2023/3/6
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReactorConnection implements IConnection{


    private Channel channel;

    private Connection connection;

    private Account.AccountInfo accountInfo;

    private String group ;


    @Override
    public Channel channel() {
        return channel;
    }

    @Override
    public Connection connection() {
        return connection;
    }

    @Override
    public Account.AccountInfo accountInfo() {
        if (accountInfo != null){
            return accountInfo;
        }else {
            accountInfo = channel.attr(ConnectionConstants.BING_ACCOUNT_KEY).get();
        }
        return accountInfo;
    }

    @Override
    public ConnectionStatus status() {
        if (channel.isActive() && !connection.isDisposed()){
            return ConnectionStatus.ACTIVE;
        }
        return ConnectionStatus.OFFLINE;
    }

    @Override
    public Boolean online() {
         return status() == ConnectionStatus.ACTIVE;
    }


    @Override
    public String group() {
        return group;
    }

    @Override
    public void close() {
        if (channel != null && channel.isActive()){
            channel.close();
        }
        if (connection!=null && connection.isDisposed()){
            connection.disposeNow();
        }
    }
}
