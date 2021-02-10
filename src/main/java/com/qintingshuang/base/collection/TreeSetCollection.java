package com.qintingshuang.base.collection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * 字母长度排序
 *
 * @author qintingshuang
 * @date 2020/2/1 12:11
 */
public class TreeSetCollection {

    public static void main(String[] args) {
        TreeSet treeSet = new TreeSet(new StrLenCompareTo());
        treeSet.add("bc");
        treeSet.add("dahisd");
        treeSet.add("tres");
        treeSet.add("ccc");
        treeSet.add("duhai");
        treeSet.add("c");
        treeSet.add("bbb");
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

}

/**
 * 重写比较
 */
class StrLenCompareTo implements Comparator {

    /**
     * 这个重点是比较后的返回值,主函数要使用这个compare方法，得出排序结果
     *
     * @param s1
     * @param s2
     * @return
     */
    @Override
    public int compare(Object s1, Object s2) {
        String str1 = (String) s1;
        String str2 = (String) s2;
        int num = new Integer(str1.length()).compareTo(new Integer(str2.length()));
        if (num == 0) {
            return str1.compareTo(str2);
        }
        return num;
    }


}