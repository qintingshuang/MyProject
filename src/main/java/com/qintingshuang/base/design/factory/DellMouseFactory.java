package com.qintingshuang.base.design.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author qintingshuang
 * @create 2021-04-13 15:11
 * @description 戴尔鼠标工厂
 **/
@Component
public class DellMouseFactory implements MouseFactory {

    @Resource(name = "dellMouse")
    Mouse mouse;

    @Override
    public Mouse getMouse() {
        return mouse;
    }

    @Override
    public String getMousePrintln() {
        return mouse.printBrand();
    }
}
