package com.qintingshuang.base.design.observed;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;

/**
 * @author qintingshuang
 * @create 2021-02-12 18:52
 * @description 用户邮件
 **/
@Slf4j
@Service
public class UserEmailService{

    @Order(1)
    @EventListener
    public void email(UserRegisterEvent event) {
        log.info(event.getUserName()+"：发送邮件");
    }
}


