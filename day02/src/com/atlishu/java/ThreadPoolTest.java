package com.atlishu.java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author lishustart
 * @create 2020-12-23-15:36
 *
 * 创建多线程方式四（线程池的使用）
 */

class NumberThread implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i <=100; i++) {
            if(i % 2 ==0){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}

class NumberThread1 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i <=100; i++) {
            if(i % 2 !=0){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}

public class ThreadPoolTest {
    public static void main(String[] args) {
        //1.提供指定数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;
        //管理的体现
        //设置线程池的属性
//        service1.setCorePoolSize(15);
//        service1.setKeepAliveTime();


        //2.执行指定线程的操作，需要提供一个实现Runnable接口或者Callable接口实现类的对象
        service.execute(new NumberThread());//适合使用于Runnable
        service.execute(new NumberThread1());//适合使用于Runnable
//        service.submit();//适合适用于Callable
        //3.关闭线程池
        service.shutdown();
    }
}
