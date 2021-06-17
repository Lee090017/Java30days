package com.atlishu.java;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * @author lishustart
 * @create 2021-03-11-9:05
 *
 * JDK8之前的日期时间的API测试
 */
public class DateTimeTest {
    /*
        SimpleDateFormat的使用：对日期Date类的格式化和解析
        1、格式化：日期--->字符串
        2、解析：字符串--->日期
     */
    @Test
    public void test1() throws ParseException {
        //实例化SimpleDateFormat()，使用默认的构造器
        SimpleDateFormat sdf = new SimpleDateFormat();

        //格式化
        Date d1 = new Date();
        System.out.println(d1);
        String format = sdf.format(d1);
        System.out.println(format);

        //解析
//        String str = "2021-03-11";//该格式无法进行解析
        String str = "2021/3/11 上午9:14";
        Date d = sdf.parse(str);
        System.out.println(d);

        //**************************************************
        //指定方式进行格式化
        //调用带参数的构造器
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String str1 = sdf1.format(d1);
        System.out.println(str1);
        //解析
        Date d2 = sdf1.parse("2021-03-11 09:24:02");
        System.out.println(d2);
    }

    /*
        练习一：字符串“2021-03-11”转换为java.sql.Date
     */
    @Test
    public void test2() throws ParseException {
        String str = "2021-03-11";
        //先将字符串解析为Date对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(str);
        //再将util.Date转换为sql.Date
        java.sql.Date jsd = new java.sql.Date(date.getTime());
        System.out.println(jsd);
    }

    /*
        练习二：“三天打鱼，两天晒网”从 1990-01-01 开始的 xxxx-xx-xx 打渔？晒网？

        计算有多少天，举例：2020-09-08？
     */
    @Test
    public void test3() throws ParseException {
        String begin = "1990-01-01";
        String end = "2020-09-08";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdf.parse(begin);
        Date d2 = sdf.parse(end);

        Long time = d2.getTime() - d1.getTime();
        System.out.println(time+" ms");//单位ms

        Long day = time/1000/60/60/24;
        System.out.println(day+" day");//单位天

        Long now = (day+1)%5;
        System.out.println(now);//4，在晒网
        //now = 1,2,3 打渔
        //now = 4,0 晒网
    }

    /*
        DateTimeFormatter:格式化或解析时间、日期
     */
    @Test
    public void test4(){
        //预定义的方式（略）

        //本地化相关的格式（略）

        //自定义格式
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //格式化
        String format = dateTimeFormatter.format(LocalDateTime.now());
        System.out.println(format);
        //解析
        TemporalAccessor parse = dateTimeFormatter.parse("2019-11-09");
        System.out.println(parse);
    }
}
