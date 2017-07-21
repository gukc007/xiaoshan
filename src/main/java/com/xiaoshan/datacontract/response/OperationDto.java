package com.xiaoshan.datacontract.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gukc007 on 2017-05-10.
 */
public class OperationDto {
    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("编码")
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
