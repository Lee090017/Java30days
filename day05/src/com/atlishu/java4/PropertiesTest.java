package com.atlishu.java4;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author lishustart
 * @create 2021-04-01-16:36
 */
public class PropertiesTest {
    //Properties:常用于处理配置文件，key和value都是String类型的
    public static void main(String[] args){
        FileInputStream fis = null;
        try {
            Properties pro = new Properties();
            fis = new FileInputStream("jdbc.properties");
            pro.load(fis);//加载流对应的文件

            String name = pro.getProperty("name");
            System.out.println(name);

            String password = pro.getProperty("password");
            System.out.println(password);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
