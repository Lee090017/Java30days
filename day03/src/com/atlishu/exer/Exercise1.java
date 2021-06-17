package com.atlishu.exer;

import org.junit.Test;

/**
 * @author lishustart
 * @create 2020-12-29-20:54
 *
 * 常见的算法题
 */
public class Exercise1 {
    /*
    1.模拟一个trim方法，去除字符串两端的空格。
     */
    @Test
    public void test1(){
        String s1 = "  he  llo   world  ";
        char[] c1 = s1.toCharArray();
        //记录第一个不为空格的索引值
        int startindex = 0;
        for (int i = 0; i < c1.length; i++) {
            if(c1[i] != ' '){
                startindex = i;
                break;
            }
        }
        //记录第一个不为空格的索引值(从后向前)
        int endindex = 0;
        for (int i = c1.length-1; i >= 0; i--) {
            if(c1[i] != ' '){
                endindex = i;
                break;
            }
        }
        String s2 = s1.substring(startindex,endindex+1);
        System.out.println(s1);
        System.out.println(s2);
    }
    /*
    2.将一个字符串进行反转。将字符串中指定部分进行反转。比如“abcdefg”反转为”abfedcg”
     */
    @Test
    public void test2(){
        String s1 = "abcdefg";
        String s2 = "cdef";
        //反转一个指定字符串
        char[] c1 = s2.toCharArray();
        int length = c1.length;
        char[] c2 = new char[length];
        for (int i = 0; i < c1.length; i++) {
            c2[i] = c1[length-1];
            length--;
        }
        String s3 = new String(c2);
        String s4 = s1.replace(s2,s3);
        System.out.println(s1);
        System.out.println(s4);
    }

    /*
    3.获取一个字符串在另一个字符串中出现的次数。
    比如：获取“ab”在“abkkcadkabkebfkabkskab”中出现的次数
     */
    @Test
    public void test3(){
        String s1 = "abkkcadkabkebfkabkskab";
        String s2 = "ab";
        int numOfstr = 0;
        int indexBegain = 0;
        while (true){
            int i = s1.indexOf(s2,indexBegain);
            System.out.println(i);
            if(i>=0){
                numOfstr++;
                indexBegain=i+2;
            }else{
                break;
            }
        }
        System.out.println(numOfstr);

    }

    /*
    4.获取两个字符串中最大相同子串。比如：
    str1="abcwerthelloyuiodef"；str2="cvhellobnm"
    提示：将短的那个串进行长度依次递减的子串与较长的串比较。
     */
    @Test
    public void test4(){
        String str1 = "abcwerthelloyuiodef";
        String str2 = "cvluyobnm";//"cvhellobnm"
        int window = str2.length();
        String str3 = "";
//        String s1 = str2.substring(0,window);
//        System.out.println(s1);
        boolean flag = false;
        while(true) {
            if (flag) {
                //已找到指定字符
                break;
            }else if(window > 0){
                window--;
            }else{
                System.out.println("未找到指定字符");
                break;
            }
            for (int i = 0; i <= str2.length() - window; i++) {
                String s1 = str2.substring(i, i + window);
                if (str1.indexOf(s1) >= 0) {
                    str3 = s1;
                    System.out.println("找到字符串："+str3);
                    flag = true;
                    break;
                }
            }
        }
    }
    /*
    5.对字符串中字符进行自然顺序排序。
    提示：
    1）字符串变成字符数组。
    2）对数组排序，选择，冒泡，Arrays.sort（）；
    3）将排序后的数组变成字符串。
     */
}
