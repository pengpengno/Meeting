package com.github.meeting.common.model.account;

import com.github.meeting.common.model.AccountSPI;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/***
 * 用户基础信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountVo implements Serializable , AccountSPI {

    private Long userId;
    @NotNull(message = "账户不可为空！")
    private String account;

    private String userName;   // 用户姓名
    @NotNull(message = "密码不可为空！")
    private String password;

    private String email;  // 电子邮件

    @Override
    public String account() {
        return account;
    }

    @Override
    public String accountName() {
        return userName;
    }

    @Override
    public Long userId() {
        return getUserId();
    }
}
