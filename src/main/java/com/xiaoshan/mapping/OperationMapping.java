package com.xiaoshan.mapping;

import com.xiaoshan.datacontract.response.OperationDto;
import com.xiaoshan.domain.OperationEntity;

/**
 * Created by gukc007 on 2017-05-10.
 */
public class OperationMapping {
    public static OperationDto toDto(OperationEntity entity) {
        OperationDto dto = new OperationDto();
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());

        return dto;
    }
}
