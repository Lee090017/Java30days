package com.atlishu.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lishustart
 * @create 2021-05-11-22:26
 */
public class Java10Test {

    //属性不允许使用var
//    var n = 11;

    //局部变量的类型推断
    @Test
    public void test1(){
        //1.声明变量时，根据所赋的值，推断变量的类型
        var num = 10;

        var list = new ArrayList<Integer>();
        list.add(1);

        //2.遍历操作
        for (var i: list) {
            System.out.println(i);
            System.out.println(i.getClass());
        }

        //3.普通遍历操作
        for (var i = 0; i < 10; i++) {
            System.out.print(i);
        }
        System.out.println();
    }

    //java10新增创建不可变的集合
    @Test
    public void test2(){
        //copyOf()
        var list = List.of(1, 2, 3, 4, 5, 6);
        var list1 = List.copyOf(list);
        System.out.println(list==list1);//true

        var arrayList = new ArrayList<Integer>();
        var list2 = List.copyOf(arrayList);
        System.out.println(arrayList==list2);//false

        //上述两种方法的区别
        //如果原来集合为只读集合，将不再新建新的集合，而直接使用原来集合；如果原集合可以
        //添加数据，则需要重写创建一个新的只读集合
    }
}
