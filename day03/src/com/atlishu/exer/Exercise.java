package com.atlishu.exer;

/**
 * @author lishustart
 * @create 2020-12-23-20:44
 */
public class Exercise {
    String str = new String("good");
    char[] ch = {'t','e','s','t'};

    public void change(String str,char ch[]){
        str = "test ok";//由于该地址值指向的常量池中"good"具有不可变性，所以重新开辟新的内存空间，用于创建"test ok"
        ch[0] = 'b';
    }

    public static void main(String[] args) {
        Exercise ex = new Exercise();
        ex.change(ex.str,ex.ch);
        System.out.println(ex.str);//good
        System.out.println(ex.ch);//best
    }
}
