package lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author qintingshuang
 * @create 2020-04-18 15:05
 * @description lambda与stream结合起来使用
 **/
@Slf4j
public class StreamLambda {


    @Data
    @AllArgsConstructor
    public class Person {
        private String firstName, lastName, job, gender;
        private Integer age, salary;

    }


    /**
     * 使用生成匿名内部内进行初始化
     * 使用了匿名内部类，但是这个匿名内部类ArrayList没有重写任何方法,但是加入了一个初始化代码块，并且调用父类的add方法
     */
    List<Person> javaProgrammers = new ArrayList<Person>() {
        {
            add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
            add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
            add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
            add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
            add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
            add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
            add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
            add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
            add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
            add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
        }
    };

    List<Person> phpProgrammers = new ArrayList<Person>() {
        {
            add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
            add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
            add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));
            add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
            add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
            add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
            add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
            add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
            add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
            add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
        }
    };


    /**
     * 对list的实体类，进行批量的操作，场景： 对list中的每一个实体类做操作（这样写的更优雅）
     */
    @Test
    public void foreachProgrammer() {
        javaProgrammers.forEach((p) -> log.info(p.firstName));
        System.out.println("------------------------------------------------------------------");
        //对每一个程序员加薪 运用 Consumer
        Consumer<Person> giveRaise = e -> e.setSalary(e.getSalary() / 100 * 5 + e.getSalary());
        phpProgrammers.forEach(giveRaise);
        phpProgrammers.forEach((p) -> log.info(p.getSalary().toString()));
    }





}
