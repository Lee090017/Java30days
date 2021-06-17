package com.atlishu.java;

import org.junit.Test;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author lishustart
 * @create 2021-05-12-10:27
 */
public class Java11Test {
    @Test
    public void test1(){
        //isBlank()判断字符串是否为空白
        System.out.println("   ".isBlank());//true
        //strip()去除首尾空白
        System.out.println("\tlishu\t".strip());
        //stripTrailing()去除尾部空格
        System.out.println("\tlishu\t".stripTrailing());
        //stripLeading()去除首部空格
        System.out.println("\tlishu\t".stripLeading());
        //repeat(n)复制n次字符串
        System.out.println("java".repeat(3));
        //lines().count()行数统计
        System.out.println("a\nb\nc\n".lines().count());

    }

    //java11新增Optional方法
    @Test
    public void test2(){
        Optional<Object> empty = Optional.empty();
        System.out.println(empty.isPresent());//判断内部value是否存在
        System.out.println(empty.isEmpty());//判断内部value是否为空

        Optional<String> abc = Optional.ofNullable("abc");//
        Object o = abc.orElseThrow();//无值抛异常
        System.out.println(o);


        empty.ifPresentOrElse(s -> System.out.println(s),()-> System.out.println("空指针"));
    }

    //局部变量类型推断的升级
    @Test
    public void test3(){
        //加注解修饰变量时必须加上var
        Consumer<String> con = (@Deprecated var s) -> System.out.println(s.toUpperCase());

    }
}
