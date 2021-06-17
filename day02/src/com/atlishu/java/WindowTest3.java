package com.atlishu.java;

/**
 *
 * 同步方法处理实现Runnable的线程安全问题
 *
 * @author lishustart
 * @create 2020-12-02-11:14
 */
class Window3 implements Runnable{
    private int ticket = 100;
//    Object obj = new Object();

    @Override
    public void run() {
        while(true){
            show();
        }
    }

    private synchronized void show(){//同步监视器this
        if (ticket > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":票号为" + ticket);
            ticket--;
        }
    }
}

public class WindowTest3 {
    public static void main(String[] args) {
        Window3 w = new Window3();
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);
        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");
        t1.start();
        t2.start();
        t3.start();

    }
}
