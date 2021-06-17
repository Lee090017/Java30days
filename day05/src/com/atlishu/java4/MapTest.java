package com.atlishu.java4;

import org.junit.Test;

import java.util.*;

/**
 * @author lishustart
 * @create 2021-03-31-10:07
 *
 *  /---Map:双列数据，用于存储具有Key-Value对的数据  ---类似于函数 y=f(x)
 *      /---HashMap：作为主要的实现类，线程不安全，效率高，储存null的key和value
 *          /---LinkedHashMap：保证在遍历Map元素时，可以按照添加的顺序实现遍历
 *              原因：在原有的HashMap底层结构基础上，添加一对指针，指向前一个和后一个数据
 *              对于频繁的遍历操作，此类执行效率高于HashMap
 *      /---TreeMap：保证按照添加的key-value对进行排序，实现排序遍历（按照key来排序）
 *                  底层使用的是红黑树
 *      /---Hashtable：古老的实现类，线程安全，效率低,不能储存null的key和value
 *          /---Properties：常用于处理配置文件，key和value都是String类型
 *
 *  HashMap的底层：数组+链表（jdk7）
 *                数组+链表+红黑树（jdk8）
 *
 *  二、HashMap结构的理解
 *  Map中的key是无序的不可重复的，使用Set存储所有key--->key所在的类也要重写equals()和hashCode()（以HashMap为例）
 *  Map中的value是无序的可重复的，使用Connection存储所有value--->value所在的类要重写equals()
 *  一个键值对：key-value构成一个Entry对象
 *  Map中的Entry是无序的不可重复的，使用Set储存所有的entry
 *
 *  三、HashMap的底层实现原理（jdk7）
 *  HashMap map = new HashMap()
 *  在实例化以后，底层创建了长度是16的一维数组Entr[] table
 *  ...可能已经执行过多次put...
 *  map.put(key1,value1)
 *  首先，调用key1所在类的hashCode()计算key1哈希值，此哈希值经过某种计算，得到Entry数组中的存放位置
 *  如果此位置上的数据为空，此时的key1-value1添加成功---情况1
 *  如果此位置上的数据不为空，此位置上存在一个或多个数据（以链表的形式存放），比较key1和以及存在的一个或多个数据的
 *  hash值：
 *      如果key1的hash值与已经存在的hash值都不相同，key1-value1添加成功---情况2
 *      如果key1的hash值与已经存在的hash值相同，继续比较key1所在类的equals()方法，比较：
 *          如果equals返回false，此时key1-value1添加成功---情况3
 *          如果equals返回true，此时key1-value1将修改原来的value值
 *
 * 补充：关于情况2和3，此时的key1-value1和原来的数据以链表的方式存储
 *
 * 在不断添加的过程中，会涉及到扩容问题，当超出临界值，且要存放的位置非空时，默认的扩容方式，扩容为原来的2倍，并将原来的数据复制过来
 *
 * jdk8 相较于jdk7在底层实现方面的不同：
 * 1、new HashMap()：底层没有创建一个长度为16的数组
 * 2、jdk 8底层的数组是：Node[]，而非Entry[]
 * 3、首次调用put时，底层创建长度为16的数组
 * 4、jdk7底层结构数组+链表，jdk8的底层结构数组+链表+红黑树
 *  当数组的某一个索引位置上的元素以链表的形式存在的数据个数>8且当前数组长度>64时，此时的索引位置上所有元素改为红黑树
 *
 * 四、LinkedHashMap底层实现原理
 * 源码中：
 *      static class Entry<K,V> extends HashMap.Node<K,V> {
 *              Entry<K,V> before, after;//能够记录添加元素的先后顺序
 *              Entry(int hash, K key, V value, Node<K,V> next) {
 *                  super(hash, key, value, next);
 *              }
 *      }
 *
 *
 */
public class MapTest {

    @Test
    public void test1(){
        HashMap hashMap = new HashMap();
        //HashMap可以放入null空键
        hashMap.put(null,123);

        //Hashtable不能放入null空键
//        Map map = new Hashtable();
//        map.put(null,123);

    }

    @Test
    public void test2(){
        HashMap hashMap = new HashMap();
        hashMap.put(123,"AA");
        hashMap.put(345,"BB");
        hashMap.put(12,"CC");
        System.out.println(hashMap);

        //将集合按添加顺序打印
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(123,"AA");
        linkedHashMap.put(345,"BB");
        linkedHashMap.put(12,"CC");
        System.out.println(linkedHashMap);
    }

    @Test
    public void test3(){
        Map map = new HashMap();
        map.put("AA",123);//添加
        map.put("BB",456);
        map.put("CC",789);
        map.put("AA",321);//修改
        System.out.println(map);

        Map map1 = new HashMap();
        map1.put("DD",123);
        map1.put("EE",456);
        map.putAll(map1);//添加所有数据
        System.out.println(map);

        map.remove("BB");//移除键值对
        System.out.println(map);

        map.clear();//clear()为清除数据，不等于将map变为null
        System.out.println(map.size());//0

        Object value = map.get("DD");
        System.out.println(value);
    }

    @Test
    public void test4(){
        Map map = new HashMap();
        map.put("AA",123);//添加
        map.put("BB",456);
        map.put("CC",789);


        //查询
        Object value = map.get("BB");
        System.out.println(value);

        //判断是否存在某值
        boolean keyIsExis = map.containsKey("AA");
        System.out.println(keyIsExis);

        boolean valueIsExis = map.containsValue(111);
        System.out.println(valueIsExis);

        System.out.println(map.size());

        System.out.println(map.isEmpty());


    }

    @Test
    public void test5(){
        //遍历Map
        /*
            Set keySet():返回所有key构成的Set集合
            Collection values():返回所有value构成的Collection集合
            Set entrySet():返回所有key-value对构成的Set集合
         */
        Map map = new HashMap();
        map.put("AA",123);//添加
        map.put("BB",456);
        map.put("CC",789);
        map.put("DD",220);

        Set set1 = map.keySet();
        System.out.println(set1);
        Iterator iterator = set1.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        Collection collection1 = map.values();
        System.out.println(collection1);
        for (Object obj : collection1
             ) {
            System.out.println(obj);
        }//遍历顺序和key相同，底层都是hash值的索引

        Set set2 = map.entrySet();
        System.out.println(set2);
        //方式一
        for (Object obj : set2
        ) {
            //entrySet集合中的元素都是entry
            Map.Entry entry = (Map.Entry) obj;
//            System.out.println(entry);
            System.out.println(entry.getKey()+"--->"+entry.getValue());
        }

        //方式二
        Set set3 = map.keySet();
        Iterator iterator1 = set3.iterator();
        while(iterator1.hasNext()){
            Object key = iterator1.next();
            System.out.println(map.get(key));
        }

    }
}
