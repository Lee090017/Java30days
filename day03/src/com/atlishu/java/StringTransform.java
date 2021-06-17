package com.atlishu.java;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @author lishustart
 * @create 2020-12-29-19:49
 *
 * 涉及到String类与其他结构之间的转换
 */
public class StringTransform {
    /*
    复习：
    String与基本数据类型、包装类的转换
    String-->基本数据类型。包装类调用包装类的静态方法parseXxx(str)
    基本数据类型-->String。调用String的ValueOf(Xxx)

     */
    @Test
    public void test1(){
        String str1 = "123";
        //String-->基本数据类型
        int i1 = Integer.parseInt(str1);
        System.out.println(i1);
        //基本数据类型-->String
        String str2 = String.valueOf(i1);
        String str3 = i1 + "";
    }

    /*
    char[]与String之间的转换
    String --> char[]。通过调用str的toCharArray方法
    char[] --> String。通过调用String的构造器
     */
    @Test
    public void test2(){
        String str1 = "abc123";
        char[] charArray = str1.toCharArray();
        for (int i = 0; i <charArray.length ; i++) {
            System.out.println(charArray[i]);
        }

        char[] arr = new char[]{'a','b','c','1','2','3'};
        String str2 = new String(arr);
        System.out.println(str2);

        //练习，将str1变为"a21cb3"
        int head = str1.indexOf('b');
        int end = str1.indexOf('2');
        for (int i = head; i < head+2 ; i++) {
            char temp = charArray[i];
            charArray[i] = charArray[end];
            charArray[end] = temp;
            end--;
        }
        String str3 = new String(charArray);
        System.out.println(str3);
    }

    /*
    String与byte[]之间的转换
    （编码）String --> byte[]。通过调用getBytes()方法
    （解码）byte[] --> String。通过调用String的构造器

    说明：解码时使用的字符集和编码时的字符集规则必须相同，否则将出现乱码
     */
    @Test
    public void test3() throws UnsupportedEncodingException {
        String str1 = "abc123中国";
        byte[] byte1 = str1.getBytes();//使用默认的UTF-8进行字节转换
        System.out.println(Arrays.toString(byte1));

        byte[] gbks = str1.getBytes("gbk");//使用gbk这个字符集进行编码
        System.out.println(Arrays.toString(gbks));
        System.out.println("****************");

        String str2 = new String(byte1);
        System.out.println(str2);

        String str3 = new String(gbks);//编码和解码的规则不一致，将出现乱码
        System.out.println(str3);

        String str4 = new String(gbks,"gbk");//编码和解码的规则一致，将不会出现乱码
        System.out.println(str4);

    }

    @Test
    public void test4(){
        String s1 = "javaEEhadoop";
        String s2 = "javaEE";
        String s3 = s2 + "hadoop";
        System.out.println(s1==s3);// false

        final String s4 = "javaEE";
        String s5 = s4 + "hadoop";
        System.out.println(s1==s5);//true
        //当声明一个变量为final以后，该变量变为常量，再进行拼接的时候将储存在常量池中
    }
}
