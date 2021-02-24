package com.qintingshuang.base.collection;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qintingshuang
 * @create 2021-02-22 16:51
 * @description 链表
 **/
@Data
public class QinNode implements Serializable {

    private String  data;

    private  QinNode next;

    public QinNode(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "QinNode{" +
                "data='" + data + '\'' +
                ", next=" + next +
                '}';
    }
}
