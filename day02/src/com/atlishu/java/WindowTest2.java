package com.atlishu.java;

/**
 * 创建三个窗口卖票，总票数为100张（用Runnable的方式实现）
 *
 * Java中通过线程的同步机制来解决线程安全问题
 *
 * 方式一：同步代码块处理实现Runnable的线程安全问题
 *      synchronized(同步监视器){
 *          //需要被同步的代码
 *      }
 *      说明：①操作共享数据的代码，即需要同步的代码，不能包含代码多了，也不能多了
 *           ②共享数据：多个线程共同操作的数据，比如本问题中的ticket
 *           ③同步监视器（锁）：任何一个类的对象，都可以当做锁
 *            要求：多个线程必须共用同一把锁
 *           ④在实现Runnable的线程安全问题时，可以用this当同步监视器
 *
 * 方式二：同步方法处理实现Runnable的线程安全问题
 *      如果操作共享数据的代码完整地声明在一个方法中，我们不妨将此方法声明为同步的
 *      总结：①同步方法仍然涉及到同步监视器，只是不需要我们显式的声明
 *           ②非静态的同步方法，同步监视器是：this
 *            静态的同步方法，同步监视器是当前类（对象）本身
 *
 * 局限性：操作代码的同时，只能有一个线程参与，其他线程等待，相当于是一个单线程过程，效率低
 *
 * @author lishustart
 * @create 2020-12-01-20:38
 */

class Window2 implements Runnable{
    private int ticket = 100;
//    Object obj = new Object();

    @Override
    public void run() {
        while(true){
            synchronized(this) {
                if (ticket <= 0) {
                    System.out.println(Thread.currentThread().getName() + ":票已售完");
                    break;
                } else {
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    System.out.println(Thread.currentThread().getName() + ":票号为" + ticket);
                    ticket--;
                }
            }
        }
    }
}

public class WindowTest2 {
    public static void main(String[] args) {
        Window2 w = new Window2();
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
