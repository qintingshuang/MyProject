package singleton;

/**
 * @author: qintingshuang
 * @date: 2019/11/10 12:37
 * @description: 饿汉式 带有static{}，这种应对比较复杂的单例时（外部文件拿值时）使用 线程安全
 */
public class Singleton_2 {

    private final static Singleton_2 instance;
    static {
        instance = new Singleton_2();
    }

    private Singleton_2() {}

    public static Singleton_2 getInstance() {
        return instance;
    }


}
