package com.qintingshuang.base.thread.pool;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static com.qintingshuang.base.thread.pool.ThreadPoolExecutorDemo.scheduleExecutor;
import static com.qintingshuang.base.thread.pool.ThreadPoolExecutorDemo.threadPool;

/**
 * @author qintingshuang
 * @create 2021-04-01 14:43
 * @description 有返回值的线程池
 * 1.知识点：
 * ThreadPoolTaskExecutor和ThreadPoolExecutor区别
 * 其中的ThreadPoolTaskExecutor是spring提供的线程池
 * ThreadPoolExecutor是jdk提供的的线程池（java.util.concurrent中）
 **/
public class PoolExecutorTest {


    @Test
    public void submit() throws ExecutionException, InterruptedException {
        System.err.println(Thread.currentThread().getName());
        Future<Integer> future = threadPool.submit(() -> {
            System.err.println(Thread.currentThread().getName());
            return 11111;
        });
        int aaa = future.get();
        System.err.println(aaa);
    }


    @Test
    public void runnableTest() {
        for (int i = 0; i < 10000; i++) {
            threadPool.execute(() -> {
                System.err.println("线程名称为：" + Thread.currentThread().getName());
            });
        }


        /**
         * 线程池要不要销毁掉？
         * 1.对于操作不是那么频繁的，比如文件下载，网络流媒体，使用线程池的目的也是为了快一些，这种是需要销毁的，否则占用资源
         * 2.对于操作比较频繁的，并发量比较高的，这个就不需要销毁线程池了
         */
        threadPool.shutdown();
    }


    @Test
    public void scheduleTest() throws ExecutionException, InterruptedException {


        Callable cal = () -> 1;
        Runnable runnable = () -> System.out.println("测试");

        /**
         * 只执行一次，有返回值
         */
        int i = (int) scheduleExecutor.schedule(cal, 2, TimeUnit.SECONDS).get();

        /**
         * 只执行一次，无返回值
         */
        scheduleExecutor.schedule(runnable, 2, TimeUnit.SECONDS);


        /**
         *  创建并执行一个周期性操作，该操作在给定的初始延迟后首先启用，随后在给定的时间段内启用;
         *
         *  即执行将在initialDelay之后开始，然后是initialDelay + period，然后是initialDelay + 2 * period，依此类推。
         *
         * 遇到异常，则禁止，
         *2.如果此任务的执行时间超过其周期，则后续执行可能会【延迟】，但不会同时执行。
         */
        scheduleExecutor.scheduleAtFixedRate(runnable, 0, 5, TimeUnit.SECONDS);


        /**
         * 同上也是周期动作。但周期是上一次任务结束和下一次任务开始的时间间隔
         * 如果任务的任何执行遇到异常，则后续执行被禁止。 否则，任务将仅通过取消或终止执行者来终止
         */
        scheduleExecutor.scheduleWithFixedDelay(runnable,2,10,TimeUnit.SECONDS);

    }

}
