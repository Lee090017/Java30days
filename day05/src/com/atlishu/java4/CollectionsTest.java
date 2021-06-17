package com.atlishu.java4;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author lishustart
 * @create 2021-04-01-17:03
 *
 * Collections：操作Collection、Map的工具类
 *
 * 面试Collection与Collections的区别
 *
 */
public class CollectionsTest {
    @Test
    public void test1(){
        List list = new ArrayList();
        List l = Arrays.asList(56,44,32,12,22,51);
        list.addAll(l);

        System.out.println(list);

        //sort()
        Collections.sort(list);
        System.out.println(list);

        //reverse()
        Collections.reverse(list);
        System.out.println(list);

        //shuffle()随机处理
        Collections.shuffle(list);
        System.out.println(list);

        //swap()
        Collections.swap(list,1,2);
        System.out.println(list);

        System.out.println(Collections.max(list));
        System.out.println(Collections.min(list));

        //frequency()
        list.add(22);
        System.out.println(Collections.frequency(list,22));//出现频率

        //copy()
        //错误写法
//        List list1 = new ArrayList();
//        Collections.copy(list1,list);
//        System.out.println(list1);//报错： Source does not fit in dest

        List list1 = new ArrayList();
        List l1 = Arrays.asList(new Object[list.size()]);//等量的null
        list1.addAll(l1);
        Collections.copy(list1,list);
        System.out.println(list1);

        //Collections里提供多个synchronizedXxx()方法
        List list2 = Collections.synchronizedList(list);//将该集合变为线程安全的集合

    }
}
