package com.atlishu.been;

/**
 * @author lishustart
 * @create 2021-05-11-19:25
 *
 * 可以在接口中定义私有方法
 */
public interface MyInterface {
    //如下三个方法的权限修饰符都是public
    void methodAbstract();

    static void methodStatic(){
        System.out.println("我是接口中的静态方法");
    }

    default void methodDefault(){
        System.out.println("我是接口中的默认方法");
    }

    //jdk 9中允许接口中定义的私有方法
    private void methodPrivate(){
        System.out.println("我是接口中的私有方法");
    }
}
