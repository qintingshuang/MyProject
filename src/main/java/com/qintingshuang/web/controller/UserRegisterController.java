package com.qintingshuang.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.qintingshuang.base.design.chainResponsibily.outControl.Task;
import com.qintingshuang.base.design.observed.UserService;
import com.qintingshuang.base.design.strategic.BizType;
import com.qintingshuang.base.design.strategic.PermissionService;
import com.qintingshuang.base.thread.fourMethodThread.RunnableTask;
import com.qintingshuang.web.common.QinResponse;
import com.qintingshuang.web.exception.BusinessException;
import com.qintingshuang.web.exception.BusinessManage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.qintingshuang.web.enums.ErrorEnum.BUSINESS_EXCEPTION;
import static com.qintingshuang.web.enums.ErrorEnum.CUSTOMIZE_EXCEPTION;

/**
 * @author qintingshuang
 * @create 2021-02-10 17:47
 * @description
 **/
@Slf4j
@Api(tags = "设计模式")
@RequestMapping("/qintingshuang/v1.0")
@RestController
public class UserRegisterController {

    @Resource
    UserService userService;


    @Autowired
    PermissionService permissionService;

    @ApiOperation("观察者模式")
    @GetMapping("/user/Register")
    public JSONObject businessClick(@RequestParam("userName") String userName) {
        userService.register(userName);
        return new JSONObject();
    }


    @ApiOperation("策略模式")
    @GetMapping("/user/permission")
    public void permission(@RequestParam("userId") Long userId,
                           @RequestParam("bizType") BizType bizType) {
        permissionService.permissionCheck(userId, bizType);
    }


    @ApiOperation("异常验证")
    @GetMapping("/user/exceptionAdvice")
    public QinResponse permission() {

//        String aaa=null;
//        if(aaa.equals("22222")){
//
//        }

        BusinessManage.invalidIf(true, BUSINESS_EXCEPTION, new RunnableTask());
        return QinResponse.success();
    }


    @ApiOperation("AOP验证")
    @PostMapping("/user/aopTest")
    public QinResponse aopTest(@RequestHeader("param1") String  param1,
                               @RequestParam("param2") String  param2,
                               @RequestBody Task task) {

//        String aaa=null;
//        if(aaa.equals("22222")){
//
//        }
        log.info(Thread.currentThread().getName());
        BusinessManage.invalidIf(true,BUSINESS_EXCEPTION,new RunnableTask());
        return QinResponse.success();
    }


}
