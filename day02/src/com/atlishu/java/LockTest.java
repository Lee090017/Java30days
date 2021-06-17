package com.atlishu.java;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决线程安全问题的方式三：lock锁
 *
 * 1.面试题：synchronized与lock有什么不同
 * 相同点：二者都可以解决线程的安全问题
 * 不同点：synchronized机制在执行完相应的同步代码以后，自动地释放同步监视器
 *        Lock需要手动启动（Lock()）和手动结束（unlock()）
 *
 * 2.面试题：如何解决线程的安全问题？3种
 * @author lishustart
 * @create 2020-12-02-16:05
 */
class Window implements Runnable{
    private int ticket = 100;
    //1.实例化Lock
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while(true){
            try {

                //2.调用lock()
                lock.lock();
                if(ticket <= 0){
                    System.out.println(Thread.currentThread().getName()+":票已售完");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }else{
                    System.out.println(Thread.currentThread().getName()+":票号为"+ticket);
                    ticket--;
                }
            } finally {
                //3.调用解锁的unlock()
                lock.unlock();
            }
        }
    }
}

public class LockTest {
    public static void main(String[] args) {
        Window w = new Window();
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
