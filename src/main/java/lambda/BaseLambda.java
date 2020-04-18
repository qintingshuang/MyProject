package lambda;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author qintingshuang
 * @create 2020-04-18 14:04
 * @description 基本的lamdba例子
 **/
@Slf4j
public class BaseLambda {


    String[] atp = {"Rafael Nadal", "Novak Djokovic",
            "Stanislas Wawrinka",
            "David Ferrer", "Roger Federer",
            "Andy Murray", "Tomas Berdych",
            "Juan Martin Del Potro"};
    List<String> players = Arrays.asList(atp);

    /**
     * foreach 循环 示例
     */
    @Test
    public void baselLambda() {

        players.forEach((player) -> System.out.println(player));
        System.out.println("-----------------------------------------");
        players.forEach(System.out::print);
    }

    /**
     * lambda  实现runnable 接口
     */
    @Test
    public void lambdaRunnable() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("test inner class Thread");
            }
        }).start();

        new Thread(() -> log.info("test lambda")).start();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log.info("test inner class runnable");
            }
        };
        runnable.run();
        // 记住这种 用lambda表达式代替匿名内部类  （前提：接口只有一个重写的可执行方法时）
        Runnable runnable1 = () -> log.info("test lambda runnable");
        runnable1.run();
    }

    /**
     * lambda 排序
     */
    @Test
    public void  lambdaSort(){

       // 根据name排序 new Comparators
        Arrays.sort(atp, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1.compareTo(o2));
            }
        });
        Arrays.asList(atp).forEach((player)->log.info(player));
        System.out.println("---------------------------------------------------");
        Arrays.sort(atp,(String o1,String o2) ->o1.compareTo(o2));
        Arrays.asList(atp).forEach((player)-> log.info(player));
        System.out.println("---------------------------------------------------");
        Arrays.sort(atp,(String o1,String o2) ->o1.length()-o2.length());
        Arrays.asList(atp).forEach((player)-> log.info(player));
        System.out.println("---------------------------------------------------");
        Arrays.sort(atp,(String o1,String o2) ->o1.substring(o1.indexOf(" ")).compareTo(o2.substring(o2.indexOf(" "))));
        Arrays.asList(atp).forEach((player)-> log.info(player));

    }


}
