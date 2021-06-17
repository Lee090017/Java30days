package com.atlishu.java;

import org.junit.Test;

import java.time.*;

/**
 * @author lishustart
 * @create 2021-03-11-11:18
 */
public class JDK8DateTimeTest {

    @Test
    public void test1(){
        //now()实例化
        LocalDate local1 = LocalDate.now();
        LocalTime local2 = LocalTime.now();
        LocalDateTime local3= LocalDateTime.now();

        System.out.println(local1);
        System.out.println(local2);
        System.out.println(local3);

        //of()：设置指定的年、月、日、时、分、秒，没有偏移量
        LocalDateTime localDateTime1 = LocalDateTime.of(2021,10,11,21,5,3);
        System.out.println(localDateTime1);

        //getXxx() 获取相应的属性
        System.out.println(local3.getDayOfYear());

        //withXxx() 设置相关的属性，体现不可变性
        LocalDateTime local4 = local3.withDayOfMonth(22);
        System.out.println(local3);
        System.out.println(local4);

        //plusXxx() 加相关的属性，体现不可变性
        LocalDateTime local5 = local3.plusMonths(2L);
        System.out.println(local3);
        System.out.println(local5);

        //minusXxx() 减相关的属性，体现不可变性
        LocalDateTime local6 = local3.minusDays(3L);
        System.out.println(local3);
        System.out.println(local6);
    }

    /*
        Instant类的使用
     */
    @Test
    public void test2(){
        //now():获取本初子午线对应的标准时间
        Instant instant = Instant.now();
        System.out.println(instant);

        //添加时间的偏移量
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        //获取时间戳的ms数
        Long time = instant.toEpochMilli();
        System.out.println(time);

        //ofEpochMilli()通过给定的毫秒数，获取Instant实例
        Instant instant1 = Instant.ofEpochMilli(1615452495810L);
        System.out.println(instant1);
    }
}
