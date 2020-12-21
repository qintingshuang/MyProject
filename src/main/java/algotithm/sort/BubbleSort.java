package algotithm.sort;

/**
 * 面试常见几种排序
 * https://blog.csdn.net/chengxian6723/article/details/100838248
 * 基本上，这几个就够吹了
 * <p>
 * 冒泡 ok
 * 快速 ok
 * 选择
 * 插入
 * 二分查找
 * 归并
 */

/**
 * @author: qintingshuang
 * @date: 2019/11/9 12:41
 * @description: 冒泡排序
 * 冒泡排序的理解：相邻的两个值进行比对，如果比它大就交换位置，每一个相邻的都比较，
 * 循环n次（n为数据的长度），就完成冒泡排序了
 *
 */
public class BubbleSort {


    public void sort(int[] n) throws InterruptedException {
        // 表示第几次循环
        for (int i = 1; i < n.length; i++) {
            //表示该次循环进行了几次比较
            for (int j = 0; j < n.length - i; j++) {
                if (n[j] > n[j + 1]) {
                    int temp = n[j];
                    n[j] = n[j + 1];
                    n[j + 1] = temp;
                }
            }
            print(n);
        }



    }
    public void print(int[] n) throws InterruptedException {
        for(int i=0;i<n.length;i++) {
            // 输出排序的数据
            System.err.print(n[i] + "\t");
        }
        Thread.sleep(200);
        System.out.println();
    }


    public  static void  main(String[] args) throws InterruptedException {
        BubbleSort s=new BubbleSort();
        int[]  n={100,60,80,90,75,38,19};
        s.sort(n);
        s.print(n);
    }

}
