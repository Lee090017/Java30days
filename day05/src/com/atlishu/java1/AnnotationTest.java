package com.atlishu.java1;

import java.util.ArrayList;

/**
 * @author lishustart
 * @create 2021-03-12-17:03
 *
 * 注解的使用
 *
 * 1.理解Annotation
 *  jdk5.0新增
 *  特殊标记，这些标记可以在编译、类加载、运行前被读取，并执行相应的操作
 *
 * 2.使用举例：
 *  示例一：生成文档相关注解
 *  示例二：在编译时进行格式检测（JDK内置的三个基本注解）
 *      //@Override、@Deprecated、@SuppressWarning
 *  示例三：跟踪代码依赖性，实现替代配置文件功能
 *
 * 3.如何自定义注解：参照SuppressWarnings定义
 *      a.注解声明为：@interface
 *      b.内部定义成员，通常使用value表示
 *      c.可以指定成员的默认值，使用default定义
 *      d.如果自定义注解没有成员，表示是一个标识作用
 *
 * 4.jdk提供的四种元注解（元Annotation用于修饰其他Annotation定义）
 * 四个标准的meta-annotation：
 * ①Retention：指定所修饰的Annotation的生命周期，SOURCE、CLASS（默认）、RUNTIME
 *            只有在声明为RUNTIME的注解，才能用反射获取。
 *
 * ②Target：用于修饰Annotation定义，用于指定被修饰的Annotation能用于修饰哪些程序元素
 *
 * ③Documented：表示所修饰的注解再被javadoc解析时将被保留下来
 *
 * ④inherited：被修饰的Annotation将具有继承性
 *
 * 5.通过反射获取注解信息
 *
 * 6.jdk8新特性：
 *      可重复注解：① 在MyAnnotation上声明@Repeatable，成员是MyAnnotations.class
 *                 ② MyAnnotation的Target和Retention和MyAnnotations相同
 *
 *      类型注解：
 *      ElementType.TYPE_PARAMETER表示该注解能写在类型变量的声明语句中（如：泛型声明）
 *      ElementType.TYPE_USE表示该注解能写在使用类型的任何语句中
 */
public class AnnotationTest {
    public static void main(String[] args) {
        @SuppressWarnings("unused")
        int num = 10;

        @SuppressWarnings({"unused","rawtypes"})
        ArrayList list = new ArrayList();
    }
}

//自定义注解
@MyAnnotation(value = "Hi")//需要给成员赋值
@MyAnnotation(value = "Haha")//可重复注解
class Person{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void walk(){
        System.out.println("走路");
    }

    public void eat(){
        System.out.println("吃饭");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}' ;
    }
}

interface InfoShow{
    void show();
}

class Student extends Person implements InfoShow{

    public Student(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println("吃KFC");;
    }

    @Override
    public void show() {//编译时就在校验
        System.out.println(this.toString());
    }
}

class Generic<@MyAnnotation T>{
    public void show() throws @MyAnnotation RuntimeException{

        ArrayList<@MyAnnotation String>list = new ArrayList<>();

        int num = (@MyAnnotation int)10L;
    }
}