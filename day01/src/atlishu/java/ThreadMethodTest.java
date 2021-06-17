package atlishu.java;

/**
 * 测试Thread类中的常用方法
 * 1.start()--->启动当前线程和当前线程的run()方法
 * 2.run()--->通常需要重写Thread类中的此方法，将创建的线程要执行的操作写入
 * 3.currentThread()--->返回当前代码执行的线程(对象)
 * 4.getName()--->获取当前线程的名字
 * 5.setName()--->设置当前线程的名字
 * 6.yield()--->释放当前CPU的执行权
 * 7.join()--->在线程A中调用线程B的join()方法，此时线程A进入阻塞状态，当B完以后，A才结束阻塞状态
 * 8.stop()--->当执行此方法时，强制结束当前进程
 * 9.sleep(long millitime)--->让线程阻塞millitime毫秒
 * 10.isAlive()--->判断当前方法是否存活
 *
 * 线程优先级：
 * MAX_PRIORITY = 10
 * MIN_PRIORITY = 1
 * NORM_PRIVORITY = 5 (默认优先级)
 * 高优先级的线程只是更高概率被执行，并不意味着只有当高优先级的执行以后，低优先级才会执行
 *
 * @author lishustart
 * @create 2020-12-01-16:42
 */
class Hello extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+":"+Thread.currentThread().getPriority()+","+i);

//            if(i % 10 == 0){
//                yield();//释放这个线程
//            }
//            if(i == 50){
//                try {
//                    sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }

    public Hello(String name){
        super(name);
    }
}

public class ThreadMethodTest {
    public static void main(String[] args) {
        Hello h1 = new Hello("线程1");

//        h1.setName("线程1");
        h1.start();
        h1.setPriority(Thread.MAX_PRIORITY);
        System.out.println(h1.isAlive());//true
        Thread.currentThread().setName("主线程");
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+":"+Thread.currentThread().getPriority()+","+i);

//            if(i == 20) {
//                try {
//                    h1.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }
        System.out.println(h1.isAlive());//false
    }
}

