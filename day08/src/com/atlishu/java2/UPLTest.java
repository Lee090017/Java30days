package com.atlishu.java2;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author lishustart
 * @create 2021-04-27-17:44
 *
 * UPL的网络编程
 * 1.UPL：统一资源定位符，对应着互联网某一资源地址
 * 2.格式：
 * 协议 主机名 端口号 资源地址 片段名 ？参数列表
 */
public class UPLTest {
    public static void main(String[] args) {

        try {
            URL url = new URL("https://open.iot.10086.cn/develop/mqtt/product/#/device/list/showstream/401277/709067149");

            //获取该URL协议名
            System.out.println(url.getProtocol());
            //获取该URL的主机名
            System.out.println(url.getHost());
            //获取URL端口名
            System.out.println(url.getPort());
            //获取URL文件路径
            System.out.println(url.getPath());
            //获取URL的文件名
            System.out.println(url.getFile());
            //获取URL查询名
            System.out.println(url.getQuery());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
