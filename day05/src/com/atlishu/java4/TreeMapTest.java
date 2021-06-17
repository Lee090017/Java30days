package com.atlishu.java4;

import org.junit.Test;

import java.util.*;

/**
 * @author lishustart
 * @create 2021-04-01-16:12
 *
 * TreeMap的排序要求key必须是同一类创建的对象
 * 自然排序和定制排序
 */
public class TreeMapTest {
    @Test
    public void test1(){
        TreeMap treeMap = new TreeMap();
        treeMap.put("BB",423);
        treeMap.put("DD",321);
        treeMap.put("AA",563);
        treeMap.put("CC",987);

        Set set = treeMap.keySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Object key = iterator.next();
            Object value = treeMap.get(key);
            System.out.println(key+":"+value);
        }

    }

    @Test
    public void test2(){
        TreeMap treeMap = new TreeMap();
        treeMap.put(new User("lishu",25),423);
        treeMap.put(new User("yanming",27),321);
        treeMap.put(new User("pengdaom",24),563);
        treeMap.put(new User("tangyige",26),987);

        Set set = treeMap.keySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Object key = iterator.next();
            Object value = treeMap.get(key);
            System.out.println(key+":"+value);
        }

    }

    @Test
    public void test3(){
        Comparator comparator = new Comparator() {
            @Override
            //按照姓名字母从大到小
            public int compare(Object o1, Object o2) {
                if(o1 instanceof User && o2 instanceof User){
                    User u1 = (User)o1;
                    User u2 = (User)o2;
                    return -u1.getName().compareTo(u2.getName());
                }
                throw new RuntimeException("....");
            }
        };

        TreeMap treeMap = new TreeMap(comparator);
        treeMap.put(new User("lishu",25),423);
        treeMap.put(new User("yanming",27),321);
        treeMap.put(new User("pengdaom",24),563);
        treeMap.put(new User("tangyige",26),987);

        Set set = treeMap.keySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Object key = iterator.next();
            Object value = treeMap.get(key);
            System.out.println(key+":"+value);
        }

    }
}

class User implements Comparable{
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    //按年龄大小，从大到小
    public int compareTo(Object o) {
        if(o instanceof User){
            User user = (User)o;
            if(this.age > user.age){
                return -1;
            }else if(this.age < user.age){
                return 1;
            }else return 0;
        }

        throw new RuntimeException("输入数据格式错误");
    }
}