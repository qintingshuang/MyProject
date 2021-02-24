package com.qintingshuang.base.collection;


import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author qintingshuang
 * @create 2021-02-22 13:52
 * @description list的相关面试代码
 **/
public class ListCollection {

    ArrayList<String> array = new ArrayList();


    LinkedList<String> linkedList = new LinkedList<>();


    private static void nodeTest() {
        //节点赋值
        QinNode root = new QinNode("火车头");
        QinNode n1 = new QinNode("车厢A");
        QinNode n2 = new QinNode("车厢B");
        QinNode n3 = new QinNode("车厢C");
        //指针
        root.setNext(n1);
        n1.setNext(n2);
        n2.setNext(n3);
        //循环获取node数据
        QinNode nodeOut = root;
        while (nodeOut != null) {
            System.err.println(nodeOut.toString());
            nodeOut = nodeOut.getNext();
        }
    }


    public  static void main(String[] args){
        nodeTest();
    }

}
