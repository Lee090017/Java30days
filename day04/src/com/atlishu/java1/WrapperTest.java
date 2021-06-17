package com.atlishu.java1;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @author lishustart
 * @create 2021-03-09-21:50
 *
 * 复习基本类型、包装类、String类间的转换
 */
public class WrapperTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //基本数据类型->包装类
        Integer i1 = 1;//自动装箱
        int i2 = i1;//自动拆箱

        //基本数据类型->String
        String str1 = "123";
        int i3 = Integer.parseInt(str1);
        String str2 = String.valueOf(i3);

        //String与char[]的转换
        char[] charArray = str1.toCharArray();//调用toCharArray()方法
        String str3 = new String(charArray);

        //String与byte[]的转换
        String str4 = "abc123中国";
        //使用默认的字符串，进行转换
        byte[] b1 = str4.getBytes();//[97,98,99,49,50,51]ASCII码

        byte[] b2 = str4.getBytes("gbk");

        //使用String的构造器进行反向操作
        String s1 = new String(b2);
        System.out.println(s1);
        String s2 = new String(b2,"gbk");//当进行解码的时候，要使用对应的解码集
        System.out.println(s2);


    }

    /*
      模拟一个trim方法，去除字符串两端的空格
     */
    @Test
    public void test1(){
        String str1 = "li shu  ";
        String str2 = "";
        int low = 0;
        int high = str1.length();

        char[] c1 = str1.toCharArray();

        for(int i=0; i< c1.length ; i++){
            if(c1[low] != ' ' && c1[high-1] != ' '){
                break;
            }
            if(c1[i] == ' '){
                low++;
            }
            if(c1[c1.length-1-i] == ' '){
                high--;
            }
            str2 = str1.substring(low,high);
        }
        System.out.println(str2);
    }

    /*
        将字符串指定位置进行反转
     */
    @Test
    public void test2(){
        String str1 = "abcdefg";
        String str2 = "cdef";
        int index = str1.indexOf(str2);
        int s = index;
        int e = index + str2.length()-1;

        StringBuilder str3 = new StringBuilder(str2);
        str3.reverse();
        System.out.println(str3);

        System.out.println(str1.replace(str2,str3));
    }

    /*
      获取一个字符串在另一个字符串中出现的次数
     */
    @Test
    public void test3(){
        String str1 = "abkkcadkabkebfkabkskab";
        String str2 = "bk";
        int count = 0;
        int index = 0;

        while (true){
            int i = str1.indexOf(str2,index);
            if(i < 0){
                break;//没有搜索到指定字符
            }else{
                count++;
                index = i + str2.length();
            }
        }

        System.out.println(str2+" 在 "+str1+" 中出现了"+count+"次。");
    }

    /*
        获取两个字符串中最大的相同子串
     */
    @Test
    public void test4(){
        String str1 = "abcwerthelloyulodef";
        String str2 = "cvhellobnmdef";
        String str = findMaxSameString(str1,str2);
        System.out.println(str);

    }

    //该函数用于查找某两个字符串中相同的最大子字符串
    public String findMaxSameString(String s1,String s2){
        //使得str1为字符串长度较大的输入
//        if(str1.length() < str2.length()){
//            String str = str1;
//            str1 = str2;
//            str2 = str;
//        }
        String str1 = s1.length() >= s2.length() ? s1 : s2;
        String str2 = s1.length() < s2.length() ? s1 : s2;

        //定义一个用于平移的窗
        int leng = str2.length();
        String str3;
        int index = 0;

        for(int i = leng;i > 0;i--){//窗口大小
            for(int j = 0;j <= leng-i;j++){//窗口起始位置
                str3 = str2.substring(j,j+i);
                index = str1.indexOf(str3);
                if(index >= 0){
                    return str3;
                }
            }
        }
        return null;
    }
    /*
        对字符串中字符进行自然顺序排序
     */
    @Test
    public void test5(){
        String str1 = "89234673156";
        char[] c1 = str1.toCharArray();
        Arrays.sort(c1);
        String str2 = new String(c1);
        System.out.println(str2);
    }
}
