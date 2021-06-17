package atlishu.java;

/**
 * 创建多线程的方式二：实现Runnable接口
 * 第一步：创建一个实现Runnable接口的类
 * 第二步：实现类去实现Runnable的抽象方法
 * 第三步：创建实现类的对象
 * 第四步：将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
 * 第五步：通过Thread类的对象调用start()
 *
 * 比较两种实现多线程的方式
 * 开发中，优先选择实现Runnable接口的方式
 * 原因：1.实现的方式没有类的单继承的局限性
 *      2.更适合多个线程有共享数据的情况
 * 联系：public class Thread implements Runnable
 * 相同点：两种方法都要重写run()，将线程要执行的逻辑声明在run()中
 *
 *
 * @author lishustart
 * @create 2020-12-01-20:15
 */
//1.创建一个实现Runnable接口的类
class MThread implements Runnable{
    @Override
    //2.实现类去实现Runnable的抽象方法
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}

public class RunnableTest {
    public static void main(String[] args) {
        //3.创建实现类的对象
        MThread m1 = new MThread();
        //4.将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
        Thread t1 = new Thread(m1);
        t1.setName("线程1");
        //5.通过Thread类的对象调用start()
        t1.start();
    }

}
