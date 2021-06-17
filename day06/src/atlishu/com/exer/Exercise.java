package atlishu.com.exer;

import org.junit.Test;

import java.util.*;

/**
 * @author lishustart
 * @create 2021-03-30-21:16
 *
 * 练习题
 * 定义一个Employee类
 */
public class Exercise {
    @Test
    public void test1() {
        Set<Employee> set = new TreeSet<Employee>();
        set.add(new Employee("lishu", 25, new Mydate(1996, 11, 9)));
        set.add(new Employee("yanming", 27, new Mydate(1994, 12, 3)));
        set.add(new Employee("tangyige", 26, new Mydate(1995, 3, 26)));
        set.add(new Employee("pengdaoming", 24, new Mydate(1997, 11, 15)));

        Iterator<Employee> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test2() {
        Comparator<Employee> comparator = new Comparator<>() {
            @Override
            //出生日期的先后顺序
            public int compare(Employee o1, Employee o2) {
                return o1.getBirthday().compareTo(o2.getBirthday());
            }
        };

        Set<Employee> set = new TreeSet<>(comparator);
        set.add(new Employee("lishu", 25, new Mydate(1996, 11, 9)));
        set.add(new Employee("yanming", 27, new Mydate(1994, 12, 3)));
        set.add(new Employee("tangyige", 26, new Mydate(1995, 3, 26)));
        set.add(new Employee("pengdaoming", 24, new Mydate(1997, 11, 15)));

        Iterator<Employee> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

class Employee implements Comparable<Employee>{
    private String name;
    private int age;
    private Mydate birthday;

    public Employee(String name, int age, Mydate birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Mydate getBirthday() {
        return birthday;
    }

    public void setBirthday(Mydate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age &&
                Objects.equals(name, employee.name) &&
                Objects.equals(birthday, employee.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, birthday);
    }

    @Override
    //按名字从小到大排序
    public int compareTo(Employee o) {

            return this.name.compareTo(o.name);

    }
}

class Mydate implements Comparable<Mydate> {
    private int year;
    private int month;
    private int day;

    public Mydate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Mydate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }

    @Override
    public int compareTo(Mydate m) {
            if (this.getYear() > m.getYear()) {
                return 1;
            } else if (this.getYear() < m.getYear()) {
                return -1;
            } else if (this.getMonth() > m.getMonth()) {
                return 1;
            } else if (this.getMonth() < m.getMonth()) {
                return -1;
            } else if (this.getDay() > m.getDay()) {
                return 1;
            }else if (this.getDay() < m.getDay()) {
                return -1;
            } else {
                return 0;
            }
    }
}
