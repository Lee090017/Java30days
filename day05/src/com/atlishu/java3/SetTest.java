package com.atlishu.java3;

import org.junit.Test;

import java.util.*;

/**
 * @author lishustart
 * @create 2021-03-30-15:36
 *
 * 1.Set接口的框架：
 * /----Set接口：存储无序的，不可重复的数据。---->数学中的“集合”（常见处理过滤问题）
 *       /----HashSet：作为Set接口的主要实现类，可以储存null值
 *       /----LinkedHashSet：作为HashSet的子类出现的，遍历其内部数据时，可以按照添加的顺序遍历
 *       /----TreeSet：可以按照添加对象的指定属性，进行排序
 *
 *
 */
public class SetTest {
    /*
    一.Set：存储无序的、不可重复的数据
        1.无序性：不等于随机性，存储的数据在底层数组中并非按照数组索引的顺序添加而是根据数据的
          hash值决定的。

        2.不可重复性：保证添加的元素按照equals()判断时，不能返回true，即：相同的元素只能添加一个

    二.添加元素的过程，以HashSet为例



     */
    @Test
    public void test1(){
        Set set = new HashSet();
        set.add(1);
        set.add(2);
        set.add("AA");
        set.add("BB");
        set.add(new Student("Tom",14));
        set.add(new Student("Tom",14));//要重写hashCode()才会执行equals()方法
        set.add(2);//不重复性

        Iterator iterator = set.iterator();

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

    @Test
    public void test2(){
        Set set = new LinkedHashSet();
        set.add(1);
        set.add(2);
        set.add("AA");
        set.add("BB");
        set.add(new Student("Tom",14));
        set.add(new Student("Tom",14));//要重写hashCode()才会执行equals()方法
        set.add(2);//不重复性

        Iterator iterator = set.iterator();

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

}

class Student {
    private String name;
    private int age;

    public Student(String name,int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age &&
                Objects.equals(name, student.name);
    }

    //假如不重写的话，实际object类中的hash值是随机计算的值，所以就算连个对象的属性相同，其hash值的计算也不同
    @Override
    public int hashCode() {//return name.hashCode() + age;
        return Objects.hash(name, age);
    }
}