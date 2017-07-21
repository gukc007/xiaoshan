package com.xiaoshan.api;

import com.xiaoshan.common.AllowAnonymous;
import com.xiaoshan.datacontract.response.UserOperationDto;
import com.xiaoshan.datacontract.request.UserParamDto;
import com.xiaoshan.datacontract.request.UserLoginParam;
import com.xiaoshan.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by gukc007 on 2017-05-07.
 */
@Api(description = "用户")
@RestController
@RequestMapping("/api/user")
public class UserRestful {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户注册", nickname = "用户注册")
    @RequestMapping(method = RequestMethod.POST, value = "/addUser")
    public Long addUser(@RequestBody UserParamDto userParamDto) throws Exception {
        return userService.addUser(userParamDto);
    }

    @AllowAnonymous
    @ApiOperation(value = "用户登录", nickname = "用户登录")
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public UserOperationDto login(@RequestBody UserLoginParam userLoginParam) throws Exception {
        int a = 1;
        return userService.login(userLoginParam);
    }

}
