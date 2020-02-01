package singleton;

/**
 * @author: qintingshuang
 * @date: 2019/11/10 12:43
 * @description: 懒汉式 静态内部类 线程安全
 */
public class Singleton_3 {

    private Singleton_3() {
    }

    /**
     * 静态内部类，只有在使用的时候才能加载
     */
    private static class innerSingleton {
        private final static Singleton_3 instance = new Singleton_3();
    }


    public static Singleton_3 getInstance() {
        return innerSingleton.instance;
    }

}
