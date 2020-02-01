package singleton;

/**
 * @author: qintingshuang
 * @date: 2019/11/9 12:26
 * @description: 枚举单例最大的优点是避免反射攻击 线程安全
 *  因为在 反射的源码中,有这么一句
 *  if ((clazz.getModifiers() & Modifier.ENUM) != 0)
 *             throw new IllegalArgumentException("Cannot reflectively create enum objects");
 * 不能实例化枚举类型
 */
public enum Singleton_enum {
    /**
     * 加载时，会创建类型为EnumSingleton的实例对象QINTINGSHUANG
     *
     */
    QINTINGSHUANG


/**
 * 枚举的原理
 *
 * 枚举实际上是个特殊类，继承了Enum类，具有final修饰符，其中可普通类最大的区别是：
 * 类可以想实例化几个，就实例化几个
 * 枚举的实例对象，只是在这个enum中定义好的这几个，它的实例是有限的
 *Java 枚举实现原理
 *https://blog.csdn.net/xunzaosiyecao/article/details/79349132
 *
 * 通过 jad反编译这个EnumSingleton.calss,得出的代码如下：
 * 可以看到在类加载时，进行了实例化
 *
 *
 * // Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
 * // Jad home page: http://kpdus.tripod.com/jad.html
 * // Decompiler options: packimports(3)
 * // Source File Name:   Singleton_enum.java
 *
 * package singleton;
 *
 *
 * public final class Singleton_enum extends Enum
 * {
 *
 *     public static Singleton_enum[] values()
 *     {
 *         return (Singleton_enum[])$VALUES.clone();
 *     }
 *
 *     public static Singleton_enum valueOf(String name)
 *     {
 *         return (Singleton_enum)Enum.valueOf(singleton/Singleton_enum, name);
 *     }
 *
 *     private Singleton_enum(String s, int i)
 *     {
 *         super(s, i);
 *     }
 *
 *     public static final Singleton_enum QINTINGSHUANG;
 *     private static final Singleton_enum $VALUES[];
 *
 *     static
 *     {
 *         QINTINGSHUANG = new Singleton_enum("QINTINGSHUANG", 0);
 *         $VALUES = (new Singleton_enum[] {
 *             QINTINGSHUANG
 *         });
 *     }
 * }
 *
 *
 *
 *
 *
 *
 *
 *
 */
}
