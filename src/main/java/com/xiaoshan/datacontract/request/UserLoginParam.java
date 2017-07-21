package com.xiaoshan.datacontract.request;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gukc007 on 2017-05-07.
 */
public class UserLoginParam {
    @ApiModelProperty("帐号")
    private String account;

    @ApiModelProperty("密码")
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
