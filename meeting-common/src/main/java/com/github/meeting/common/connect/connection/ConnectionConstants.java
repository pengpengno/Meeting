package com.github.meeting.common.connect.connection;

import com.github.meeting.common.connect.model.proto.Account;
import io.netty.util.AttributeKey;

/**
 * @author pengpeng
 * @description
 * @date 2023/3/16
 */
public interface ConnectionConstants {

    public static AttributeKey<Account.AccountInfo> BING_ACCOUNT_KEY = AttributeKey.valueOf("Account");



    AttributeKey<String> ROOM_KEY = AttributeKey.valueOf("group");

}
