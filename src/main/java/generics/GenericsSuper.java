package generics;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author qintingshuang
 * @date 2020/2/1 20:57
 */
public class GenericsSuper {


    public static void main(String[] args) {
        ArrayList<Person> personArrayList = new ArrayList<Person>();
        personArrayList.add(new Person("zhangsan"));
        personArrayList.add(new Person("kevin"));
        personArrayList.add(new Person("json"));
        getStr3(personArrayList);

        ArrayList<StudenEx> studentExes = new ArrayList<StudenEx>();
        studentExes.add(new StudenEx("jill"));
        studentExes.add(new StudenEx("kevin"));
        studentExes.add(new StudenEx("json"));
        getStr2(studentExes);
    }


    /**
     * ?可以当做一个通配符，当不知道什么类型
     *
     * @param person
     */
    private static void getStr(ArrayList<?> person) {
        Iterator<?> iterator = person.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
    }


    /**
     * 当然这个？的通配符替换为T，区别是T是具体类型，可以使用（E为指定类型）
     *
     * @param person
     * @param <T>
     */
    private static <T> void getStr1(ArrayList<T> person) {
        Iterator<?> iterator = person.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
    }

    /**
     * 参数类型只能是Person子类和Person类型，其他的不行   这种是上限
     *
     * @param person
     */
    private static void getStr2(ArrayList<? extends Person> person) {
        Iterator<? extends Person> iterator = person.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
    }

    /**
     * 参数化类型只能是StudentEx的父类和StudentEx本身，其他的不行，这种是下限
     * @param student
     */
    private static void getStr3(ArrayList<? super StudenEx> student) {
        Iterator<? super StudenEx> iterator = student.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
    }
}


class StudenEx extends Person {
    private  String   student;

    public StudenEx(String name) {
        super(name);
        this.student=name;
    }

    @Override
    public String toString() {
        return "StudenEx{" +
                "student='" + student + '\'' +
                '}';
    }
}

class Person {
    private String Name;

    public Person(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "Name='" + Name + '\'' +
                '}';
    }
}


