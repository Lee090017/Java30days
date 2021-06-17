package com.atlishu.java;

/**
 * @author lishustart
 * @create 2021-04-27-22:39
 */
public class Person {
    public int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Person() {

    }

    private Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show(){
        System.out.println("I'm a good kid.");
    }

    private String nation(String n){
        System.out.println("我的国际是"+n);;
        return n;

    }
}
