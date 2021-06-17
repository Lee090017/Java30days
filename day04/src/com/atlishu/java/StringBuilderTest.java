package com.atlishu.java;

/**
 * @author lishustart
 * @create 2021-03-09-15:27
 *
 * 用于测试StringBuilder的常用方法
 *
 */
public class StringBuilderTest {
    public static void main(String[] args) {
        StringBuilder s = new StringBuilder("I");
        //在字符结尾后面添加字符
        s.append(" java");
        System.out.println("append:"+s.toString());
        //在字符串中间插入字符串
        s.insert(1," love");
        System.out.println("insert:"+s.toString());
        System.out.println("length:"+s.length());
        //使用 ch 指定的新值设置 index 指定的位置上的字符
        s.setCharAt(7,'D');
        System.out.println("setCharAt:"+s.toString());
        //反转字符
        s.reverse();
        System.out.println("reverse:"+s.toString());
        //删除指定位置的字符串
        s.delete(8,10);//删除8、9
        System.out.println("delete:"+s.toString());
        //替换指定位置字符串
        StringBuilder s2 = new StringBuilder("lishu");
        s2.replace(2,5,"ming");
        System.out.println("replace:"+s2.toString());
        //获取指定位置的元素
        System.out.println("charAt:"+s2.charAt(4));
        System.out.println("indexOf:"+s2.indexOf("in"));

        //遍历
        for (int i = 0; i < s2.length(); i++) {
            System.out.println(s2.charAt(i));
        }
    }
}
