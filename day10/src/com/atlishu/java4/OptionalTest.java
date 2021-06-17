package com.atlishu.java4;

import org.junit.Test;

import java.util.Optional;

/**
 * @author lishustart
 * @create 2021-05-11-15:28
 *
 * Optional类：为了在程序中避免痴线空指针异常
 */
public class OptionalTest {

    /*
    Optional.of(T t)创建一个Optional实例，t必须为非空
    Optional.empty()创建一个空的Optional实例
    Optional.ofNullable(T t)t可以为null
     */
    @Test
    public void test1(){
        //创建一个非空的Optional
        Optional<Girl> girl = Optional.of(new Girl());

        //ofNullable(T t)：t可以为null
        Girl girl1 = new Girl();
        girl1=null;
        Optional<Girl> girl2 = Optional.ofNullable(girl1);
        System.out.println(girl2);

    }

    @Test
    public void test2(){
        //可能出现空指针异常
        Girl girl = new Girl();
        String boyName = getBoyName3(girl);
        System.out.println(boyName);
    }

    //可能出现空指针异常
    public String getBoyName1(Girl girl){
        return girl.getBoy().getName();
    }

    //优化以后的
    public String getBoyName2(Girl girl){
        if(girl!=null){
            if(girl.getBoy()!=null){
                return girl.getBoy().getName();
            }
        }
        return null;
    }

    //使用Optional类的getBoyName()
    public String getBoyName3(Girl girl){
        Optional<Girl> girl1 = Optional.ofNullable(girl);
        //假如girl1不为空则返回自身，为空则返回指定值
        Girl girl2 = girl1.orElse(new Girl(new Boy("lishu")));
        //现在boy依然可能为空
        Boy boy = girl2.getBoy();
        Optional<Boy> boy1 = Optional.ofNullable(boy);
        Boy lee = boy1.orElse(new Boy("lee"));

        return lee.getName();
    }
}
