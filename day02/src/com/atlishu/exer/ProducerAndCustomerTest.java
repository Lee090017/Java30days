package com.atlishu.exer;

/**
 * 经典案例--->生产者和消费者问题
 *
 * 两个问题：
 * 生产者比消费者快时，消费者会漏掉一些数据没有取到
 * 消费者比生产者快时，消费者会取相同的数据
 *
 * @author lishustart
 * @create 2020-12-02-19:10
 */
//店员
class Clerk{
    private int numOfProduct = 0;

    public Clerk(int numOfProduct) {
        this.numOfProduct = numOfProduct;
    }

    //进货
    public synchronized void addProduct(){
        notify();
        if(numOfProduct < 20){
            numOfProduct++;
            System.out.println(Thread.currentThread().getName()+"开始生产第"+numOfProduct+"个产品");
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //卖货
    public synchronized void saleProduct(){
        notify();
        if(numOfProduct > 0){
            System.out.println(Thread.currentThread().getName()+"开始消费第"+numOfProduct+"个产品");
            numOfProduct--;
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//生产者
class Productors implements Runnable{
    private Clerk clerk;

    public Productors(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始生产");
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.addProduct();
        }
    }
}

//消费者
class Customers implements Runnable{
    private Clerk clerk;

    public Customers(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始消费");
        while(true){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.saleProduct();
        }
    }
}

public class ProducerAndCustomerTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk(0);
        Productors p = new Productors(clerk);
        Customers c = new Customers(clerk);
        Thread tp = new Thread(p);
        Thread tc = new Thread(c);

        tp.setName("生产者");
        tc.setName("消费者");

        tp.start();
        tc.start();


    }
}
