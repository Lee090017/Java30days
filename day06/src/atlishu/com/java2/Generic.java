package atlishu.com.java2;

import org.junit.Test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author lishustart
 * @create 2021-04-02-16:18
 *
 *
 * 1.泛型在继承方面的体现
 *
 * 2.通配符的使用
 *
 * 3.有限制条件的通配符
 *
 */
public class Generic {
    /*
        1.泛型在继承方面的体现
            类A是类B的父类，G<A>和G<B>二者不具备子父类关系，是并列关系
            补充：类A是类B的父类，A<G>是B<G>的父类
     */
    @Test
    public void test1(){
        Object obj = null;
        String str = null;
        obj = str;

        Object[] arr1 = null;
        String[] arr2 = null;
        arr1 = arr2;

        //此时的list1和list2不具有字符类关系
        List<Object> list1 = null;
        List<String> list2 = null;

//        list1 = list2;//编译不通过
        /*
            假设list2可以赋值为list1
            list.add(123)；导致混入非String的数据，出错
         */

    }

    @Test
    public void test2(){
        AbstractList<String> list1 = null;
        List<String> list2 = null;
        ArrayList<String> list3 = null;

        list1 = list3;
        list2 = list3;

        List<String> list4 = new ArrayList<>();
    }

    /*
           2.通配符
     */
    @Test
    public void test3(){
        List<Object> list1 = null;
        List<String> list2 = null;

        List<?> list = null;

        list = list1;
        list = list2;

        show(list1);
        show(list2);

        //
        List<String> list3 = new ArrayList<>();
        list3.add("AA");
        list3.add("BB");
        list3.add("CC");
        list3.add("DD");
        list = list3;
        //添加:对于List<?>就不能向内部添加数据
        //除了添加null
//        list.add("EE");
        list.add(null);
        //允许读,读取数据为Object类型
        Object o = list.get(0);
        System.out.println(o);
    }

    public void show(List<?> list){
        Iterator<?> iterator = list.iterator();
        while(iterator.hasNext()){
            Object obj = iterator.next();
            System.out.println(obj);
        }
    }

    /*
        3.有限制条件的通配符的使用
            ? extends
     */
    @Test
    public void test4(){
        List<? extends Person>list1 = null;//小于等于
        List<? super Person>list2 = null;//大于等于

        List<Student> list3 = null;
        List<Person> list4 = null;
        List<Object> list5 = null;

        list1 = list3;
        list1 = list4;
//        list1 = list5;

//        list2 = list3;
        list2 = list4;
        list2 = list5;

        //读取数据
        list1 = list3;
        Person p =list1.get(0);//多态性
        Object o =list1.get(0);
        //编译不通过
//        Student s = list1.get(0);

        list2 = list4;
        //编译不通过
//        Person c =list2.get(0);
//        Student s =list2.get(0);
        Object b =list2.get(0);

        //写入数据
        //编译不通过
//        list1.add(new Student());
//        list1.add(new Person());
//        list1.add(new Object());

        list2.add(new Person());

    }
}

class Person{

}

class Student extends Person{

}