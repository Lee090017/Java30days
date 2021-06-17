package com.atlishu.java1;

/**
 * @author lishustart
 * @create 2021-03-09-22:06
 *
 * 使用带有泛型参数的方法
 */
public class TSTest {
    /*
      注意：定义带类型参数的方法，其主要目的是为了表达多个参数以及返回值之间的关系。
      例如本例子中T和S的继承关系， 返回值的类型和第一个类型参数的值相同。
    */
    public static <T, S extends T> T testDemo(T t, S s){
        System.out.println("我是T类型，我的类型是："+ t.getClass().getName());
        System.out.println("我是S类型，我的类型是："+ s.getClass().getName());
        return t;
    }

    public static void main(String[] args) {
        Animal a = new Animal();
        Dog d = new Dog();
        Animal a1 = testDemo(a,d);
        System.out.println("我是对象a1，我的类型是："+a1.getClass().getName());
    }

}

class Animal{
    public Animal() {
        System.out.println("我是动物");
    }
}

class Dog extends Animal{
    public Dog() {
        System.out.println("我是狗");
    }
}