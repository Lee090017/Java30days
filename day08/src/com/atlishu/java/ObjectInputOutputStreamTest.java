package com.atlishu.java;

import org.junit.Test;

import java.io.*;

/**
 * @author lishustart
 * @create 2021-04-22-20:24
 *
 * 对象流的使用
 * 1、ObjectInputStream和ObjectOutputStream
 * 2、作用：用于存储基本数据类型或者对象的处理流
 * 3、要想序列化类需要满足以下的要求，
 * ①实现两个接口Serializabel和Externalizable
 * ②需要serialVersionUID作为序列的版本号
 * ③要保证内部所有属性也必须是可序列化的
 *
 * 4.不能序列化static和transient修饰的成员变量
 *
 *
 */
public class ObjectInputOutputStreamTest {
    /*
        序列化过程：将内存中的java对象保存到磁盘中或者通过网络传输出去
        使用ObjectOutputStream实现
     */
    @Test
    public void testObjectOutputStream() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.dat"));

        oos.writeObject(new String("我爱北京天安门"));
        oos.flush();//刷新操作

        oos.writeObject(new Person("lishu",25,1234,new Account(5000000)));
        oos.flush();

        oos.close();
    }

    /*
        反序列化过程：将磁盘中的对象读出来还原为java对象
        使用ObjectInputStream实现
     */
    @Test
    public void testObjectInputStream() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.dat"));

        //也需要按顺序进行反序列化
        Object obj1 = ois.readObject();
        System.out.println(obj1);

        Object obj2 = ois.readObject();
        System.out.println(obj2);

        ois.close();
    }

}
