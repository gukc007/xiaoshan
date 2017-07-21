package com.xiaoshan.datacontract.response;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by gukc007 on 2017-05-10.
 */
public class UserOperationDto {

    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("操作")
    List<OperationDto> operations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OperationDto> getOperations() {
        return operations;
    }

    public void setOperations(List<OperationDto> operations) {
        this.operations = operations;
    }
}
