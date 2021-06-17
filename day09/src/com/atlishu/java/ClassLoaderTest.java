package com.atlishu.java;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author lishustart
 * @create 2021-04-29-11:15
 *
 * 了解类的加载器
 *
 */
public class ClassLoaderTest {
    @Test
    public  void test1(){
        //对应自定义类使用系统类加载器
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);
        //调用系统类加载器的getParent()，可以获取扩展类加载器
        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println(classLoader1);
        //调用扩展类加载器的getParent()，无法获取引导类加载器

        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);
        //引导类加载器主要负责价值java的核心类库，无法加载自定义类的
        ClassLoader classLoader3 = String.class.getClassLoader();
        System.out.println(classLoader3);
    }

    /*
        Properties:用来读取配置文件

     */
    @Test
    public void test2() throws IOException {
        Properties properties = new Properties();
        //此时文件默认在当前的module下：
        //读取配置文件方式1
//        FileInputStream fis = new FileInputStream("jdbc.properties");
//        properties.load(fis);

        //读取配置方式2，使用ClassLoader
        //配置文件默认识别为：当前module的src下
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("jdbc.properties");
        properties.load(is);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        System.out.println("user="+user+",password="+password);

        is.close();

    }
}
