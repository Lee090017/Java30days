package atlishu.java;

/**
 * 创建三个窗口卖票，总票数为100张（用Runnable的方式实现）
 *
 * @author lishustart
 * @create 2020-12-01-20:38
 */

class Window2 implements Runnable{
    private int ticket = 100;

    @Override
    public void run() {
        while(true){
            if(ticket < 0){
                System.out.println(Thread.currentThread().getName()+":票已售完");
                break;
            }else{
                System.out.println(Thread.currentThread().getName()+":票号为"+ticket);
                ticket--;
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
