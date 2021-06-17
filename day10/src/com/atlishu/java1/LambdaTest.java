package com.atlishu.java1;

import org.junit.Test;

import java.util.Comparator;

/**
 * @author lishustart
 * @create 2021-05-06-21:33
 *
 * Lambda表达式的使用举例
 *
 */
public class LambdaTest {
    @Test
    public void test1(){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("I love java");
            }
        };
        r1.run();

        System.out.println("*********************************");
        //Lambda表达式
        Runnable r2 = () -> System.out.println("I love C++");
        r2.run();
    }

    @Test
    public void test2(){
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
        Comparator<Integer> com2 = (o1,o2)-> Integer.compare(o1,o2);
        int compare2 = com2.compare(56,23);
        System.out.println(compare2);

        System.out.println("*********************************");
        //方法引用
        Comparator<Integer> com3 = Integer :: compare;
        int compare3 = com3.compare(56,23);
        System.out.println(compare3);

    }
}
