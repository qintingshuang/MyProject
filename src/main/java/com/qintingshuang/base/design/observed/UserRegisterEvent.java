package com.qintingshuang.base.design.observed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author qintingshuang
 * @create 2021-02-10 18:02
 * @description 注册事件
 **/
public class UserRegisterEvent extends ApplicationEvent {

    private String userName;


    public UserRegisterEvent(Object source) {
        super(source);
    }

    public UserRegisterEvent(Object source, String userName) {
        super(source);
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
