package com.qintingshuang.base.thread.pool;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.*;

/**
 * @author: qintingshuang
 * @date: 2019/11/14 23:04
 * @description: 线程池示例
 * 包括流程图
 * 线程池是如何运转的
 */

@EnableAsync
@Configuration
public class ThreadPoolExecutorDemo implements AsyncConfigurer {


    /**
     * 核心线程数
     */
    private static final int CORE_THREAD_NUM = 5;

    /**
     * 最大线程数
     */
    private static final int MAX_THREAD_NUM = 10;

    /**
     * 最大线程数
     */
    private static final int QUEUE_LENGTH = 1000;

    /**
     * 线程超时时间
     */
    private static final int TIMEOUT_NUM = 60;

    /**
     * 线程池
     */
    public static ThreadPoolExecutor threadPool;


    /**
     * 延时调度池
     */
    public static ScheduledThreadPoolExecutor scheduleExecutor;

    static {
        threadPool = new ThreadPoolExecutor(
                CORE_THREAD_NUM,
                MAX_THREAD_NUM,
                TIMEOUT_NUM,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue(QUEUE_LENGTH),
                new ThreadPoolExecutor.AbortPolicy()
        );

        scheduleExecutor=new ScheduledThreadPoolExecutor(2,
                new ThreadPoolExecutor.DiscardPolicy());
    }


    @Override
    public Executor getAsyncExecutor() {
        return threadPool;
    }




    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> {
            ex.printStackTrace();
        };
    }
}


