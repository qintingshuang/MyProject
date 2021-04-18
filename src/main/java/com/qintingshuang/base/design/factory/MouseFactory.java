package com.qintingshuang.base.design.factory;

/**
 * @author qintingshuang
 * @create 2021-04-13 11:41
 * @description 鼠标工厂
 *
 * 工厂模式：是两个接口 一个是产品接口，一个是工厂接口，其中具体产品实现产品接口，
 * 具体的工厂实现工厂接口，然后具体工厂与具体产品进行mapping
 *
 * 通过该工厂实现该产品
 *
 **/
public interface MouseFactory {

    /**
     * 获取鼠标产品
     * @return
     */
    Mouse getMouse();


    /**
     * 鼠标打印
     * @return
     */
    String  getMousePrintln();

}
