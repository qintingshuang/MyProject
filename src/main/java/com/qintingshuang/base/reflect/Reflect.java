package com.qintingshuang.base.reflect;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * @author: qintingshuang
 * @date: 2019/11/9 12:17
 * @description: 利用反射，
 * 什么叫反射，反射就是程序运行中，动态的获取、使用类的属性、方法，实例化该类。
 *
 * 其中最重要的是setAccessible属性，无视修饰符，自带BGM出场的选手。
 *
 *
 * 1.创建person对象,并赋值age为“23岁”
 * 2.获取字段hobby的值，
 * 3.通过setName给Name赋值“Kevin”,并且通过getName拿到Name的值,打印出来
 */
@Slf4j
public class Reflect {


    /**
     * 比较类信息是否是一个
     */
    private static void EquealClass() {
        Person person = new Person();
        //通过对象获取类
        Class clazz = person.getClass();
        //直接获取类
        Class class2 = Person.class;
        //相同的原因，是类的信息是存在元空间里面的（方法区），取得的同一个信息
        System.out.println("clazz的hashcode为: " + clazz.hashCode());
        System.out.println("clazz2的hashcode为: " + class2.hashCode());
    }

    /**
     * 运用反射，创建对象
     *
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    private static void reflectConstruct() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //加载类，返回class对象
        Class clazz = Class.forName("com.qintingshuang.base.reflect.Person");
        //通过Class动态的创建对象
        Object object = clazz.newInstance();

        //声明构造函数,如果没有参数，可以不填参数
        Constructor c1 = clazz.getDeclaredConstructor(Integer.class);
        //赋予可以访问的权限
        c1.setAccessible(true);
        //实例化该对象
        Object object2 = c1.newInstance(23);

        //这个也是破坏单例模式的利器
//        Constructor cc=   clazz.getDeclaredConstructor();
//        cc.setAccessible(true);
    }

    /**
     * 通过反射，获取字段hobby的值
     *
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws NoSuchFieldException
     */
    private static void reflectField() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class clazz = Class.forName("com.qintingshuang.base.reflect.Person");
        //实例化对象
        Constructor constructor = clazz.getDeclaredConstructor(Integer.class);
        constructor.setAccessible(true);
        Object obj = constructor.newInstance(23);
        //获取私有字段的值
        Field field = clazz.getDeclaredField("hobby");
        field.setAccessible(true);
        Object objectField = field.get(obj);
        log.info(objectField.toString());
    }

    private static void reflectMethod() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        //创建对象
        Class clazz = Class.forName("com.qintingshuang.base.reflect.Person");
        Constructor constructor = clazz.getDeclaredConstructor(Integer.class);
        constructor.setAccessible(true);
        Object object = constructor.newInstance(23);
        //获取这个setName方法
        Method m = clazz.getDeclaredMethod("setName", String.class);
        m.setAccessible(true);
        //调用这个方法 invoke调用
        m.invoke(object, "kevin");
        //获取到getName方法
        Method n = clazz.getDeclaredMethod("getName");
        n.setAccessible(true);
        //调用这个方法
        Object obj = n.invoke(object);
        log.info("获取的字段name为：" + obj.toString());
    }


    public static void main(String[] args) {
        // EquealClass();
        try {
            //  reflectConstruct();
            // reflectField();
            reflectMethod();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();


        }
    }


}
