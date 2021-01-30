package thread.pool;

import java.util.concurrent.*;

/**
 * @author: qintingshuang
 * @date: 2019/11/14 23:04
 * @description: 线程池示例
 * 包括流程图
 * 线程池是如何运转的
 */
public class ThreadPoolExecutorDemo {


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
    private static final int QUEUE_LENGTH = 100;

    /**
     * 线程超时时间
     */
    private static final int TIMEOUT_NUM = 60;

    /**
     * 线程池
     */
    public static ThreadPoolExecutor threadPool;

    static {
        threadPool = new ThreadPoolExecutor(
                CORE_THREAD_NUM,
                MAX_THREAD_NUM,
                TIMEOUT_NUM,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue(QUEUE_LENGTH),
                new ThreadPoolExecutor.AbortPolicy()
        );

    }

    public static void main(String[] args) {
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


}


