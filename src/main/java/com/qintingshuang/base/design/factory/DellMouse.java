package com.qintingshuang.base.design.factory;

import org.springframework.stereotype.Component;

/**
 * @author qintingshuang
 * @create 2021-04-13 11:37
 * @description 戴尔鼠标产品
 **/
@Component
public class DellMouse implements Mouse {


    @Override
    public String printBrand() {
        String  a="业务代码:戴尔鼠标产品";
        System.err.println(a);
        return a;
    }
}
