package com.qintingshuang.base.thread.fourMethodThread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author: qintingshuang
 * @date: 2019/11/15 23:25
 * @description:
 *  * * 四种线程的实现方式
 *  *  * *  https://www.imooc.com/article/48991
 */
@Slf4j
public class ThreadMain {
    @Test
    public void test(){
//        new ThreadImpl("Kevin").start();
//        new ThreadImpl("tom").start();
//        log.info("创建了两个线程");


        new  RunableInterface("kevin",18);
        new  RunableInterface("tony",20);

    }

}
