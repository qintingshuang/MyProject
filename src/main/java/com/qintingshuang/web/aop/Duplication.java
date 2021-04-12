package com.qintingshuang.web.aop;

import com.qintingshuang.web.enums.DupType;

import java.lang.annotation.*;

import static com.qintingshuang.web.enums.DupType.PARAM_TYPE;

/**
 * @author qintingshuang
 * @create 2021-04-09 11:24
 * @description 幂等注解
 **/
@Documented
@Inherited
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
public @interface Duplication {

    DupType dupType() default PARAM_TYPE;

}
