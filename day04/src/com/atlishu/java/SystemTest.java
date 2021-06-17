package com.atlishu.java;

import org.junit.Test;

import java.util.Arrays;
import java.util.Properties;

/**
 * @author lishustart
 * @create 2021-03-09-20:36
 *
 * System类的常用静态方法
 */
public class SystemTest {
    public static void main(String[] args) {
        int[] a = {7,8,9,10,11};
        int[] b = {1,2,3,4,5};
        //从数组a的第二个元素开始，复制到b数组的第三个位置 复制的元素长度为3
        System.arraycopy(a,1,b,2,3);
        System.out.println(Arrays.toString(b));
        System.out.println("当前时间："+System.currentTimeMillis());
        //获取指定键的系统属性
        System.out.println("java的版本信息："+System.getProperty("java.version"));
        //运行垃圾收集器
        System.gc();
        //终止当前正在运行的Java虚拟机，status为 0时退出
        System.exit(0);
    }

    @Test
    public void test(){
        //获取java的安装目录
        System.out.println("java的安装目录为"+System.getProperty("java.home"));
        Properties props=System.getProperties(); //系统属性
        System.out.println("Java的运行环境版本："+props.getProperty("java.version"));
        System.out.println("Java的运行环境供应商："+props.getProperty("java.vendor"));
        System.out.println("Java供应商的URL："+props.getProperty("java.vendor.url"));
        System.out.println("Java的安装路径："+props.getProperty("java.home"));
        System.out.println("Java的虚拟机规范版本："+props.getProperty("java.vm.specification.version"));
        System.out.println("Java的虚拟机规范供应商："+props.getProperty("java.vm.specification.vendor"));
        System.out.println("Java的虚拟机规范名称："+props.getProperty("java.vm.specification.name"));
        System.out.println("Java的虚拟机实现版本："+props.getProperty("java.vm.version"));
        System.out.println("Java的虚拟机实现供应商："+props.getProperty("java.vm.vendor"));
        System.out.println("Java的虚拟机实现名称："+props.getProperty("java.vm.name"));
        System.out.println("Java运行时环境规范版本："+props.getProperty("java.specification.version"));
        System.out.println("Java运行时环境规范供应商："+props.getProperty("java.specification.vender"));
        System.out.println("Java运行时环境规范名称："+props.getProperty("java.specification.name"));
        System.out.println("Java的类格式版本号："+props.getProperty("java.class.version"));
        System.out.println("Java的类路径："+props.getProperty("java.class.path"));
        System.out.println("加载库时搜索的路径列表："+props.getProperty("java.library.path"));
        System.out.println("默认的临时文件路径："+props.getProperty("java.io.tmpdir"));
        System.out.println("一个或多个扩展目录的路径："+props.getProperty("java.ext.dirs"));
        System.out.println("操作系统的名称："+props.getProperty("os.name"));
        System.out.println("操作系统的构架："+props.getProperty("os.arch"));
        System.out.println("操作系统的版本："+props.getProperty("os.version"));
        System.out.println("文件分隔符："+props.getProperty("file.separator"));   //在 unix 系统中是＂／＂
        System.out.println("路径分隔符："+props.getProperty("path.separator"));   //在 unix 系统中是＂:＂
        System.out.println("行分隔符："+props.getProperty("line.separator"));   //在 unix 系统中是＂/n＂
        System.out.println("用户的账户名称："+props.getProperty("user.name"));
        System.out.println("用户的主目录："+props.getProperty("user.home"));
        System.out.println("用户的当前工作目录："+props.getProperty("user.dir"));
    }

}
