package com.atlishu.java2;

import com.atlishu.java1.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author lishustart
 * @create 2021-05-06-16:26
 *
 * 调用运行时类中指定结构：属性、方法、构造器
 *
 **/

public class RefectionTest {
    @Test
    public void test1() throws Exception {
        Class clazz = Person.class;

        //创建运行时类对象
        Person p = (Person) clazz.newInstance();

        //获取指定的属性，要求运行时类中的属性声明为public
        //通常不采用此方法
        Field id = clazz.getField("id");

        /*
        设置当前属性值

        set():参数1：指明设置哪个对象的属性 参数2：将此属性设置为多少
         */
        id.set(p,1001);

    }

    //如何操作运行时类中指定的属性
    @Test
    public void test2() throws Exception {
        Class clazz = Person.class;

        //创建运行时类对象
        Person p = (Person) clazz.newInstance();

        //getDeclaredField(String Fieldname)获取运行时类中指定变量名的属性
        Field name = clazz.getDeclaredField("name");

        //保证当前属性是可访问的
        name.setAccessible(true);
        //获取、设置指定对象的此属性值
        name.set(p,"Tom");
        System.out.println(name.get(p));

    }

    //如何操作运行时类中指定的方法
    @Test
    public void test3() throws Exception {
        Class clazz = Person.class;

        //创建运行时类对象
        Person p = (Person) clazz.newInstance();

        /*
            1.获取指定的某个方法
            getDeclaredMethod()
            参数1：获取的方法名；参数2：获取的形参列表
         */
        Method show = clazz.getDeclaredMethod("show", String.class);
        //2.保证当前属性是可访问的
        show.setAccessible(true);
        /*
            3.invoke()：参数1：方法的调用者，参数2：给方法形参赋值的实参
            invoke()方法的返回值就是对应类中调用的方法的返回值
         */
        Object returnValue = show.invoke(p, "CHN");
        //String nation = p.show("CHN");
        System.out.println(returnValue);

        System.out.println("----------调用静态方法----------");
        Method showDesc = clazz.getDeclaredMethod("showDesc");
        showDesc.setAccessible(true);
        //如果调用的运行时类没有返回值，则返回null
        showDesc.invoke(clazz);//写法1
        showDesc.invoke(null);//写法2

    }

    //如何操作运行时类中指定的构造器
    @Test
    public void test4() throws Exception {
        Class clazz = Person.class;

        //找到指定构造器
        Constructor declaredConstructor = clazz.getDeclaredConstructor(String.class, int.class);

        //保证当前构造器是可访问的
        declaredConstructor.setAccessible(true);

        //调用此构造器创建运行时类的对象
        Person p = (Person) declaredConstructor.newInstance("Tom", 11);
        System.out.println(p);

    }
}
