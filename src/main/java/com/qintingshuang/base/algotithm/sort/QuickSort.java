package com.qintingshuang.base.algotithm.sort;

import java.util.Arrays;

/**
 * @author qintingshuang
 * @create 2020-09-08 15:21
 * @description 快速排序
 * 首先随机的选出一个基准，然后把比这个基准大的数字放到右边，比这个基准小的数字放到左边，
 * 然后左边再选择一个基准重复上面的动作，右边也是；
 *
 *
 *
 **/
public class QuickSort {


    public static void main(String[] args) {

        int[] arr = {49, 38, 65, 97, 23, 22, 76, 1, 5, 8, 2, 0, -1, 22};

        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后：");
        for (int i : arr) {
            System.out.println(i);
        }
    }

    private static void quickSort(int[] arr, int low, int high) {
        //直到low=high时结束递归
        if (low < high) {
            // 找寻基准数据的正确索引
            int index = getIndex(arr, low, high);
            quickSort(arr, low, index - 1);
            quickSort(arr, index + 1, high);
        }

    }


    public static int getIndex(int[] arr, int low, int high) {

        int temp = arr[low];
        while (low < high) {

            //右面的数字大于标准数时，右边的数的位置不变，指针向左移一个位置
            while (low < high && arr[high] >= temp) {
                high--;
            }
            //右边的数字小于或等于标准数时，将右边的数放到左边
            arr[low] = arr[high];
            //左边的数字小于或等于标准数时，左边的数据的位置不变，指针向右移一个位置
            while (low < high && arr[low] <= temp) {
                low++;
            }
            //左边的数字大于基本数，将左边的的数字放到右边
            arr[high] = arr[low];
        }
        arr[low] = temp;
        System.out.println(temp);
        System.out.println(Arrays.toString(arr));
        return low;
    }

}
