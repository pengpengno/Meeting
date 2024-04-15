package com.github.meeting.common.model;

/**
 * 账户模块拓展点
 * @author pengpeng
 * @description
 * @date 2023/1/11
 */
public interface AccountSPI {

    /***
     * 获取用户身份唯一标识
     * @return
     */
    public String account() ;

    public Long userId();


    public String accountName();




}
