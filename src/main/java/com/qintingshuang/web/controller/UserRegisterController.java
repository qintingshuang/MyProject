package com.qintingshuang.web.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qintingshuang
 * @create 2021-02-10 17:47
 * @description
 **/
@Api(tags = "用户注册测试")
@RequestMapping("/qintingshuang/v1.0")
@RestController
public class UserRegisterController {

    @ApiOperation("触发接口")
    @GetMapping("/user/Register")
    public JSONObject businessClick(@RequestParam("userName") String userName) {
        return new JSONObject();
    }
}
