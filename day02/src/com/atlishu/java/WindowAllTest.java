package com.atlishu.java;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lishustart
 * @create 2020-12-22-21:19
 *
 * 该程序用于复习各类多线程创建的问题
 */

class Windown1 extends Thread{

    private static int tickets = 100;

    public void Windown1(){

    }

    @Override
    public void run() {
        while(true){
            synchronized (Windown1.class){
                if(tickets > 0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"：票号为"+tickets);
                    tickets--;
                }else{
                    System.out.println(Thread.currentThread().getName()+"：票已售完");
                    break;
                }
            }
        }

    }
}

class Windown2 implements Runnable{

    private int ticket = 100;
    private boolean flag = true;

    @Override
    public void run() {
        while (flag){
            flag = show();
        }
    }

    private synchronized boolean show(){
        if(ticket > 0){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"：票号为"+ticket);
            ticket--;
            return true;
        }else{
            System.out.println(Thread.currentThread().getName()+"：票已售完");
            return false;
        }
    }
}

class Windown3 implements Runnable{

    private int tickets = 100;

    private ReentrantLock lock = new ReentrantLock();

    public void Windown3(){

    }

    @Override
    public void run() {
        while(true){
            try{
                lock.lock();
                if(tickets > 0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"：票号为"+tickets);
                    tickets--;
                }else{
                    System.out.println(Thread.currentThread().getName()+"：票已售完");
                    break;
                }
            }finally {
                lock.unlock();
            }
        }

    }
}

public class WindowAllTest {
    public static void main(String[] args) {
        //1.synchronized代码块实现Thread
//        Windown1 w1 = new Windown1();
//        Windown1 w2 = new Windown1();
//        Windown1 w3 = new Windown1();
        //2.synchronized方法实现Runnable
//        Windown2 w = new Windown2();
//        Thread w1 = new Thread(w);
//        Thread w2 = new Thread(w);
//        Thread w3 = new Thread(w);
        //3.lock实现Runnable
        Windown3 w = new Windown3();
        Thread w1 = new Thread(w);
        Thread w2 = new Thread(w);
        Thread w3 = new Thread(w);

        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        /**join方法可以传递参数，join(1000)表示main线程会等待w2线程1000毫秒，1000毫秒过去后，
         * main线程和w2线程之间执行顺序由串行执行变为普通的并行执行
         */
        try {
            w2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        w3.start();
    }
}
