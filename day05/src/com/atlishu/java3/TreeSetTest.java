package com.atlishu.java3;

import org.junit.Test;

import java.util.*;

/**
 * @author lishustart
 * @create 2021-03-30-17:23
 *
 * TreeSet要求添加的数据必须是相同类的对象
 *
 * 自然排序中，比较两个对象是否相同的标准为：compareTo()返回0，不再是equals()
 *
 */
public class TreeSetTest {

    @Test
    public void test1(){
        //举例一
        Set set = new TreeSet();
        set.add(12);
        set.add(2);
        set.add(-5);
        set.add(10);
        set.add(0);

        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println("*************");
        //举例二
        Set set1 = new TreeSet();
        set1.add(new Person("Tom",14));
        set1.add(new Person("Jerry",13));
        set1.add(new Person("Mary",15));
        set1.add(new Person("Bob",14));

        Iterator iterator1 = set1.iterator();
        while(iterator1.hasNext()){
            System.out.println(iterator1.next());
        }

        System.out.println("*************");
        //举例三
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Student && o2 instanceof Student){
                    Student stu1 = (Student)o1;
                    Student stu2 = (Student)o2;
                    if(stu1.getAge() > stu2.getAge()){
                        return 1;
                    }else if(stu1.getAge() < stu2.getAge()){
                        return -1;
                    }else{
                        return -stu1.getName().compareTo(stu2.getName());
                    }
                }
                throw new RuntimeException("输入数据格式错误");
            }
        };

        Set set2 = new TreeSet(comparator);
        set2.add(new Student("Tom",14));
        set2.add(new Student("Jerry",13));
        set2.add(new Student("Mary",15));
        set2.add(new Student("Bob",14));

        Iterator iterator2 = set2.iterator();
        while(iterator2.hasNext()){
            System.out.println(iterator2.next());
        }

    }
}

class Person implements Comparable{
    private String name;
    private int age;

    public Person(String name,int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public int compareTo(Object o) {
        //先按照年龄从小到大，姓名按照从到小排列
        if(o instanceof Person){
            Person p = (Person)o;
            if(this.age > p.age){
                return 1;
            }else if(this.age < p.age){
                return -1;
            }else{
                return -this.name.compareTo(p.name);
            }
        }

        throw new RuntimeException("传入的数据类型不一致");
    }
}
