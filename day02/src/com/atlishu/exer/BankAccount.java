package com.atlishu.exer;

/**
 * 银行有有个账户，两个人分别向同一个账户存钱，共3000元，每次存1000元，存3次，每次存完打印账户余额
 *
 * @author lishustart
 * @create 2020-12-02-16:32
 */
class Account {
    private double balance = 0;

//    private ReentrantLock lock = new ReentrantLock();

    public Account(double balance){
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount){

            if (amount >= 0) {
                balance += amount;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "存款1000元，余额为：" + balance);
            }

    }
}

class Customer extends Thread{
    private Account acct;

    public Customer(Account acct){
        this.acct = acct;
    }

    @Override
    public void run() {

            for(int i = 0;i < 3;i++){
                synchronized (Customer.class) {
                    acct.deposit(1000);
                }
            }
//            System.out.println(getName() + "存款1000元，余额为：" + acct.getBalance());
    }
}

public class BankAccount {
    public static void main(String[] args) {
        Account acct = new Account(0);
        Customer c1 =new Customer(acct);
        Customer c2 =new Customer(acct);
        c1.setName("甲");
        c2.setName("乙");

        c1.start();
        c2.start();
    }
}
