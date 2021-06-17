package com.atlishu.java;

/**
 * 线程通信的例子：使用两个线程打印1-100，线程1，线程2交替打印
 *
 * 涉及到的方法：
 * wait()：一旦执行，此线程进入阻塞状态，并且释放同步监视器
 * notify()：一旦执行，就会唤醒被wait的一个线程，如果多个线程，选择优先级最高的那个
 * notifyAll()：一旦执行，就会唤醒被wait的所有线程
 *
 * 说明：
 * 1.wait()/notify()/notifyAll()必须使用在同步方法块或同步方法中
 * 2.这三个方法的调用者必须是同步监视器
 * 3.这三个方法是定义在Object中，所以任何对象都可以调用
 *
 * 面试题：sleep()和wait()的异同
 * 1.相同点：一旦执行，线程都会进入阻塞状态
 * 2.不同点：①两个方法的声明位置不同，Thread中声明sleep，Object声明wait
 *          ②调用的要求不同：sleep可以在任何需要的地方调用，wait必须在同步代码块或者同步方法中使用
 *          ③sleep方法不会释放同步监视器，wait会释放同步监视器
 *
 * @author lishustart
 * @create 2020-12-02-18:31
 */
class Number implements Runnable{
    private int num = 1;
    @Override
    public void run() {
        while (true){
            synchronized (this) {

                notify();//唤醒wait的线程

                if(num < 100){
                    System.out.println(Thread.currentThread().getName()+":"+num);
                    num++;

                    try {
                        wait();//使得调用wait方法的线程进入阻塞状态，会释放锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    break;
                }
            }
        }
    }
}

public class ThreadCommunicationsTest {
    public static void main(String[] args) {
        Number number = new Number();
        Thread t1 = new Thread(number);
        Thread t2 = new Thread(number);

        t1.setName("线程1");
        t2.setName("线程2");
        t1.start();
        t2.start();
    }
}
