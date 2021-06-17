package com.atlishu.been;

/**
 * @author lishustart
 * @create 2021-05-11-19:31
 */
public class MyInterfaceImplements implements MyInterface{
    @Override
    public void methodAbstract() {

    }

    @Override
    public void methodDefault() {
        System.out.println("我是重写的默认方法");
    }

    public static void main(String[] args) {
        //接口中的静态方法只能由自己调用
        MyInterface.methodStatic();
        //接口实现类不能调用静态方法
//        MyInterfaceImplements.methodStatic();

        MyInterfaceImplements mii = new MyInterfaceImplements();
        mii.methodDefault();
        //私有方法不能在外部调用
//        mii.methodPrivate();
    }
}
