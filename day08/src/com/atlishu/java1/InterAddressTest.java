package com.atlishu.java1;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author lishustart
 * @create 2021-04-24-16:00
 *
 * 网络编程
 *
 * 1、定位主机和主机上的应用：IP、端口号
 * 2、找到主机之后如何可靠高效地进行数据传输：网络通信协议（TCP/IP参考模型）
 *
 * (1)IP：唯一标识互联网上的主机（通信实体）
 * (2)在java中用InetAddress类代表IP
 * (3)分类：IPV4、IPV6；万维网、局域网
 * (4)域名：www.baidu.com
 * (5)本地回路地址：127.0.0.1 对应：localhost
 * (6)实例化InetAddress：两个方法getByName(String host)、getLocalHost();
 *                      两个常用方法：getHostName()/getHostAddress()
 * (7)端口号：进程，要求不同的进程要有不同的端口号
 * (8)端口号与IP地址的组合得到一个网络套接字：Socket
 *
 */
public class InterAddressTest {
    public static void main(String[] args) {

        try {
            //写法一：IP地址
            InetAddress inet1 = InetAddress.getByName("192.168.200.2");//host主机名
            System.out.println(inet1);
            //写法二：域名
            InetAddress inet2 = InetAddress.getByName("www.baidu.com");
            System.out.println(inet2);//打印对应的IP

            //本机地址
            InetAddress inet3 = InetAddress.getByName("localhost");
            System.out.println(inet3);

            InetAddress inet4 = InetAddress.getLocalHost();
            System.out.println(inet4);
            System.out.println(inet4.getHostName());
            System.out.println(inet4.getHostAddress());


        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
