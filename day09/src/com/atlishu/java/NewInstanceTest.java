package com.atlishu.java;

import org.junit.Test;

import java.util.Random;

/**
 * @author lishustart
 * @create 2021-04-29-15:43
 *
 * 通过反射创建对应的运行时类的对象
 */
public class NewInstanceTest {
    @Test
    public void test1() throws IllegalAccessException, InstantiationException {
        Class<Person> clazz = Person.class;
        /*
            newInstance():调用此方法，创建对应的运行时类的对象，内部调用了运行时类的空参构造器

            要想此方法正常地创建运行时类的对象，要求：
            1.运行时类必须提供空参构造器
            2.空参构造器访问权限得够，一般是public

            在javabean中要求提供一个public的空参构造器，原因：
            1.便于通过反射，创建运行时类的对象
            2.便于子类继承此运行时类时，默认调用super()，保证子类有此构造器
         */

        Person person = clazz.newInstance();

        System.out.println(person);
    }

    /*
        反射的动态性
        运行时才知道造的是哪个类的对象
     */
    @Test
    public void test3(){
        for (int i = 0; i < 100; i++) {
            int n = new Random().nextInt(3);//0,1,2
            String classPath = "";
            switch (n){
                case 0:
                    classPath = "java.lang.Object";
                    break;
                case 1:
                    classPath = "com.atlishu.java.Person";
                    break;
                case 2:
                    classPath = "java.util.Date";
                    break;
            }
            try {
                System.out.println(getInstance(classPath));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }

    }

    /*
    创建一个类的对象
    classPath:指定类的全类名
     */
    public Object getInstance(String classPath) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class clazz = Class.forName(classPath);
        return clazz.newInstance();
    }
}
