package com.qintingshuang.base.thread.pool;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author qintingshuang
 * @create 2021-04-01 14:43
 * @description 有返回值的线程池
 * 1.知识点：
 * ThreadPoolTaskExecutor和ThreadPoolExecutor区别
 * 其中的ThreadPoolTaskExecutor是spring提供的线程池
 * ThreadPoolExecutor是jdk提供的的线程池（java.util.concurrent中）
 **/
public class ReturnPoolExecutor {


    @Test
    public void submit() throws ExecutionException, InterruptedException {
        System.err.println(Thread.currentThread().getName());
        Future<Integer> future = ThreadPoolExecutorDemo.threadPool.submit(() -> {
            System.err.println(Thread.currentThread().getName());
            return 11111;
        });
        int aaa = future.get();
        System.err.println(aaa);
    }


}
