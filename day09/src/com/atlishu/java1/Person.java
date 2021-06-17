package com.atlishu.java1;

/**
 * @author lishustart
 * @create 2021-04-29-16:43
 *
 * 拥有丰富结构的Person类
 */
@MyAnnotation(value = "hi")
public class Person extends Creature<String> implements Comparable<String>,MyInterface{
    private String name;
    int age;
    public int id;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @MyAnnotation(value = "aaa")
    public Person(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    private String show(String nation) throws NullPointerException,ClassCastException{
        System.out.println("I come from "+nation);
        return nation;
    }

    @MyAnnotation
    public String display(String interest){
        return interest;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public void info() {
        System.out.println("我是一个人");
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }

    private static void showDesc(){
        System.out.println("这是一个静态方法");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
