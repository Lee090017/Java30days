package com.atlishu.java;

import org.junit.Test;

import java.util.Random;
import java.util.Scanner;

/**
 * @author lishustart
 * @create 2021-03-09-20:56
 *
 * Random 类用于生成伪随机数流，在 java.util包下。
 */
public class RandomTest {
    public static void main(String[] args) {
        Random random = new Random();
        System.out.println("生成50到100整数:"+(random.nextInt(100-50+1)+50));
        System.out.println("生成一个整数 long范围:"+random.nextLong());
        //生成[0,1.0)范围的float型小数
        System.out.println("生成一个float:"+random.nextFloat());
        //生成[0,1.0)范围的double型小数
        System.out.println("生成一个double:"+random.nextDouble());
        //练习
        Scanner in = new Scanner(System.in);
        Random rd = new Random();
        System.out.println("请输入：");
        int a = in.nextInt();
        int b = in.nextInt();
        System.out.println("输出：\r\n"+(rd.nextInt(b-a+1)+a));
    }

    @Test
    public void test(){
        //从控制台中获取 Int 数据 m，n (m < n)，先输入 m，后输入 n。
        //输出一个 [m,n] 之间的随机数。
        Scanner in = new Scanner(System.in);
        Random rd = new Random();
        System.out.println("请输入：");
        int a = in.nextInt();
        int b = in.nextInt();
        System.out.println("输出：\r\n"+(rd.nextInt(b-a+1)+a));
    }
}
