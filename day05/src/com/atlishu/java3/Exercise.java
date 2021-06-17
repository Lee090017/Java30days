package com.atlishu.java3;

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
    public void test1(){
        Set set = new TreeSet();
        set.add(new Employee("lishu",25,new Mydate(1996,11,9)));
        set.add(new Employee("yanming",27,new Mydate(1994,12,3)));
        set.add(new Employee("tangyige",26,new Mydate(1995,3,26)));
        set.add(new Employee("pengdaoming",24,new Mydate(1997,11,15)));

        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test2(){
        Comparator comparator = new Comparator() {
            @Override
            //出生日期的先后顺序
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Employee && o2 instanceof Employee){
                    Employee e1 = (Employee) o1;
                    Employee e2 = (Employee) o2;
                    //方法一
//                    if(e1.getBirthday().getYear() > e2.getBirthday().getYear()){
//                        return 1;
//                    }else if(e1.getBirthday().getYear() < e2.getBirthday().getYear()){
//                        return -1;
//                    }else if(e1.getBirthday().getMonth() > e2.getBirthday().getMonth()){
//                        return 1;
//                    }else if(e1.getBirthday().getMonth() < e2.getBirthday().getMonth()){
//                        return -1;
//                    }else if(e1.getBirthday().getDay() > e2.getBirthday().getDay()){
//                        return 1;
//                    }else if(e1.getBirthday().getDay() < e2.getBirthday().getDay()){
//                        return -1;
//                    }else{
//                        return 0;
//                    }
                    //方法二
                    return e1.getBirthday().compareTo(e2.getBirthday());
                }
                throw new RuntimeException("输入数据格式错误");
            }
        };

        Set set = new TreeSet(comparator);
        set.add(new Employee("lishu",25,new Mydate(1996,11,9)));
        set.add(new Employee("yanming",27,new Mydate(1994,12,3)));
        set.add(new Employee("tangyige",26,new Mydate(1995,3,26)));
        set.add(new Employee("pengdaoming",24,new Mydate(1997,11,15)));

        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test3(){
        //面试题
        HashSet set = new HashSet();
        Person p1 = new Person("AA",11);
        Person p2 = new Person("BB",12);

        set.add(p1);
        set.add(p2);
        System.out.println(set);//2

        p1.setName("CC");
        set.remove(p1);//要先判断该值在不在，计算的hash值使原本AA与11的值
        System.out.println(set);//2
        set.add(new Person("CC",11));
        System.out.println(set);//3
        set.add(new Person("AA",11));
        System.out.println(set);//4
    }
}

class Employee implements Comparable{
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
    public int compareTo(Object o) {
        if(o instanceof Employee){
            Employee e = (Employee) o;
            return this.name.compareTo(e.name);
        }

        throw new RuntimeException("输入数据格式错误");
    }
}

class Mydate implements Comparable{
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
    public int compareTo(Object o) {
        if(o instanceof Mydate){
            Mydate m = (Mydate)o;
            if(this.getYear()>m.getYear()){
                return 1;
            }else if(this.getYear()<m.getYear()){
                return -1;
            }else if(this.getMonth()>m.getMonth()){
                return 1;
            }else if(this.getMonth()<m.getMonth()){
                return -1;
            }else if(this.getDay()>m.getDay()){
                return 1;
            }if(this.getDay()<m.getDay()){
                return -1;
            }else{
                return 0;
            }
        }
        throw new RuntimeException("输入数据格式错误");
    }
}