package com.qintingshuang.base.basic;

/**
 * @author qintingshuang
 * @create 2020-12-28 11:16
 * @description 接口测试
 **/
public interface InterfaceTest {


    /**
     * 1.8后可以使用全局变量，String类型，不加final与static
     */
    String outTest = "normal_test";

    final String finalTest = "final";

    static String statusString = "static";

    public final static String finalStaticString = "";


    /**
     * 1.8以后，可能使用default修饰符定义方法,默认实现
     */
    default void testDefalutImpl() {
        String test = "test";
        System.err.println(outTest);
    }

    /**
     * 1.8以后，也可以使用static
     *
     * @return
     */
    static String testDefalutStr() {
        String test = "test";
        System.err.println(test);
        return test;
    }


    public void test();

}


class ImplTest implements InterfaceTest {

    @Override
    public void test()
    {

        /**
         * 线程安全
         */
        StringBuffer buffer=new StringBuffer();

        /**
         * 线程不安全
         */
        StringBuilder builder=new StringBuilder();
        System.err.println("AAAA");
    }


    public static void main(String[] args)
    {
        ImplTest implTest=new ImplTest();
        implTest.test();
        implTest.testDefalutImpl();
        InterfaceTest.testDefalutStr();
    }
}


