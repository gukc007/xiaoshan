package com.xiaoshan.mapping;

import com.google.common.collect.Lists;
import com.xiaoshan.common.helper.SecurityHelper;
import com.xiaoshan.datacontract.response.OperationDto;
import com.xiaoshan.datacontract.response.UserOperationDto;
import com.xiaoshan.datacontract.request.UserParamDto;
import com.xiaoshan.datacontract.response.UserResponse;
import com.xiaoshan.domain.OperationEntity;
import com.xiaoshan.domain.UserEntity;

import java.util.List;


/**
 * Created by gukc007 on 2017-05-07.
 */
public class UserMapping {
    public static UserEntity toEntity(UserParamDto dto) {
        UserEntity entity = new UserEntity();
        entity.setAccount(dto.getAccount());
        entity.setPassword(dto.getPassword());
        entity.setName(dto.getName());
        return entity;
    }

    public static UserResponse toUserResponse(UserEntity entity) {
        UserResponse dto = new UserResponse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAccount(entity.getAccount());
        dto.setToken(SecurityHelper.getToken(entity.getAccount(), entity.getPassword()));

        return dto;
    }

    public static UserOperationDto toUserOperationDto(UserEntity entity) {
        UserOperationDto dto = new UserOperationDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        List<OperationDto> operations = Lists.newArrayList();
        for (OperationEntity operation : entity.getOperations()) {
            operations.add(OperationMapping.toDto(operation));
        }
        dto.setOperations(operations);

        return dto;
    }
}
