package com.qintingshuang.base.thread.fourMethodThread;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: qintingshuang
 * @date: 2019/11/19 10:03
 * @description:
 */
@Slf4j
@NoArgsConstructor
public class RunnableTask implements Runnable {


    private String name = "qin";
    private Integer age = 18;

    public RunnableTask(String name, Integer age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public void run() {
        log.info(this.name + "开启了一个线程" + "他的年纪并不大，只有" + this.age + "岁");
    }

}
