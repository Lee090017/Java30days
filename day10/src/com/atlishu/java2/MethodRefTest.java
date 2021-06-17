package com.atlishu.java2;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author lishustart
 * @create 2021-05-07-9:52
 *
 * 方法引用
 *
 * 1.使用说明:，当要传递给Lambda体的操作，已经有实现的方法，可以使用方法引用
 *
 * 2.方法引用，本质上就是Lambda表达式，而Lambda表达式作为函数式接口的实例
 *
 * 3.使用格式 类（或对象）：：方法名
 *
 * 4.具体分为如下三种情况
 * 对象：：非静态方法
 * 类：：静态方法
 * 类：：非静态方法
 *
 * 5.方法引用使用要求：要求接口中的抽象方法的形参列表和返回值类型与方法引用的方法的
 * 形参列表和返回值类型相同。
 */
public class MethodRefTest {
    //情况一：对象：：实例方法
    //Consumer中的accept
    //PrintStream中的println
    @Test
    public void test1(){
        Consumer<String> stringConsumer = astring-> System.out.println(astring);
        stringConsumer.accept("北京");
        System.out.println("******************************");
        PrintStream ps = System.out;
        Consumer<String> stringConsumer1 = ps :: println;
        stringConsumer1.accept("Beijing");
    }

    //Supplier中的get()
    //Employee中的String getName
    @Test
    public void test2(){
        Employee emp = new Employee(1001,"Tom",23,5600);
        Supplier<String> sup1 = ()->emp.getName();
        System.out.println(sup1.get());
        System.out.println("******************************");
        Supplier<String> sup2 = emp :: getName;
        System.out.println(sup2.get());
    }

    //情况二：类：：静态方法
    //Comparator中的int compare(T t1,T t2)
    //Integer中的int compare(T t1,T t2)
    @Test
    public void test3(){
        Comparator<Integer> comparator1 = (t1,t2)-> Integer.compare(t1,t2);
        System.out.println(comparator1.compare(100,200));
        System.out.println("******************************");
        Comparator<Integer> comparator2 = Integer::compare;
        System.out.println(comparator2.compare(200,100));

    }

    //情况三：类：：实例方法
    //Comparator中的int compare(T t1,T t2)
    //String中的int t1.compareTo(t2)
    @Test
    public void test4(){
        Comparator<String> comparator1 = (t1,t2)->t1.compareTo(t2);
        System.out.println(comparator1.compare("abc","bcd"));
        System.out.println("******************************");
        Comparator<String> comparator2 = String::compareTo;
        System.out.println(comparator2.compare("mqtt","tcp"));
    }

    //Function中的R apply(T t)
    //Employee中的String getName();
    @Test
    public void test5(){
        Employee emp = new Employee(1001,"Tom",23,5600);
        Function<Employee,String> function1 = e->e.getName();
        System.out.println(function1.apply(emp));
        System.out.println("******************************");
        Function<Employee,String> function2 = Employee::getName;
        System.out.println(function2.apply(emp));

    }
}
