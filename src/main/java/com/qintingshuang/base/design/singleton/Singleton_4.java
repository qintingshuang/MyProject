package com.qintingshuang.base.design.singleton;

/**
 * @author: qintingshuang
 * @date: 2019/11/10 13:03
 * @description: 懒汉式 单线程线程安全，多线程不安全
 */
public class Singleton_4 {

    private static Singleton_4 instance;

    private Singleton_4() {
    };

    public static Singleton_4 getInstance() {
        if (instance == null) {
            instance=new Singleton_4();
        }
        return  instance;
    }



}
