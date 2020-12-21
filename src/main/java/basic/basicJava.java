package basic;

import org.junit.Test;

/**
 * @author qintingshuang
 * @create 2020-10-11 09:58
 * @description 基础java知识
 **/

public class basicJava {


    /**
     * 要出局部变量表和操作栈上说明原理
     */
    @Test
    public void iTest() {

        //先压入了栈顶，然后在局部变量表中进行的 iinc
        int i = 0;
        int a = i++;
        System.err.println(a);
        //局部变量确实加了1
        int b=++i;
        System.err.println(b);
        //先在局部表里中进行的iinc,然后再压入了栈顶
        int j=0;
        int c=++j;
        System.err.println(c);

    }


}
