package lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

        @Override
        public String toString() {
            return "Person{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", job='" + job + '\'' +
                    ", gender='" + gender + '\'' +
                    ", age=" + age +
                    ", salary=" + salary +
                    '}';
        }
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


    /**
     * 过滤得到大于1300的php程序员
     */
    @Test
    public void filterProgrammer() {
        phpProgrammers.stream().filter(person -> person.getSalary() > 1300)
                .forEach((p) -> log.info(p.getFirstName()));
    }

    Predicate<Person> ageFilter = person -> person.getAge() > 25;
    Predicate<Person> nameFilter = person -> person.getFirstName().length() > 5;

    @Test
    public void filter2Programmer() {
        phpProgrammers.stream().filter(person -> person.getSalary() > 1300)
                .filter(ageFilter)
                .filter(nameFilter)
                .forEach((p) -> log.info(p.getFirstName()));
    }

    /**
     * 过滤后，刷选前三个
     */
    @Test
    public void limitProgrammer() {
        phpProgrammers.stream().filter(person -> person.getSalary() > 1300)
                .limit(3)
                .forEach(person -> log.info(person.getFirstName()));
    }


    /**
     * 排序，转换为 list
     */
    @Test
    public void sortToListProgrammer() {
        List sortProgrammers = phpProgrammers.stream()
                .sorted((p1, p2) -> p2.getSalary() - p1.getSalary())
                .collect(Collectors.toList());
        sortProgrammers.forEach(person -> log.info(person.toString()));
    }

    /**
     * 求待遇最高的那个人
     * 这个min 和max 是被排序影响的，排序从大到小，求第一个
     */
    @Test
    public void maxProgrammer() {
        Person person = phpProgrammers.stream().min((p1, p2) -> p1.getSalary() - p2.getSalary()).get();
        log.info(person.toString());
    }


    /**
     * map映射用法,获取其中的一个字段 重点！！！！！！
     * map  collect 转换为String
     * map  collect 转换为Set
     * map  collect 转换为list
     */
    @Test
    public void mapProgrammer() {
        String phpDeveloper = phpProgrammers.stream().map(Person::getFirstName).collect(Collectors.joining(";"));
        log.info(phpDeveloper);
        Set<String> phpDeveloper1 = phpProgrammers.stream().map(Person::getLastName).collect(Collectors.toSet());
        TreeSet phpDeveloper2 = phpProgrammers.stream().map(Person::getJob).collect(Collectors.toCollection(TreeSet::new));
        List phpDeveloper3 = phpProgrammers.stream().map(Person::getSalary).collect(Collectors.toList());

    }


    /**
     * 并行流式求和
     */
    @Test
    public void parallelProgrammer() {
        Integer totalSalary = javaProgrammers.parallelStream().mapToInt(p -> p.getSalary()).sum();
        log.info(totalSalary.toString());
    }


    /**
     * 统计数据 可以获取该list的 min  max avg sum
     */
    @Test
    public void statisticsProgrammer() {
        IntSummaryStatistics totalSalary = javaProgrammers.stream().mapToInt(p -> p.getAge()).summaryStatistics();
        log.info(totalSalary.toString());
    }

}
