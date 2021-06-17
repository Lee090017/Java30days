package com.atlishu.java;

import java.io.Serializable;

/**
 * @author lishustart
 * @create 2021-04-22-20:37
 *
 * Person类要想可序列化，要满足以下条件：
 * 1.需要实现标识接口Serializable
 * 2.为当前类提供一个全局常量：serialVersionUID
 * 3.除了当前Person类需要实现Serializable接口外，还必须保证其内部所有属性也必须是可序列化的（默认情况下，基本数据类型可序列化）
 */
public class Person implements Serializable {

    static final long serialVersionUID = 12121212121L;//序列版本号

    private String name;
    private int age;
    private int id;
    private Account account;

    public Person(String name, int age, int id, Account account) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.account = account;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                ", account=" + account +
                '}';
    }
}

class Account implements Serializable{
    static final long serialVersionUID = 45214545L;

    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                '}';
    }
}