package com.github.meeting.common.model.account.session;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * 会话创建实体
 * @author pengpeng
 * @description
 * @date 2023/1/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SessionAccountVo extends SessionInfoVo {


    @NotEmpty(message = "The specify account could not be empty!" , groups = {SessionAccountAdd.class})
    private Set<Long> addUseIdSet;


    /**
     * @author pengpeng
     * @description
     * @date 2023/1/17
     */
    public interface SessionAccountAdd {
    }
}
