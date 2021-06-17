package com.atlishu.java;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author lishustart
 * @create 2021-04-27-22:38
 */
public class ReflectionTest {

    //Reflection之前对于Person类的操作
    @Test
     public void test1(){
        //1.创建Person类的对象
        Person p1 = new Person(18,"Tom");

        //2.调用属性方法
        System.out.println(p1.getAge());
        p1.show();

        //在Person类外部，不可以通过Person类的对象调用其内部私有结构
        //比如name、nation()方法
    }

    @Test
    public void test2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class clazz = Person.class;
        //1、通过反射，创建Person类的对象
        Constructor cons = clazz.getConstructor(int.class,String.class);

        Object obj = cons.newInstance(12, "Tom");
        Person p1 = (Person)obj;
        //2、通过反射，调用对象的属性和方法
        //属性
        Field age = clazz.getDeclaredField("age");
        age.set(p1,20);
        System.out.println(p1.toString());
        //方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p1);
        //通过反射，可以调用Person类的私有结构:如私有构造器、方法、属性
        //私有的构造器
        Constructor cons1 = clazz.getDeclaredConstructor(String.class);
        cons1.setAccessible(true);
        Person p2 = (Person)cons1.newInstance("Jerry");
        System.out.println(p2.toString());
        //私有属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p1,"lishu");
        System.out.println(p1.toString());
        //调用私有的方法
        Method nation = clazz.getDeclaredMethod("nation", String.class);
        nation.setAccessible(true);
        String str = (String)nation.invoke(p1,"中国");//相当于String nation =p1.nation("中国");
        System.out.println(str);
    }

    //疑问：通过直接new对象或者反射都可以调用公共资源，开发中到底用哪个？
    //建议直接用new的方式
    //什么时候会用反射的方式，反射的特征，动态性（编译的时候确定不下来用哪个对象）

    //疑问：反射机制与面向对象的封装性是否矛盾？如何看待两个技术？
    //不矛盾，封装性表示不建议用户调用私有结构

    /*
    关于java.lang.Class类的理解
    1、类的加载过程：
    程序在经过javac.exe命令后，会生成一个或多个字节码文件（.class结尾），接着我们使用java.exe
    命令对某个字节码文件进行解释运行，相当于将某个字节码文件加载到内存中，此过程就称为类的加载。
    加载到内存中的类称为运行时类，此时运行时类，就作为Class的一个实例

    2.Class类的实例对应一个运行时类，不可new

    3.加载到内存中的运行时类，会缓存一定时间，在此时间内，我们可以通过不同方式来获取此运行时类
     */
    //获取Class的实例方式
    @Test
    public void test3() throws ClassNotFoundException {
        //方式一,调用运行时类的属性class
        Class<Person> clazz1 = Person.class;
        System.out.println(clazz1);
        //方式二，通过运行时类的对象，调用getClass()方法
        Person p1 = new Person();
        Class clazz2 = p1.getClass();//Object类里的getClass()方法
        System.out.println(clazz2);
        //方式三，调用Class的静态方法：forName(String classPath)
        Class clazz3 = Class.forName("com.atlishu.java.Person");//类的全类名(包含完整的包名)
        System.out.println(clazz3);

        System.out.println(clazz1 == clazz2);//不同方法调用同一运行时类

        //方式四，使用类的加载器ClassLoader
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class clazz4 = classLoader.loadClass("com.atlishu.java.Person");
        System.out.println(clazz4);

    }

}
