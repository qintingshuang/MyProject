package com.qintingshuang.base.design.factory;

import org.springframework.stereotype.Component;

/**
 * @author qintingshuang
 * @create 2021-04-12 17:15
 * @description 工厂模式 重新写一下
 *
 **/
@Component
public class HpMouse implements Mouse {

    @Override
    public String printBrand() {
        String  a="业务代码:惠普鼠标产品";
        System.err.println(a);
        return a;
    }
}
