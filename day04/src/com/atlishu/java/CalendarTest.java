package com.atlishu.java;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * @author lishustart
 * @create 2021-03-11-10:36
 *
 * Calendar日历类
 */
public class CalendarTest {

    @Test
    public void test1(){
        //1、实例化
        //方式一：创建子类GregorianCalendar的对象
        //方法二：调用静态方法Calendar.getInstance()
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getClass());

        //常用方法
        //get()
        int day = calendar.get(Calendar.DAY_OF_MONTH);//获取该月的第几天
        System.out.println(day);

        //set()
        calendar.set(Calendar.DAY_OF_MONTH,8);
        day = calendar.get(Calendar.DAY_OF_MONTH);//获取该月的第几天
        System.out.println(day);

        //add()
        calendar.add(Calendar.DAY_OF_MONTH,-5);
        day = calendar.get(Calendar.DAY_OF_MONTH);//获取该月的第几天
        System.out.println(day);

        //getTime()
        Date date = calendar.getTime();
        System.out.println(date);

        //setTime()
        Date date1 = new Date();
        calendar.setTime(date1);
        day = calendar.get(Calendar.DAY_OF_MONTH);//获取该月的第几天
        System.out.println(day);
    }



}
