package com.qintingshuang.base.thread.fourMethodThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author qintingshuang
 * @create 2021-03-22 15:35
 * @description 1.8之后新的同步非阻塞写法
 **/
public class CompletableFutureTest {

    static String str = "业务实体1";

    public static void main(String[] args) {

        System.err.println("开始" + str);

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.err.println("当前线程" + Thread.currentThread().getName());
            return bussinessInt(str);
        });
        CompletableFuture<List<Integer>> future2 = CompletableFuture.supplyAsync(() -> {
            System.err.println("当前线程" + Thread.currentThread().getName());
            return bussinessInt2("业务实体2");
        });
        Integer join1 = future1.join();
        List<Integer> join2 = future2.join();
        System.err.println(join1+join2.toString());
    }


    private static int bussinessInt(String a) {
        System.err.println("执行业务代码" + a);

        System.err.println(Thread.currentThread().getName() + "睡眠了1000ms");
        return 1000;
    }


    private static List<Integer> bussinessInt2(String a) {
        System.err.println("执行业务代码" + a);
        System.err.println(Thread.currentThread().getName() + "睡眠了2000ms");
        return new ArrayList<Integer>() {{
            add(20);
        }};
    }


}
