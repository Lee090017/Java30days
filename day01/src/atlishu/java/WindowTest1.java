package atlishu.java;

/**
 * 创建三个窗口卖票，总票数为100张（用Thread的方式实现）
 *
 * 存在线程的安全问题（重票）待解决
 *
 * @author lishustart
 * @create 2020-12-01-19:44
 */
class Window1 extends Thread{

    private static int ticket = 100;

    @Override
    public void run() {
        for ( ; ; ) {
           if(ticket < 0){
               System.out.println(getName()+":票已售完");
               break;
           }else {
               System.out.println(getName()+":票号为"+ticket);
               ticket--;
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
