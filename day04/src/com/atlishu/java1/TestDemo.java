package com.atlishu.java1;

/**
 * @author lishustart
 * @create 2021-03-09-21:21
 *
 * 泛型-定义一个泛型类
 */
public class TestDemo {
    public static void main(String[] args) {
        //定义一个泛型类Test的一个Integer版本
        Test<Integer> intOb = new Test<Integer>(88);
        intOb.showType();
        System.out.println(intOb.getOb());
        System.out.println("----------------------");
        //定义一个泛型类Test的额一个String版本
        Test<String> stringOb = new Test<String>("lishu");
        stringOb.showType();
        System.out.println(stringOb.getOb());
    }

}

/*
* 使用T代表类型，无论何时都没有比这更具体的类型来区分它。
* 如果有多个类型参数，我们可能使用字母表中T的临近的字母，比如S。
*/
class Test<T>{

    private T ob;

    public Test(T ob) {
        this.ob = ob;
    }

    public T getOb() {
        return ob;
    }

    public void setOb(T ob) {
        this.ob = ob;
    }

    public void showType(){
        System.out.println("T的实际类型是："+ob.getClass().getName());
    }
}
