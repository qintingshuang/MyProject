package singleton;

import lombok.extern.slf4j.Slf4j;

import static singleton.EnumSingleton.QINTINGSHUANG;

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

      EnumSingleton singleton=  QINTINGSHUANG;

      log.info(singleton.toString());

        Singleton_3.getInstance();

    }





}
