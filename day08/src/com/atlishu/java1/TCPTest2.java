package com.atlishu.java1;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lishustart
 * @create 2021-04-27-10:46
 *
 * TCP网络编程
 * 例题2：客户端发送文件给服务器，服务器保存数据到本地
 *
 */
public class TCPTest2 {
    @Test
    public void client() throws IOException {//异常用简写代替
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"),9090);

        OutputStream os = socket.getOutputStream();

        //本地读取文件流
        FileInputStream fis = new FileInputStream(new File("猫.jpg"));
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer))!=-1){
            os.write(buffer,0,len);
        }

        fis.close();
        os.close();
        socket.close();
    }

    @Test
    public void server() throws IOException {
        ServerSocket ss = new ServerSocket(9090);
        Socket socket = ss.accept();
        InputStream is = socket.getInputStream();

//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileOutputStream fos = new FileOutputStream(new File("传输文件.jpg"));
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer))!=-1){
            bos.write(buffer,0,len);
        }
        System.out.println("接收到来自client："+socket.getInetAddress().getHostName()+"的图片");

        bos.close();
        is.close();
        socket.close();
        ss.close();
    }
}
