package com.atlishu.java2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author lishustart
 * @create 2021-03-29-21:31
 *
 * 集合元素的遍历操作，使用Iterator接口
 */
public class IteratorTest {

    @Test
    public void test1(){
        Collection coll = new ArrayList();
        coll.add("AA");
        coll.add("BB");
        coll.add(123);//自动装箱
        coll.add(new String("Tom"));
        Student s = new Student("Jerry");
        coll.add(s);

        Iterator iterator = coll.iterator();
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());

        //不推荐
//        for (int i = 0; i <coll.size() ; i++) {
//            System.out.println(iterator.next());
//        }

        //推荐
        while(iterator.hasNext()){//判断还有没有元素
            System.out.println(iterator.next());
        }

        //两种错误写法
//        while(iterator.next() != null){
//            System.out.println(iterator.next());
//        }//将会跳跃输出，并且报错

//        while(coll.iterator().hasNext()){
//            System.out.println(iterator.next());
//        }//死循环，不断输出123
    }

    @Test
    public void test2(){
        //迭代器的remove方法的使用
        Collection coll = new ArrayList();
        coll.add("AA");
        coll.add("BB");
        coll.add(123);//自动装箱
        coll.add(new String("Tom"));
        Student s = new Student("Jerry");
        coll.add(s);
        coll.add(456);
        System.out.println(coll);

        Iterator iterator = coll.iterator();

        //删除集合中的TOM
        while(iterator.hasNext()){
            Object obj = iterator.next();
            if("Tom".equals(obj)){
                iterator.remove();//remove方法可以移除该元素
            }else{
                System.out.println(obj);
            }
        }

        System.out.println(coll);

    }
}
