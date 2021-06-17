package com.atlishu.java2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author lishustart
 * @create 2021-03-30-9:38
 *
 * jdk5.0
 * foreach-->增强for循环，用于遍历集合和数组
 */
public class ForeachTest {
    @Test
    public void test1(){
        Collection coll = new ArrayList();
        coll.add("AA");
        coll.add("BB");
        coll.add(123);//自动装箱
        coll.add(new String("Tom"));
        Student s = new Student("Jerry");
        coll.add(s);
        coll.add(456);

        //for(集合元素类型 局部变量 ：集合对象)
        for(Object obj : coll){
            System.out.println(obj);
        }
    }

    //练习题
    @Test
    public void test2(){
        String[] arr = new String[]{"MM","MM","MM"};

        //方式一，改变数组的值
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = "GG";
//        }

        //方式二，不改变数组的值
        for(String s: arr){
            s = "GG";
        }

        for (String s : arr
             ) {
            System.out.println(s);
        }
    }

}
