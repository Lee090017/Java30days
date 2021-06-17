package com.atlishu.java2;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author lishustart
 * @create 2021-05-07-15:24
 *
 * 一、构造器引用
 * 和方法引用类似，函数式接口的抽象方法的形参列表和构造器
 *
 */
public class ConstructorRefTest {

    //Supplier中的T get()
    //Employee中的空参构造器Employee()
    @Test
    public void test1(){
        Supplier<Employee> sup1 = () -> new Employee();
        sup1.get();
        System.out.println("************************");
        Supplier<Employee> sup2 = Employee::new;
        sup2.get();
    }

    //Function中的R apply(T t)
    //Employee中的
    @Test
    public void test2(){
        Function<Integer,Employee> fun1 = (id) -> new Employee(id);
        Employee employee1 = fun1.apply(1234);
        System.out.println(employee1);
        System.out.println("************************");
        Function<Integer,Employee> fun2 = Employee::new;
        Employee employee2 = fun2.apply(5678);
        System.out.println(employee2);
    }

    //数组引用
    //Function中的R apply(T t)
    @Test
    public void test3(){
        Function<Integer,String[]> fun1 = length->new String[length];
        String[] str1 = fun1.apply(5);
        System.out.println(Arrays.toString(str1));
        System.out.println("************************");
        Function<Integer,String[]> fun2 = String[]::new;
        String[] str2 = fun2.apply(6);
        System.out.println(Arrays.toString(str2));
    }
}
