package com.qintingshuang.web.aop;

import com.qintingshuang.base.thread.lock.RedisLock;
import com.qintingshuang.web.enums.DupType;
import com.qintingshuang.web.exception.BusinessManage;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.client.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;

import static com.qintingshuang.web.enums.ErrorEnum.DUPLICATE_EXCEPTION;

/**
 * @author qintingshuang
 * @create 2021-04-12 15:34
 * @description 羃等切面
 **/
@Slf4j
@Aspect
@Configuration
public class DuplicationAspect {

    @Autowired
    RedisLock redisLock;

    /**
     * 切点
     */
    @Pointcut(value = "@annotation(com.qintingshuang.web.aop.Duplication)")
    public void dupAnnotation() {
    }

    /**
     * @param joinPoint
     */
    @Before(value = "dupAnnotation()")
    public void deBefore(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String args = Arrays.toString(joinPoint.getArgs());
        String key = "method:" + method.getName() + ":" + args;
        Duplication duplication = method.getAnnotation(Duplication.class);
        DupType dup = duplication.dupType();
        System.err.println("注解中dupType()方法名称为：" + dup.name());
        String requestId = UUID.randomUUID().toString();
        if (redisLock.tryLock(key, requestId, 1)) {
            BusinessManage.invalidIf(true, DUPLICATE_EXCEPTION);
        }
        ;
    }


}
