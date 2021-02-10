package com.qintingshuang.base.algotithm.String;

import java.util.HashSet;

/**
 * @author qintingshuang
 * @create 2020-12-20 14:31
 * @description 回文相关的算法
 * 1.给定一个字符串，验证他是不是为回文，只考虑字母和数字，可以忽略字母的大小写，空字符串定义为有效的字符串
 * <p>
 * 2.给定一个包含大写字母与小写字母的字符串，找到通过这些字母构成的最长的回文串的长度，构造的字符串区别大小写
 **/
public class Symmetrical {


    /**
     * 1.给定一个字符串，验证他是不是为回文，只考虑字母和数字，可以忽略字母的大小写，空字符串定义为有效的字符串
     *
     * @param s
     * @return
     */
    private static boolean isPalindrome(String s) {
        // 如果为空时
        if (s.length() == 0) {
            return true;
        }
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            //如果不是字母或者数字就l进一位
            if (!Character.isLetterOrDigit(s.charAt(l))) {
                l++;
            } else if (!Character.isLetterOrDigit(s.charAt(r))) {
                //如果不是字母或者数字就r就减一位
                r--;
            } else {
                //对比是否相等，如果不相等就返回false
                if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
                    return false;
                }
            }
            l++;
            r--;
        }
        return true;
    }


    /**
     *  2.给定一个包含大写字母与小写字母的字符串，找到通过这些字母构成的最长的回文串的长度，构造的字符串区别大小写
     * @param a
     * @return
     */
    private static int longestPalindrome(String a) {
        if (a.length() == 0) {
            return 0;
        }
        HashSet<Character> hashSet = new HashSet<>();
        char[] chars = a.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (!hashSet.contains(a.charAt(i))) {
                hashSet.add(a.charAt(i));
            } else {
                hashSet.remove(a.charAt(i));
                count++;
            }
        }
        return hashSet.isEmpty() ? count * 2 : count * 2 + 1;
    }


    public static void main(String[] args) {
        String a = "noon";
        System.err.println(longestPalindrome(a));


//        String a="level";


    }
}
