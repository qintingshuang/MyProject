package com.qintingshuang.web.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author qintingshuang
 * @create 2021-04-23 13:47
 * @description 测试实体类
 **/
@Data
@AllArgsConstructor
public class EntityTest {


    private String username;

    public void start() {
        System.out.println("TestBean 初始化。。。");
    }

    public void cleanUp() {
        System.out.println("TestBean 销毁。。。");
    }
}


