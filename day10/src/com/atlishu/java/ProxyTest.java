package com.atlishu.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lishustart
 * @create 2021-05-06-20:05
 *
 * 动态代理的举例
 */

interface Human{
    String getBelief();

    void eat(String food);
}

class HumanUtil{
    public void method1(){
        System.out.println("==========通用方法一==========");
    }

    public void method2(){
        System.out.println("==========通用方法二==========");
    }
}

//被代理类
class SuperMan implements Human{
    public SuperMan() {
        super();
    }

    @Override
    public String getBelief() {
        return "I believe I can fly!";
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃"+food);
    }
}

/*
    要想实现动态代理，需要解决的问题
    问题1：如何根据被代理类，动态创建一个代理类及其对象
    问题2：当通过代理类的对象调用方法时，如何动态调用被代理类的同名方法
 */
class ProxyFactory{

    //调用此方法，返回一个代理类的对象，解决问题一
    public static Object getProxyInstance(Object obj) throws Exception {//obj:被代理类对象
        MyInvocationHandler handler = new MyInvocationHandler();

        handler.bind(obj);

        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),handler);
        //        Class<?> objClass = obj.getClass();
//        return objClass.newInstance();
    }
}

class MyInvocationHandler implements InvocationHandler{

    private Object obj;//赋值时要使用被代理类的对象进行实例化

    public void bind(Object obj){
        this.obj = obj;
    }
    //当我们通过代理类的对象，调用方法a时，就会自动调用如下的方法：invoke()
    //将被代理类要执行的方法a的功能就声明在invoke()中
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        HumanUtil util = new HumanUtil();
        util.method1();//面向切面编程的体现

        //method：即为代理类对象调用的方法，此方法也就作为被代理类对象要调用的方法
        //obj：被代理类的对象
        Object returnValue = method.invoke(obj,args);

        util.method2();
        //上述方法的返回值就作为当前类invoke()方法的返回值
        return returnValue;
    }
}


public class ProxyTest {
    public static void main(String[] args) throws Exception {
//        Class<SuperMan> superManClass = SuperMan.class;
//        SuperMan superMan = superManClass.newInstance();
//
//        Method getBelief = superManClass.getDeclaredMethod("getBelief");
//
//        Object invoke = getBelief.invoke(superMan);
//
//        System.out.println(invoke);
        //proxyInstance:代理类的对象
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(new SuperMan());
        //当通过代理类对象调用方法时，会自动的调用被代理类中同名的方法
        proxyInstance.getBelief();
        proxyInstance.eat("串串");

        System.out.println("********************");
        ClothFactory proxyInstance1 = (ClothFactory) ProxyFactory.getProxyInstance(new NikeClothFactory());
        proxyInstance1.produceCloth();

    }
}
