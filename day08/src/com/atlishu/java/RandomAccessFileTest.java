package com.atlishu.java;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * @author lishustart
 * @create 2021-04-22-21:49
 *
 * RandomAccessFile的使用
 * 1.RandomAccessFile直接继承于java.lang.Object类，实现了DataInput和DataOutput接口
 * 2.RandomAccessFile既可以作为一个输入流也可以作为一个输出流
 * 3.如果RandomAccessFile作为输出流出现，写出的文件如果不存在，自动创建，如果写出的文件存在，则会对原有文件内容进行覆盖
 * 4.可以通过相关操作，实现RandomAccessFile“插入”数据操作
 *
 */
public class RandomAccessFileTest {

    @Test
    public void test1() throws IOException {
        RandomAccessFile raf1 = new RandomAccessFile(new File("c:\\io\\故障电弧探测器1.jpg"),"r");
        RandomAccessFile raf2 = new RandomAccessFile(new File("c:\\io\\故障电弧探测器1(复制).jpg"),"rw");

        byte[] buffer = new byte[1024];
        int len;
       while (( len=raf1.read(buffer))!=-1){
           raf2.write(buffer,0,len);
       }

       raf1.close();
       raf2.close();
    }

    @Test
    public void test2() throws IOException {
        RandomAccessFile raf1 = new RandomAccessFile(new File("hello.txt"),"rw");

        raf1.write("xyz".getBytes());//假如写出的文件存在，则对原有数据进行覆盖

        raf1.close();
    }

    /*
        在指定的位置进行插入操作
        在abcdef的abc后面插入xyz
     */
    @Test
    public void test3() throws IOException {
        RandomAccessFile raf1 = new RandomAccessFile(new File("hello.txt"),"rw");

        byte[] bytes = new byte[(int)raf1.length()];
        int len = raf1.read(bytes);
        byte[] bytes1 = Arrays.copyOfRange(bytes,3,bytes.length);
        raf1.seek(3);//将角标调到3的位置
        raf1.write("xyz".getBytes());
//        System.out.println(raf1.getFilePointer());//6
        raf1.write(bytes1);

        raf1.close();
    }
}
