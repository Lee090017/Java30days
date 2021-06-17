package com.atlishu.java;

import org.junit.Test;

/**
 * @author lishustart
 * @create 2020-12-23-21:21
 *S
 * String的常用方法
 */
public class MethodTest {

    @Test
    public void test1(){
        /*
        String常用方法一
         */
        String s1 = "HelloWorld";
        System.out.println(s1.length());
        System.out.println(s1.charAt(0));

        System.out.println(s1.isEmpty());//判断是否为空字符串
        System.out.println(s1.toLowerCase());//将字符串改为小写的，s1不变
        System.out.println(s1.toUpperCase());//将字符串改为大写的，s1不变

        String s2 = "  he  llo   world  ";
        String s3 = s2.trim();//去除字符串首位的空格，中间的空格没关系
        System.out.println("-----"+s2+"-----");
        System.out.println("-----"+s3+"-----");

        String s4 = "abc";
        String s5 = "abc";
        String s6 = "ABC";

        System.out.println(s4.equals(s5));
        System.out.println(s4.equalsIgnoreCase(s6));//与equals方法类似，还可以忽略大小写

        System.out.println(s4.concat(s5));//将指定字符串连接到此字符串的结尾，等价于用“+”

        String s7 = new String("acd");//比较两个字符的大小,一个字符一个字符比较,涉及到字符串排序
        System.out.println(s5.compareTo(s7));//b-c = -1

        String s8 = "重庆邮电大学";
        System.out.println(s8.substring(2));//返回一个新的字符串，它是此字符串的从beginIndex开始截取的字符串
        System.out.println(s8.substring(2,4));//返回一个新的字符串，它是此字符串的从beginIndex开始截取在endIndex结束的字符串

    }

    @Test
    public void test2(){
        /*
        String常用方法二
         */
        String s1 = "helloworld";
        boolean b1 = s1.endsWith("world");//该字符串是否以指定的后缀结束
        System.out.println(b1);
        boolean b2 = s1.startsWith("hell");//该字符串是否以指定的前缀开始
        System.out.println(b2);
        boolean b3 = s1.startsWith("wo",5);//该字符串是否以指定索引的前缀开始
        System.out.println(b3);

        boolean b4 = s1.contains("abc");//判断该字符串中是否包含该字符串
        System.out.println(b4);

        int i1 = s1.indexOf("lo");//找到指定字符串在该字符串第一次出现的索引
        System.out.println(i1);//找不到索引就会返回-1

        int i2 = s1.indexOf("lo",5);//找到指定字符串在该字符串从指定位置开始的第一次出现的索引
        System.out.println(i2);//可以用于统计该字符串文本出现多少次指定字符串

        String s2 = "cqupt";
        System.out.println(s2.lastIndexOf("qu"));//找到指定字符串在该字符串最右边开始第一次出现的索引，索引值还是从前面的0开始
        System.out.println(s2.lastIndexOf("qu",3));//找到指定字符串在该字符串最右边指定位置开始第一次出现的索引，索引值还是从前面的0开始

        //什么情况下，indexOf(str)和lastIndexOf(str)返回值相同?
        System.out.println(s2.lastIndexOf("up"));
        System.out.println(s2.indexOf("up"));
        //情况一：相同且存在的str，情况二：不存在str
    }

    @Test
    public void test3(){
        /*
        String常用方法三
         */
        //替换
        String s1 = "重庆邮电大学通信与信息工程学院";
        String s2 = s1.replace('信','X');//替换原字符串中所有指定的字符
        System.out.println(s1);
        System.out.println(s2);

        String s3 = s1.replace("重庆","北京");//替换原字符串中所有指定的字符串
        System.out.println(s3);
        System.out.println("------------------------");

        String s4 = "1j212kk1jjh12n4sa4ssjo8jaw4nw4dia9";
        //将字符串的数字替换为“~”，如果结果开头或者结尾有~的话就去掉
        String s5 = s4.replaceAll("\\d+","~").replaceAll("^~|~$","");
        System.out.println(s5);
        //replaceFirst()只替换第一个
        //匹配
        String s6 = "12345";
        //判断s6字符串是否全是数字
        boolean match = s6.matches("\\d+");
        System.out.println(match);
        //判断这是否是一个杭州的电话号码
        String tell = "0571-451611560";
        boolean result = tell.matches("0571-\\d{7,8}");//尾号为7位或者8位为正则匹配
        System.out.println(result);
        //切片
        String str = "hello|world|java";
        //将字符串按照"|"切分存到数组里面
        String[] strs = str.split("\\|");
        for (int i = 0; i <strs.length ; i++) {
            System.out.println(strs[i]);
        }

    }
}
