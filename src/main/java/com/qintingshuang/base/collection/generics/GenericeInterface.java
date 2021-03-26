package com.qintingshuang.base.collection.generics;

/**
 * @author qintingshuang
 * @date 2020/2/1 22:08
 */
public interface GenericeInterface<T> {

    void getGenerice(T ado);
}


class GenericesImpl implements GenericeInterface<String> {
    @Override
    public void getGenerice(String ado) {
        System.out.println(ado);
    }

    public static void main(String[] args) {
        GenericeInterface generice = new GenericesImpl();
        generice.getGenerice("实现了接口");

        GenericesImpl1<Integer> genericesImpl1=new GenericesImpl1<Integer>();
        genericesImpl1.getGenerice(15);

    }

}

/**
 * 这个是实现类也不知道用什么类型，在主函数时，再定义类型
 * @param <T>
 */
class GenericesImpl1<T> implements GenericeInterface<T>{
    @Override
    public void getGenerice(T ado) {
        System.out.println(ado.toString());
    }
}