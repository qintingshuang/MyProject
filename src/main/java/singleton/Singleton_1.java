package singleton;

/**
 * @author: qintingshuang
 * @date: 2019/11/10 12:35
 * @description: 常规饿汉式单例模式 线程安全
 *
 * 也可以把 instance改为private的，然后提供一个public getInstance();
 *
 */
public class Singleton_1 {
    public  final  static  Singleton_1 instance=new Singleton_1();
    private Singleton_1(){};
}
