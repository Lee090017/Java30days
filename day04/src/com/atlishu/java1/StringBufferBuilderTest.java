package com.atlishu.java1;

/**
 * @author lishustart
 * @create 2021-03-10-19:43
 *
 * 关于StringBuffer和StringBuilder的使用
 *
 */
public class StringBufferBuilderTest {
    /*
        String、StringBuffer、StringBuilder的区别
        String 不可变的字符序列
        StringBuffer 可变的字符序列，线程安全，效率低
        StringBuilder 可变的字符序列，线程不安全，效率高
        三者底层都是用char[]存储
     */
    /*
        String str = new String();//char[] value = new char[0];
        String str = new String("abc");//char[] value = new char[]{'a','b','c'};

        StringBuffer sb1 = new StringBuffer();//char[] value = new char[16];
        sb1.append('a');//value[0] = 'a';
        sb1.append('b');//value[0] = 'b';

        StringBuffer sb2 = new StringBuffer("abc");//char[] value = new char["abc".length()+16];

        问题一：System.out.println(sb2.length());//3
        问题二：扩容问题，如果要添加的数据底层数组装不下了，那就要扩容底层的数组。
               默认情况下，扩容为原来容量的2倍 + 2，同时将原有数组中的元素复制到新的数组中。
     */

}
