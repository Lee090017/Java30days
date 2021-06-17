package com.atlishu.java1;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lishustart
 * @create 2021-04-27-15:00
 *
 * TCP网络编程
 * 例题3：客户端发送文件给服务器，服务器保存数据到本地，并返回“发送成功”给客户端
 * 并关闭相应连接
 *
 *
 */
public class TCPTest3 {
    @Test
    public void client() throws IOException {//异常用简写代替
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"),7878);

        OutputStream os = socket.getOutputStream();

        //本地读取文件流
        FileInputStream fis = new FileInputStream(new File("猫.jpg"));
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer))!=-1){
            os.write(buffer,0,len);
        }
        //关闭数据的输出
        socket.shutdownOutput();

        //接收确认响应
        InputStream is = socket.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = new byte[20];
        int len1;
        while((len1 = is.read(bytes))!=-1){
            baos.write(bytes,0,len1);
        }
        System.out.println(baos.toString());

        fis.close();
        os.close();
        baos.close();
        is.close();
        socket.close();
    }

    @Test
    public void server() throws IOException {
        ServerSocket ss = new ServerSocket(7878);
        Socket socket = ss.accept();
        InputStream is = socket.getInputStream();

//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileOutputStream fos = new FileOutputStream(new File("传输文件.jpg"));
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        byte[] buffer = new byte[1024];
        int len;
        //read()方法是一个阻塞方法，需要客户端发送“发送完成”指令，否则不能跳出循环
        while ((len = is.read(buffer))!=-1){
            bos.write(buffer,0,len);
        }
        System.out.println("接收到来自client："+socket.getInetAddress().getHostName()+"的图片");


        //发送确认响应

        OutputStream os = socket.getOutputStream();
        os.write("发送成功".getBytes());

        bos.close();
        is.close();
        os.close();
        socket.close();
        ss.close();
    }
}
