package com.atlishu.java;

/**
 * 创建三个窗口卖票，总票数为100张（用Thread的方式实现）
 *
 * 1.存在线程的安全问题（重票和错票）待解决
 * 2.问题的原因：当某个线程操作车票的过程中，尚未操作完成，其他线程参与进来了，也在操作车票
 * 3.如何解决：当一个线程在操作ticket的时候，其他线程不能参与进来，直到A操作完ticket，其他线程才能操作
 *            这种情况，线程A出现阻塞也不能改变
 *
 * 方式一：同步代码块处理实现继承Thread类的线程安全问题
 *        在实现Thread的线程安全问题时，不能用this当同步监视器，可以考虑用这个类充当同步监视器
 *        （Window.class）
 *
 *
 * 方式二：同步方法处理实现继承Thread类的线程安全问题
 *
 * @author lishustart
 * @create 2020-12-01-19:44
 */
class Window1 extends Thread{

    private static int ticket = 100;
    private static Object obj = new Object();

    @Override
    public void run() {
        for ( ; ; ) {
//           synchronized (obj) {//此时不能用this，否则锁不唯一
            synchronized (Window1.class){//Class class = Window1.class
               if (ticket > 0){
                   System.out.println(getName() + ":票号为" + ticket);
                   ticket--;
               }else{
                   System.out.println(getName()+":票已售完");
                   break;
               }
           }
        }
    }
    
    public Window1(String name){
        super(name);
    }
}


public class WindowTest1 {
    public static void main(String[] args) {
        Window1 w1 = new Window1("窗口1");
        Window1 w2 = new Window1("窗口2");
        Window1 w3 = new Window1("窗口3");

//        w1.setPriority(10);
//        w2.setPriority(5);
//        w3.setPriority(1);

        w1.start();
        w2.start();
        w3.start();
    }
}
