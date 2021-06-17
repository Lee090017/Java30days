package com.atlishu.java2;

import org.junit.Test;

/**
 * @author lishustart
 * @create 2021-03-14-17:35
 *
 * 枚举类的复习
 */
public class ReviewEnumTest {
    @Test
    public void test1(){
        Person p1 = Person.PROFESSOR;
        System.out.println(p1.getName());

        System.out.println(Person.STUDENT);

        p1.show();

    }
    @Test
    public void test2(){
        Animal animal = Animal.CAT;
        System.out.println(animal);//已经重写了toString，返回对象的名

        Animal[] animals = Animal.values();//返回对象数组
        for (int i = 0; i < animals.length; i++) {
            System.out.println(animals[i]);
        }

        String name = "BIRD";
        Animal animal1 = Animal.valueOf(name);//将字符串转换为同名枚举类
        System.out.println(animal1);

    }

    @Test
    public void test3(){
        Animal animal = Animal.CAT;
        animal.show();
    }

}

/*
    方式一：自定义枚举类
*/
class Person implements Info{
    private final String name;
    private final int age;

    private Person(String name,int age){
        this.name = name;
        this.age = age;
    }

    public static final Person STUDENT = new Person("lishu",25);
    public static final Person PROFESSOR = new Person("Wangyi",40);

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}' ;
    }

    @Override
    public void show() {
        System.out.println("搞开发");
    }
}

/*
    方式二：用Enum关键字实现枚举类
 */

enum Animal implements Info{
    DOG("柴犬",20){
        @Override
        public void show() {
            System.out.println("吃骨头");
        }
    },
    CAT("英短",10){
        @Override
        public void show() {
            System.out.println("吃鱼");
        }
    },
    BIRD("鹦鹉",5){
        @Override
        public void show() {
            System.out.println("吃虫");
        }
    };

    private final String name;
    private final int number;

    private Animal(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

}

interface Info{

    public abstract void show();

}
