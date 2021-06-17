package com.atlishu.java2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author lishustart
 * @create 2021-03-30-9:58
 *
 * List接口框架
 * /----Collection接口：单列集合，用来存储一个一个的对象
 *      /----List接口：存储有序的，可重复的数据 --->“动态数组”
 *             /----ArrayList：作为List接口的主要实现类，线程不安全，效率高，底层用 Object[] elementDate存储
 *             /----LinkedList：底层使用的是双向链表存储，对于频繁的插入和删除操作，使用此类效率比ArrayList高
 *             /----Vector：作为List接口的古老实现类，线程安全，效率低，底层用 Object[]存储
 *
 * ArrayList源码分析：jdk 7情况下
 *      ArrayList list = new ArrayList();//底层创建长度是10的Object[]数组elementDate
 *      list.add(123);//elementDate[0]=new Integer(123);
 *      ...
 *      list.add(11);//如果此次添加导致底层的elementDate数组容量不够，则扩容
 *      默认情况下，扩容为原来的1.5倍，同时需要将原来的数组中的数据复制到新的数组中
 *
 *      结论：建议开发中使用带参的构造器： ArrayList list = new ArrayList(50);避免中间环节扩容
 *
 * jdk 8情况下ArrayList的变化
 *      ArrayList list = new ArrayList();//底层Object[] elementDate初始化为{}，并没有创建长度为10的数组
 *      list.add(123)；//第一次调用add()时，底层才创建了长度为10 的数组，并将数据123添加到elementDate
 *
 * LinkedList源码分析：
 *      LinkedList list = new LinkedList();//内部声明了Node类型的first和ate属性，默认值为null
 *      LinkedList.add(123);//将123封装到Node中，创建Node对象
 *      其中Node定义体现了LinkedList的双向列表（不涉及扩容）
 *
 * Vector源码分析：
 *      jdk7/8中通过Vector()构造器创建对象时，底层都创建了长度为10的数组，要是扩容时，长度变为
 *      原来的2倍。
 *
 *
 * 面试题：比较三者的异同？
 * 同：三个类都实现了List接口，存储的数据都是有序可重复的数据
 *
 *
 * List接口中的常用方法
 */
public class ListTest {
    @Test
    public void test1(){

        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add(new Student("TOM"));
        list.add("AA");

        //add(int index,Object ele)
        list.add(1,"插入数据");
        System.out.println(list);

        //addAll(int index,Collection eles)
        List list2 = Arrays.asList("AA","BB");
        list.addAll(list2);
        System.out.println(list);

        //Object get(int index)
        System.out.println(list2.get(1));

        //int indexOf(Object obj)
        System.out.println(list.indexOf("AA"));

        //int lastIndexOf(Object obj)
        System.out.println(list.lastIndexOf("AA"));

        //Object remove(int index)
        list.remove(3);
        System.out.println(list);

        //Object set(int index,Object ele)
        list.set(1,"HaHaHa");
        System.out.println(list);

        //List subList(int fromIndex,int toIndex)
        List list1 = list.subList(1, 3);
        System.out.println(list1);

        //遍历
        //方式一
        System.out.println("***************");
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        //方式二
        System.out.println("***************");
        for (Object obj:list
             ) {
            System.out.println(obj);
        }
        //方式三
        System.out.println("***************");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    /*
        面试题，区分list和collection的remove方法
     */
    @Test
    public void test3(){
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        updateList(list);
        System.out.println(list);

    }

    private void updateList(List list){
//        list.remove(2);//该方法为删除索引
        list.remove(new Integer(2));//[1,3]
    }
}
