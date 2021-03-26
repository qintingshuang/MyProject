package com.qintingshuang.base.collection.generics;

/**
 * @author qintingshuang
 * @date 2020/2/1 10:26
 */
public class GenericsMethod<E> {


    /**
     * 泛型作用在方法上，以方法为最小单位，类上的泛型、其他方法的泛型影响不到他
     * 其中泛型是参数化类型，指的的是 参数的类型，所以返回类型是指定的，泛型和返回类型没有关系
     *
     * @param dto
     * @param <T>
     */
    private <T> void setMethod(T dto) {
        System.out.println(dto.toString());
    }

    private <T> String getStr(T dto) {
        System.out.println(dto.toString());
        String aaa = "hhhhhhhh";
        return aaa;
    }


    /**
     * 这个方法是使用的类上的泛型，不是方法定义的泛型
     * @param dto
     */
    private void setTest(E dto) {
        System.out.println(dto);
    }


    /**
     *
     * 静态方法不能用 类 上的定义的泛型，如果要使用泛型需要在方法上定义泛型
     * @param dto
     * @param <T>
     * @return
     */
    public  static <T> String getDto(T dto){
        System.out.println(dto.toString());
        return dto.toString();
    }

//    错误写法
//    private  static string  getDtoErr(E dto){
//    }


    public static void main(String[] args) {
        GenericsMethod<Integer> genericsMethod = new GenericsMethod<Integer>();
        genericsMethod.setMethod("方法泛型");
        genericsMethod.getStr(888);
        genericsMethod.setTest(100);
        GenericsMethod.getDto("静态泛型定义");
    }

}
