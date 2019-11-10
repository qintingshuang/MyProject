package singleton;

/**
 * @author: qintingshuang
 * @date: 2019/11/10 15:07
 * @description:懒汉模式 volitile  sychronize 运用
 */
public class Singleton_5 {

    private Singleton_5() {
    }

    private static Singleton_5 instance;

    public static Singleton_5 getInstance() throws InterruptedException {
        //提高性能
        if (instance == null) {
            //这个已经可以解决多线程不安全的问题
            synchronized (Singleton_5.class) {
                if (instance == null) {
                    Thread.sleep(100);
                    instance = new Singleton_5();
                }
            }
        }
        return instance;
    }


    /**
     *
     * sychronized关键值的运用
     *
     * 同步锁，增加一个锁对象，相当于锁住这个线程，加了一个监视器
     *
     * 具有独占性和可见性
     *
     *
     */



}
