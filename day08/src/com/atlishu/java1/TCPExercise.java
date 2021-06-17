package com.atlishu.java1;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lishustart
 * @create 2021-04-27-15:44
 *
 * 客户端给服务器发送文本，服务器将文本转成大写返回给客户端
 *
 */
public class TCPExercise {
    @Test
    public void client() throws IOException {
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9510);
        OutputStream os = socket.getOutputStream();

        FileInputStream fis = new FileInputStream(new File("alphabet.txt"));
        byte[] buffer = new byte[20];
        int len;
        while((len = fis.read(buffer))!=-1){
            os.write(buffer,0,len);
        }
        socket.shutdownOutput();

        FileOutputStream fos = new FileOutputStream(new File("upper_case_alphabet.txt"));
        InputStream is = socket.getInputStream();
        byte[] bytes = new byte[20];
        int len1;
        while((len1 = is.read(bytes))!=-1){
            fos.write(bytes,0,len1);
        }

        is.close();
        fos.close();
        fis.close();
        os.close();
        socket.close();
    }

    @Test
    public void server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(9510);
        Socket socket = serverSocket.accept();
        InputStream is = socket.getInputStream();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[20];
        int len;
        while((len = is.read(buffer))!=-1){
            baos.write(buffer,0,len);
        }
        String str = baos.toString();
        String upperstr = str.toUpperCase();

        OutputStream os = socket.getOutputStream();
        os.write(upperstr.getBytes());

        os.close();
        baos.close();
        is.close();
        socket.close();
        serverSocket.close();

    }
}
