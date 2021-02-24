package com.qintingshuang.base.algotithm.String;

import java.util.Scanner;

/**
 * @author qintingshuang
 * @create 2021-02-24 11:26
 * @description String反转
 * 输入文本 we are a good staff 反转打印出来staff good a are we
 **/
public class stringTrans {


    /**
     * 字符串反转
     * @param str 字符串
     */
    private static void reverseStr(String str) {
        //1.字符串整体反转
        char[] chars = str.toCharArray();
        chars = reverse(chars, 0, str.length() - 1);
        int start = 0;
        //对反转后的字符串遍历，更具是否为空格，进行单词反转，最后要对最后一个单词进行反转
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                reverse(chars, start, i - 1);
                start = i + 1;
            }
            if (i == chars.length - 1) {
                reverse(chars, start, i);
            }
        }
        System.err.println(String.valueOf(chars));
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        reverseStr(str);
    }


    /**
     * 文本反转
     * @param chars 数组
     * @param start 开始反转的索引
     * @param end   结束反转的索引
     * @return
     */
    private static char[] reverse(char[] chars, int start, int end) {
        int i = start;
        int j = end;
        while (i < j) {
            char temp;
            temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }
        return chars;
    }
}
