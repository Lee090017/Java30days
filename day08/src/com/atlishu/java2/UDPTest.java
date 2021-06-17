package com.atlishu.java2;

import org.junit.Test;

import java.io.IOException;
import java.net.*;

/**
 * @author lishustart
 * @create 2021-04-27-17:03
 *
 * UDP网络编程
 *
 */
public class UDPTest {

    //发送端
    @Test
    public void send() throws IOException {
        DatagramSocket socket = new DatagramSocket();

        String str = "我是UDP方式发送的数据";
        byte[] data = str.getBytes();
        DatagramPacket packet = new DatagramPacket(data,0,data.length, InetAddress.getByName("127.0.0.1"),9090);
        socket.send(packet);

        socket.close();
    }

    @Test
    public void receiver() throws IOException {
        DatagramSocket socket = new DatagramSocket(9090);

        byte[] buffer = new byte[100];
        DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);

        socket.receive(packet);

        System.out.println(new String(packet.getData(),0,packet.getLength()));

        socket.close();
    }
}
