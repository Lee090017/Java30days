package com.atlishu.java;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author lishustart
 * @create 2020-12-23-11:30
 *
 * 创建多线程的方式三（实现Callable接口）
 *
 * 如何理解实现Callable接口的方式比实现Runnable接口创建多线程方式强大
 * 1.call方法可以有返回值
 * 2.call方法可以抛出异常，被外面捕获，获取异常信息
 * 3.Callable支持泛型
 */

//1.创建一个实现Callable的实现类
class NumThread implements Callable{
    //2.实现call方法，将此线程要执行的操作声明在call方法中
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 1; i <=100 ; i++) {
            if(i % 2 == 0){
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}

public class CallableTest {
    public static void main(String[] args) {
        //3.创建Callable接口实现类的对象
        NumThread num = new NumThread();
        //4.将Callable接口的实现类对象作为传递到FutureTask构造器中，创建FutureTask的对象
        FutureTask futureTask = new FutureTask(num);
        //5.将FutureTask的对象作为传递到Thread构造器中，创建Thread的对象，并调用start方法
        new Thread(futureTask).start();
        //6.获取Callable中call方法的返回值
        try {
//            get()方法的返回值即为FutureTask构造器参数Callable实现类重写的call()返回值
            Object sum = futureTask.get();
            System.out.println("总和为"+sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
