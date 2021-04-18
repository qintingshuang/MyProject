package com.qintingshuang.base.design.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author qintingshuang
 * @create 2021-04-13 13:44
 * @description 惠普鼠标工厂
 **/
@Component
public class HpMouseFactory implements MouseFactory {

    @Resource(name = "hpMouse")
    Mouse mouse;

    @Override
    public Mouse getMouse() {
        //业务逻辑
        return mouse;
    }

    @Override
    public String getMousePrintln() {
        //业务逻辑
        return mouse.printBrand();
    }
}
