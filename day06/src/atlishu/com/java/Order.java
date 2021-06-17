package atlishu.com.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lishustart
 * @create 2021-04-02-11:08
 */

//自定义泛型类
public class Order<T> {
    String orderName;
    int orderId;

    //类的内部结构就可以使用类的类型

    T orderT;//某个类叫T

    public Order(){

    }

    public Order(String orderName, int orderId, T orderT) {
        this.orderName = orderName;
        this.orderId = orderId;
        this.orderT = orderT;
    }

    public T getOrderT(){
        return orderT;
    }

    public void setOrderT(T orderT) {
        this.orderT = orderT;
    }

}

class SubOrder1 extends Order<Integer>{
    //不再是泛型类

    public SubOrder1() {//构造器不用加泛型
    }

    public SubOrder1(String orderName, int orderId, Integer orderT) {
        super(orderName, orderId, orderT);
    }

    //静态方法不能用类的泛型
//    public  static void show(T orderT){
//        System.out.println(orderT);
//    }

    //泛型方法：在方法中出现泛型结构，其所在的类是不是泛型类都没关系
    public <E> List<E> copyFromArrayToList(E[] arr){
        ArrayList<E> list = new ArrayList<>();

        for(E e : arr){
            list.add(e);
        }

        return list;
    }


}

class SubOrder2<T> extends Order<T>{
//仍为泛型类
}