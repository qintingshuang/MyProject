package com.qintingshuang.base.reflect;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: qintingshuang
 * @date: 2019/11/9 12:14
 * @description:
 */
@Slf4j
public class Person {

    private String Name;

    private Integer age;

    private String hobby = "basketball";


    public Person() {
        log.info("实例化了共有的Person对象");
    }

    private Person(Integer age) {
        this.age = age;
        log.info("age:"+this.age);
        log.info("实例化了私用的Person对象");

    }

    private String getName() {
        return this.Name;
    }

    private void setName(String name) {
        this.Name = name;
    }


}
