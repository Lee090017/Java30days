package com.atlishu.java;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lishustart
 * @create 2021-03-09-15:59
 *
 * 测试Date类
 */
public class DateTest {
    public static void main(String[] args) {
        String strDate, strTime;
        //构造器1
        Date objDate = new Date();
        System.out.println("今天的日期是："+objDate);
        //两个方法的使用
        long time = objDate.getTime();
        System.out.println("从1970年1月1日起以毫秒为单位的时间（GMT）："+time);
        strDate = objDate.toString();//显示当前的年月日

        //构造器2
        Date date2 = new Date(1615380099894L);
        System.out.println(date2.toString());

        //提取GMT时间
        strTime = strDate.substring(11,strDate.length()-9);
        System.out.println("时间："+strTime);

        //格式化时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        System.out.println(formatter.format(objDate));

        //创建java.sql.Date的构造器
        java.sql.Date date3 = new java.sql.Date(1615380099894L);
        System.out.println(date3);

        //如何将util.Date转化为sql.Date对象
        Date date4 = new java.sql.Date(1615380099894L);
        //情况一
        java.sql.Date date5 = (java.sql.Date) date4;
        //情况二
        Date date6 = new Date();
        java.sql.Date date7 = new java.sql.Date(date6.getTime());
    }

    /*
        System类的currentTimeMillis()
     */
    @Test
    public void test1(){
        Long time = System.currentTimeMillis();
        System.out.println(time);
    }
}
