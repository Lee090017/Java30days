package com.atlishu.java2;

import org.junit.Test;

import java.util.*;

/**
 * @author lishustart
 * @create 2021-03-14-15:46
 *
 * 一、集合框架的概述
 * 1.集合和数组都是对多个数据进行存储操作（java容器）
 *      说明：此时的存储还是内存层面的存储，不涉及持久化的存储（.txt/.jpg/.avi）
 * 2.数组在存储多个数据层面的特点：
 *      ①一旦初始化，其长度确定
 *      ②数组一旦定义好之后，其元素的类型也就确定了，比如String[]，我们只能操作指定类型的数据了
 *      缺点：
 *      ①一旦初始化之后长度不能修改
 *      ②数组中提供的方法非常有限，对于添加、删除、插入数据等操作，非常不便，同时效率也不高
 *      ③获取数组中实际元素的个数的需求，数组中没有现成的属性或方法可用
 *      ④数组存储的数据是有序，可重复的，对于无序不能重复的需求，不能满足
 *
 * 二、集合框架
 * /----Collection接口：单列集合，用于存储一个一个的对象
 *      /----List接口：存储有序的，可重复的数据。---->“动态数组”
 *          /----ArrayList,LinkedList,Vector
 *      /----Set接口：存储无序的，不可重复的数据。---->数学中的“集合”（常见处理过滤问题）
 *          /----HashSet,LinkedHashSet,TreeSet
 * /----Map接口：双列集合，用来村春一对（key,value）
 *          /----HashMap,LinkedHashMap,TreeMap,Hashtable,Properties
 *
 * 三、Collection接口中的方法
 * 注意 ：向collection接口的实现类对象中添加数据obj时，要求obj所在类要重写equals方法
 *
 */
public class CollectionTest {
    @Test
    public void test1(){
        Collection coll = new ArrayList();

        //add()：将元素e添加到集合coll中
        coll.add("AA");
        coll.add("BB");
        coll.add(123);//自动装箱
        coll.add(new String("Tom"));
        Student s = new Student("Jerry");
        coll.add(s);

        //size()：获取添加元素的个数
        System.out.println(coll.size());//

        //addAll(Collection coll)：将coll集合的元素添加到当前的集合中
        Collection coll1 = new ArrayList();
        coll1.add(123);
        coll1.add("CC");
        coll1.addAll(coll);
        System.out.println(coll1.size());
        System.out.println(coll1);

        //clear()：清空集合元素
        coll1.clear();

        //isEmpty()：判断当前集合是否为空
        System.out.println(coll.isEmpty());

        //contains(Object obj)判断当前集合是否有obj，实际调用的是equals方法
        System.out.println(coll.contains(new String("Tom")));//true
        System.out.println(coll.contains(s));//true

        //重写equals()后将由false-->true
        System.out.println(coll.contains(new Student("Jerry")));//true

        //containsAll(Collection coll1)判断形参coll1中的所有元素是否存在于当前集合中
        Collection coll2 = Arrays.asList(123);
        System.out.println(coll.containsAll(coll2));//true

    }

    @Test
    public void test2(){
        Collection coll = new ArrayList();
        coll.add("AA");
        coll.add("BB");
        coll.add(123);//自动装箱
        coll.add(new String("Tom"));
        Student s = new Student("Jerry");
        coll.add(s);

        //remove()
        coll.remove(123);
        System.out.println(coll);
        coll.remove(new Student("Jerry"));
        System.out.println(coll);
        
        //removeAll(Collection coll1)移除coll1里所有的共有元素
        Collection coll1 = Arrays.asList(123,456,789);
        coll.retainAll(coll1);//取“交集”，修改了当前的coll集合

        //equals(Object obj):判断当前集合和形参集合元素是否相同
        System.out.println(coll.equals(coll1));
    }

    @Test
    public void test3(){
        Collection coll = new ArrayList();
        coll.add("AA");
        coll.add("BB");
        coll.add(123);//自动装箱
        coll.add(new String("Tom"));
        Student s = new Student("Jerry");
        coll.add(s);

        //hashCode()返回当前对象的hash值
        System.out.println(coll.hashCode());

        //集合--->数组
        Object[] arr = coll.toArray();
        for(int i = 0;i < arr.length;i++){
            System.out.println(arr[i]);
        }

        //拓展：数组-->集合
        List<String> list = Arrays.asList(new String[]{"AA","BB","CC"});
        System.out.println(list);

        List<int[]> list1 = Arrays.asList(new int[]{123,456,789});
        System.out.println(list1);//数组被认为是一个元素

        List list2 = Arrays.asList(new Integer[]{123,456,789});
        System.out.println(list2);//正确写法

        //iterator()：返回Iterator接口的实例，用于遍历集合的元素，放在IteratorTest.java中测试

    }
}

class Student{
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}' ;
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("调用equals方法");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name);
    }

}