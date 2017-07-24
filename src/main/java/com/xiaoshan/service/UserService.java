package com.xiaoshan.service;


import com.xiaoshan.datacontract.request.UserParamDto;
import com.xiaoshan.datacontract.request.UserLoginParam;
import com.xiaoshan.datacontract.response.UserResponse;

/**
 * Created by gukc007 on 2017-05-07.
 */
public interface UserService {
    Long addUser(UserParamDto userParamDto) throws Exception;

    UserResponse login(UserLoginParam userLoginParam) throws Exception;

    UserResponse verfiyUser(String token) throws Exception;
}
