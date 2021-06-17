package com.atlishu.java1;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author lishustart
 * @create 2021-05-06-21:49
 *
 * Lambda表达式的使用
 *
 * 1.举例：(o1,o2)-> Integer.compare(o1,o2);
 * 2.格式：
 *      ->：Lambda操作符 或者 箭头操作符
 *      (o1,o2)：Lambda形参列表（其实就是接口中抽象方法的形参列表）
 *      Integer.compare(o1,o2);：Lambda体（其实就是重写的抽象方法的方法体）
 * 3.表达式的使用（6种）
 *      总结：
 *      ->左边：Lambda形参列表的参数类型可以省略（类型推断），如果参数列表只有一个参数，小括号也可以省略，只有一个或两个以上不能省略
 *      ->右边：Lambda体需要使用{}包括，但是只有一条执行语句是可以省略的（return关键字也可以省略）
 *
 * 4.Lambda表达式的本质：作为函数式接口的实例
 *
 * 5.如果一个接口中只声明了一个抽样方法，它就是一个函数式接口
 */
public class LambdaTest1 {
    //语法格式一：无参，无返回值
    @Test
    public void test1(){
        Runnable r = () -> {
            System.out.println("I love java");
        };
        r.run();
    }

    //语法格式二：Lambda需要一个参数，但是没有返回值
    @Test
    public void test2(){
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("Good Morning");

        System.out.println("******************");
        Consumer<String> con1 = (String s)->
        {
            System.out.println(s);
        };
        con1.accept("Good Evening");

    }
    //语法格式三：数据类型可以省略因为可以由编译器推断得出，称为“类型推断”
    @Test
    public void test3(){
        Consumer<String> con1 = (s)->//可以由前面编译器得出其格式为String
        {
            System.out.println(s);
        };
        con1.accept("Good Evening");

    }

    //语法格式四：Lambda如果形参只有一个参数，小括号也可以省
    @Test
    public void test4(){
        Consumer<String> con1 = s->//小括号也可以省
        {
            System.out.println(s);
        };
        con1.accept("Good Evening");
    }

    //语法格式五：Lambda需要两个或以上的参数，多条执行语句，并且可以有返回值
    @Test
    public void test5(){
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return Integer.compare(o1,o2);
            }
        };

        int compare1 = com1.compare(12,23);
        System.out.println(compare1);

        System.out.println("*********************************");
        //Lambda表达式
        Comparator<Integer> com2 = (o1,o2)-> {
            System.out.println(o1);
            System.out.println(o2);
            return Integer.compare(o1,o2);
        };
        int compare2 = com2.compare(56,23);
        System.out.println(compare2);
    }

    //语法格式六：当Lambda体只有一条语句时，return与大括号若有，都可以省略
    @Test
    public void test6(){
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };

        int compare1 = com1.compare(12,23);
        System.out.println(compare1);

        System.out.println("*********************************");
        //Lambda表达式
        Comparator<Integer> com2 = (o1,o2)->Integer.compare(o1,o2);//只有一条语句，括号与return都可以省略
        int compare2 = com2.compare(56,23);
        System.out.println(compare2);
    }
}
