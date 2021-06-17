package com.atlishu.java;

import org.junit.Test;

/**
 * @author lishustart
 * @create 2021-03-09-20:00
 *
 * Math类的常用方法
 *
 */
public class MathTest {
    public static void main(String[] args) {
        System.out.println("sin(30')="+Math.sin(Math.PI/6));
        System.out.println("cos(60')="+Math.cos(Math.PI/3));
        System.out.println("acos(0.5)="+Math.acos(0.5));
        System.out.println("asin(0.5)="+Math.asin(0.5));
        //计算a的b次方
        System.out.println("2^8="+Math.pow(2,8));
        System.out.println("64^1/2="+Math.sqrt(64));
        System.out.println("|-23|="+Math.abs(-23));
        //返回大于等于 numvalue 的最小整数值
        System.out.println("ceil(-1.3)="+Math.ceil(-1.3));
        //返回小于等于 numvalue 的最大整数值
        System.out.println("floor(-1.3)="+Math.floor(-1.3));
        System.out.println("max(1,2)="+Math.max(1,2));
        System.out.println("min(1,2)="+Math.min(1,2));
        //返回最接近 numvalue 的整数值
        System.out.println("rint(-1.3)="+Math.rint(-1.3));
        //返回最接近 arg 的整数值,arg 为 double 时返回 long，为 float 时返回 int
        System.out.println("round(2.5)="+Math.round(2.5));
        //返回带正号的 double 值，该值大于等于 0.0 且小于 1.0
        System.out.println("random()="+Math.random());
    }

    //随机生成两个1-100的随机整数，并比较大小
    @Test
    public void test1(){
        int a = (int)(Math.random()*100);
        int b = (int)(Math.random()*100);
        System.out.println("a="+a+",b="+b);
        System.out.println("较大值为"+Math.max(a,b));
    }
}
