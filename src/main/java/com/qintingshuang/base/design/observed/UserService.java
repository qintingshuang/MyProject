package com.qintingshuang.base.design.observed;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * @author qintingshuang
 * @create 2021-02-12 18:44
 * @description 用户服务
 **/
@Slf4j
@Service
public class UserService implements ApplicationEventPublisherAware {

    ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void register(String userName) {
        log.info(userName + "：完成用户注册");
        applicationEventPublisher.publishEvent(new UserRegisterEvent(this, userName));
    }
}
