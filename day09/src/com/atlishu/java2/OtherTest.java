package com.atlishu.java2;

import com.atlishu.java1.Person;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author lishustart
 * @create 2021-05-06-15:47
 *
 * 获取其他常用结构
 */
public class OtherTest {

    //构造器
    @Test
    public void test1(){
        Class clazz = Person.class;
        //getConstructors()获取当前运行时类中声明为public访问权限的构造器
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor c:constructors) {
            System.out.println(c);
        }
        //getDeclaredConstructors()获取当前运行时类中声明的所有方法
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor c:declaredConstructors) {
            System.out.println(c);
        }
    }

    //获取运行时类的父类
    @Test
    public void test2(){
        Class clazz = Person.class;

        Class superclass = clazz.getSuperclass();
        System.out.println(superclass);
        //获取运行时类带泛型的父类
        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println(genericSuperclass);
        //获得父类的泛型
        ParameterizedType paramType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = paramType.getActualTypeArguments();
        System.out.println(actualTypeArguments[0].getTypeName());
    }

    //接口
    @Test
    public void test3(){
        Class clazz = Person.class;
        Class[] interfaces = clazz.getInterfaces();
        for (Class c:interfaces) {
            System.out.println(c.getName());
        }
        //父类接口
        Class[] interfaces1 = clazz.getSuperclass().getInterfaces();
        for (Class c1:interfaces1) {
            System.out.println(c1.getName());
        }
    }

    //包
    @Test
    public void test4(){
        Class clazz = Person.class;
        Package pack = clazz.getPackage();
        System.out.println(pack);
    }
    //注解
    @Test
    public void test5(){
        Class clazz = Person.class;
        Annotation[] annotations = clazz.getAnnotations();
    }


}
