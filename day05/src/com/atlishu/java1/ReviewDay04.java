package com.atlishu.java1;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/**
 * @author lishustart
 * @create 2021-03-12-11:06
 *
 * 复习day04的重要知识点
 * 1、包括有两种时间类Date和LocalDateTime的格式化和解析
 * 2、StringBuffer和StringBuilder类的使用
 * 3、两种实现java比较器的操作Comparable接口和Comparator
 */
public class ReviewDay04 {
    /*
        Date类的格式化和解析
     */
    @Test
    public void test1() throws ParseException {
        Date d1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String str1 = simpleDateFormat.format(d1);
        System.out.println(str1);

        String str2 = "2021-03-12 11:16:37";
        Date d2 = simpleDateFormat.parse(str2);
        System.out.println(d2);
    }

    /*
        LocalDateTime格式化和解析
     */
    @Test
    public void test2(){
//        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime = LocalDateTime.of(2018,5,12,15,50);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String string = dateTimeFormatter.format(localDateTime);
        System.out.println(string);

        TemporalAccessor localDateTime1 = dateTimeFormatter.parse(string);
        System.out.println(localDateTime1);

    }

    /*
        StringBuilder类的使用
     */
    @Test
    public void test3(){
        StringBuilder str1 = new StringBuilder("I");
        str1.append(" java");
        System.out.println(str1);
        str1.insert(1," love");
        System.out.println(str1);
        str1.delete(1,6);
        System.out.println(str1);
        str1.replace(1,2," love ");
        System.out.println(str1);
        str1.setCharAt(7,'f');
        System.out.println(str1);

    }
    /*
        实现Comparable接口的比较器
     */
    @Test
    public void test4(){
        Animal[] arr = new Animal[5];
        arr[0] = new Animal("monkey",50);
        arr[1] = new Animal("bird",100);
        arr[2] = new Animal("dog",60);
        arr[3] = new Animal("cat",20);
        arr[4] = new Animal("fish",200);

        Arrays.sort(arr);

        System.out.println(Arrays.toString(arr));

    }

    /*
        实现Comparator的比较器
     */
    @Test
    public void test5(){
        Animal[] arr = new Animal[6];
        arr[0] = new Animal("monkey",50);
        arr[1] = new Animal("bird",100);
        arr[2] = new Animal("dog",60);
        arr[3] = new Animal("cat",20);
        arr[4] = new Animal("fish",200);
        arr[5] = new Animal("chicken",100);
        //先实现对象的数量从小到大，数量相同就名字从大到小

        Arrays.sort(arr, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Animal && o2 instanceof Animal){
                    Animal a1 = (Animal) o1;
                    Animal a2 = (Animal) o2;
                    if(a1.getNumber() == a2.getNumber()){
                        return -a1.getName().compareTo(a2.getName());
                    }else{
                        return Integer.compare(a1.getNumber(),a2.getNumber());
                    }
                }

                throw new RuntimeException("输入数据格式不一致");
            }
        });

        System.out.println(Arrays.toString(arr));

    }
}

class Animal implements Comparable{
    private String name;
    private int number;

    public Animal(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "{"+name+","+number+"}";
    }

    //按照动物数量从多到少排列
    @Override
    public int compareTo(Object o) {
        if(o instanceof Animal){
            Animal animal = (Animal)o;
            return -Integer.compare(this.number,animal.number);
        }

        throw new RuntimeException("传入数据格式不一致");
    }
}