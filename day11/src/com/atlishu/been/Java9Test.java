package com.atlishu.been;

import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author lishustart
 * @create 2021-05-11-19:43
 *
 */
public class Java9Test {
    /*
    java9特性：钻石操作符的升级
    <>在jdk8中的匿名内部类中不能省略，jdk9中可以表示
     */
    @Test
    public void test1(){
        Comparator<Object> comparator = new Comparator<>() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };

        //jdk7中的新特性，类型推断
        ArrayList<String> list = new ArrayList<>();
    }

    //jdk9中关于try操作的升级
    public static void main(String[] args) {
        //方式一
//        InputStreamReader isr = null;
//        try {
//            isr = new InputStreamReader(System.in);
//            char[] cbuf = new char[20];
//            int len;
//            if((len = isr.read(cbuf))!=-1){
//                String str = new String(cbuf,0,len);
//                System.out.println(str);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if(isr!=null){
//                try {
//                    isr.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        //java8的优化处理，自动关闭资源
//        try(InputStreamReader isr = new InputStreamReader(System.in)){
//            char[] cbuf = new char[20];
//            int len;
//            if((len = isr.read(cbuf))!=-1){
//                String str = new String(cbuf,0,len);
//                System.out.println(str);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //java9中资源的关闭操作
        InputStreamReader isr = new InputStreamReader(System.in);//初始化可以在try子句的外部
        try(isr){//资源为一个常量，不能修改
            char[] cbuf = new char[20];
            int len;
            if((len = isr.read(cbuf))!=-1){
                String str = new String(cbuf,0,len);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //集合工厂方法：创建只读集合
    //aslist也是一个只读集合
    @Test
    public void test2(){
        //java8
        ArrayList<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        List<String> strings = Collections.unmodifiableList(list);
//        strings.add("ddd");//UnsupportedOperationException

        //java9
        List<Integer> list1 = List.of(1, 2, 3, 4, 5);//只读集合，不能添加

        Map<String, Integer> stringIntegerMap = Map.ofEntries(Map.entry("Tom", 34), Map.entry("Bob", 23));


    }

    //InputStream里的方法transferTo可以将数据流直接传给OutpuStream
    @Test
    public void test3() throws Exception {
        File file = new File("hello.txt");
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        isr.transferTo(osw);
        isr.close();
        osw.close();
    }

    //streamAPI的加强
    @Test
    public void test4(){
        List<Integer> list = Arrays.asList(12, 54, 15, 56, 89, 45, 21, 33);
        //takeWhile()返回从开头开始的尽可能多的元素
        Stream<Integer> stream = list.stream().takeWhile(i -> i < 50);//12
        stream.forEach(System.out::print);
        System.out.println();

        //dropWhile()丢掉从开头开始的尽可能多的元素
        list.stream().dropWhile(i -> i < 50) .forEach(System.out::print);
        System.out.println();

        //ofNullable()
        Stream<Integer> integerStream = Stream.of(1, 2, 3, null);
        integerStream.forEach(System.out::println);
        //of()里不能存储单个null，否则报异常
//        Stream<Integer> integerStream1 = Stream.of(null);
//        integerStream1.forEach(System.out::println);

        //此时允许存入单一null
        Stream<Integer> integerStream1 = Stream.ofNullable(null);
        integerStream1.forEach(System.out::println);
//        System.out.println(integerStream1.count());

        //iterate()新重载方法，让你提供一个判断条件结束迭代
        Stream.iterate(1,i->i+1).limit(10).forEach(System.out::println);
        Stream.iterate(1, i->i<100, i->i+1).forEach(System.out::println);
    }

    //Optional提供stream()
    @Test
    public void test5(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        Optional<List<Integer>> optional = Optional.ofNullable(list);
        Stream<List<Integer>> stream = optional.stream();
//        System.out.println(stream.count());//1

        stream.flatMap(x -> x.stream()).forEach(System.out::println);
    }
}
