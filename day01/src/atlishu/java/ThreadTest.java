package atlishu.java;

/**
 * 多线程的创建，方式一：集成Thread类
 * 第一步：创建一个继承Tread类的子类
 * 第二步：重写Tread类中的run()方法--->将此线程执行的操作声明在run()方法中
 * 第三步：创建Tread类的子类的对象
 * 第四步：通过此对象调用start()方法
 *
 * @author lishustart
 * @create 2020-12-01-15:54
 */
//1.创建一个子类
class MyThread extends Thread{

    //2.重写run()方法
    @Override
    public void run() {//创建非主线程执行代码
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}

public class ThreadTest {
    public static void main(String[] args) {
        //3.创建Tread类的子类的对象
        MyThread mt1 = new MyThread();//main方法的主线程执行的
        MyThread mt2 = new MyThread();

        //4.通过此对象调用start()方法：1.启动当前线程，2.调用run()方法
//        mt.run();//不能通过run的方式直接调用线程
        mt1.start();

        //start不能还让已经使用的线程去执行，此时需要新建一个对象
        mt2.start();

        //如下操作仍然是在main线程中执行的
        for (int i = 0; i <100; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
        }


    }
}
