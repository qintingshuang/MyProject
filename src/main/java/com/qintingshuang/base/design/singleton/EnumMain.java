package com.qintingshuang.base.design.singleton;

import lombok.extern.slf4j.Slf4j;

import static com.qintingshuang.base.design.singleton.Singleton_enum.QINTINGSHUANG;

/**
 * @author: qintingshuang
 * @date: 2019/11/9 20:30
 * @description:
 *
 *
 *
 *
 */
@Slf4j
public class EnumMain {


    public static void  main(String[] args){

      Singleton_enum singleton=  QINTINGSHUANG;

      log.info(singleton.toString());

        Singleton_3.getInstance();

    }





}
