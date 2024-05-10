package com.github.meeting.web.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Description:
 * <p>
 * </p>
 *
 * @author pengpeng
 * @version 1.0
 * @since 2024/5/9
 */
@Table("account_oauth")
public class AccountOAuth {

    @Id
    private Long id;

    private String accountId;

    private String oauthId;

    private String oauthProvider;



}