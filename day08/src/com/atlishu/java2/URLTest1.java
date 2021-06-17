package com.atlishu.java2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author lishustart
 * @create 2021-04-27-21:10
 */
public class URLTest1 {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://mqtt.heclouds.com:6002/");

        URLConnection urlConnection = (HttpURLConnection)url.openConnection();

        urlConnection.connect();

        InputStream is = urlConnection.getInputStream();
        FileOutputStream fos = new FileOutputStream(new File("day08\\数据点.txt"));

        byte[] buffer = new byte[1024];
        int len;
        while((len = is.read(buffer))!=-1){
            fos.write(buffer,0,len);
        }

        fos.close();
        is.close();
    }
}
