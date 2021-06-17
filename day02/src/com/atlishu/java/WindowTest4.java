package com.atlishu.java;

/**
 *
 * 同步方法处理实现继承Thread类的线程安全问题
 *
 * @author lishustart
 * @create 2020-12-02-11:24
 */
class Window4 extends Thread{

    private static int ticket = 100;
    private static Object obj = new Object();

    @Override
    public void run() {
        for ( ; ; ) {
//           synchronized (obj) {//此时不能用this，否则锁不唯一
//            synchronized (Window4.class){//Class class = Window4.class
                show();
            }
        }

    public Window4(String name){
        super(name);
    }

    private static synchronized void show(){//同步监视器：Window.class
        if (ticket > 0){
            System.out.println(Thread.currentThread().getName() + ":票号为" + ticket);
            ticket--;
        }
    }
}


public class WindowTest4 {
    public static void main(String[] args) {
        Window4 w1 = new Window4("窗口1");
        Window4 w2 = new Window4("窗口2");
        Window4 w3 = new Window4("窗口3");

//        w1.setPriority(10);
//        w2.setPriority(5);
//        w3.setPriority(1);

        w1.start();
        w2.start();
        w3.start();
    }
}
