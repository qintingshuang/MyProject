package com.qintingshuang.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.qintingshuang.base.design.observed.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author qintingshuang
 * @create 2021-02-10 17:47
 * @description
 **/
@Api(tags = "用户注册测试")
@RequestMapping("/qintingshuang/v1.0")
@RestController
public class UserRegisterController {

    @Resource
    UserService userService;

    @ApiOperation("触发接口")
    @GetMapping("/user/Register")
    public JSONObject businessClick(@RequestParam("userName") String userName) {
        userService.register(userName);
        return new JSONObject();
    }
}
