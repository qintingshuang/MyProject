package com.qintingshuang.base.basic;

/**
 * @author qintingshuang
 * @create 2020-12-28 11:20
 * @description 抽象类
 **/
public abstract class AbstractTest {


    static int i = 0;
    private int i1 = 0;

    /**
     * 抽象方法就是为了让重写，所以不能使用private
     */
    public abstract void abstractMethod();


    /**
     * 类中所有的private方法都隐式地指定为final
     */
    private  void  privatefinal(){}


}
