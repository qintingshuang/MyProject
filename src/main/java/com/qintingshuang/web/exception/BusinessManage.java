package com.qintingshuang.web.exception;

import com.qintingshuang.web.enums.ErrorEnum;

import static com.qintingshuang.base.thread.pool.ThreadPoolExecutorDemo.threadPool;

/**
 * @author qintingshuang
 * @create 2021-04-08 15:50
 * @description 业务管理
 **/
public class BusinessManage {

    /**
     * 业务异常触发
     * @param predicate
     * @param errorEnum
     */
    public static void invalidIf(boolean predicate, ErrorEnum errorEnum) {
        if (predicate) {
            throw new BusinessException(errorEnum);
        }
    }

    /**
     * 业务异常触发 异步执行任务
     * @param predicate
     * @param errorEnum
     * @param task
     */
    public static  void  invalidIf(boolean predicate, ErrorEnum errorEnum,Runnable task){
        if (predicate) {
            threadPool.execute(task);
            throw new BusinessException(errorEnum);
        }
    }
}
