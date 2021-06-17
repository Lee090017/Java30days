package com.atlishu.singleton;

/**
 * 使用同步机制，将单例模式中的懒汉式
 *
 * @author lishustart
 * @create 2020-12-02-15:06
 */
class Bank{

    private Bank() {

    }

    private static Bank bank = null;

    //使用同步方法
    public static synchronized Bank getBank1(){//锁是当前类本身
        if(bank != null){
            //可能出现阻塞
            bank = new Bank();
        }
        return bank;
    }

    //使用同步代码块
    public static Bank getBank2(){//锁是当前类本身
        //方式一：效率稍差
//        synchronized (Bank.class) {
//            if(bank != null){
//                //可能出现阻塞
//                bank = new Bank();
//            }
//            return bank;
//        }
        //方式二：效率更高
        if(bank != null) {
            synchronized (Bank.class) {
                if (bank != null) {
                    //可能出现阻塞
                    bank = new Bank();
                }
            }
        }
        return bank;
    }
}

public class BankTest {
    public static void main(String[] args) {

    }
}
