package com.qintingshuang.base.thread.fourMethodThread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: qintingshuang
 * @date: 2019/11/15 21:48
 * @description: 继承Thread类实现多线程
 * 通过多线程实现0-100的相加
 */
@Slf4j
public class ThreadImpl {

    private static int sum = 0;
    private static Object o = new Object();


    public static void main(String[] args) throws InterruptedException {

        ThreadImpl aaa = new ThreadImpl();

        ThreadSum threadSum1 = aaa.new ThreadSum(1, 25);
        ThreadSum threadSum2 = aaa.new ThreadSum(26, 50);
        ThreadSum threadSum3 = aaa.new ThreadSum(51, 75);
        ThreadSum threadSum4 = aaa.new ThreadSum(76, 100);

        threadSum1.start();
        threadSum2.start();
        threadSum3.start();
        threadSum4.start();

        threadSum1.join();
        threadSum2.join();
        threadSum3.join();
        threadSum4.join();

        log.info("最后的sum: " + sum);
    }


    /**
     * 其中Thread类也是继承Runnable接口的，在运用中，可以直接采用Runnable的实现类这种写法
     */
    class ThreadSum extends Thread {

        private int min;
        private int max;

        public ThreadSum(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public void run() {
            synchronized (o) {
                for (int i = min; i <= max; i++) {
                    sum = i + sum;
                    log.info("当前线程名称：" + Thread.currentThread().getName() + "," + "i的值为：" + i + "," + "sum: " + sum);
                }

            }

        }
    }


}
