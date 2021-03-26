package com.qintingshuang.base.collection.generics;

/**
 *
 * 泛型，是1.5之后出现的一种安全机制，它将运行出现的ClassCastException异常,转移到了编译期，方便寻找问题。
 *
 * 也避免强制转换问题
 *
 * 泛型类
 * @author qintingshuang
 * @date 2020/2/1 10:27
 */
public class GenericsClass {

    public  static void main(String[]  args){
        Tool tool=new Tool();
        tool.setPerson(new Student());
        tool.getPerson();

        Tool tool1=new Tool();
        tool1.setPerson(new Teacher());
        tool1.getPerson();
    }
}



class Tool<T>{

    private  T person;

    public void setPerson(T person){
        this.person=person;
    }
    public T getPerson(){
        return  person;
    }
}

class Student{}

class Teacher{};
