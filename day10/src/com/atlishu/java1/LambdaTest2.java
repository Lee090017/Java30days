package com.atlishu.java1;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author lishustart
 * @create 2021-05-07-9:20
 *
 * 4大内置函数式接口：消费型、供给型、函数型、断定型接口
 */
public class LambdaTest2 {
    @Test
    public void test1(){
        Consumer<Double> doubleConsumer = adouble-> System.out.println("充原神花了"+adouble+"元。");
        doubleConsumer.accept(648.0);
    }

    @Test
    public void test2(){
        Predicate<Integer> integerPredicate = aint->{
            if(aint>=60) return true;
            return false;
        };
        boolean t = integerPredicate.test(45);
        if(t){
            System.out.println("及格");
        }else System.out.println("不及格");

    }
}
