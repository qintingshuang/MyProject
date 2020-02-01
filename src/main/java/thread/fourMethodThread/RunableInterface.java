package thread.fourMethodThread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: qintingshuang
 * @date: 2019/11/19 10:03
 * @description:
 */
@Slf4j
public class RunableInterface implements  Runnable {


    private  String  name;
    private  Integer age;

    public RunableInterface(String name, Integer age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public void run() {
        log.info(this.name+"开启了一个线程"+"他的年纪并不大，只有"+this.age+"岁");
    }

}
