package com.atlishu.stack;

import java.util.Scanner;

/**
 * @author lishustart
 * @create 2021-05-18-19:54
 *
 * 数组实现栈
 */
public class
ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStatck stack = new ArrayStatck(4);
        String key = "";
        boolean loop = true;
        Scanner sc = new Scanner(System.in);

        while(loop){
            System.out.println("(1)show:显示栈");
            System.out.println("(2)exit:退出");
            System.out.println("(3)push:入栈");
            System.out.println("(4)pop:出栈");
            System.out.println("请输入你的选择(1~4)");
            key = sc.next();
            switch (key){
                case "1":
                    stack.list();
                    break;
                case "3":
                    System.out.println("请输入一个数");
                    int value = sc.nextInt();
                    stack.push(value);
                    break;
                case "4":
                    try {
                        int pop = stack.pop();
                        System.out.println("出栈数据："+pop);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2":
                    sc.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出了");
    }
}

//定义一个ArrayStack
class ArrayStatck{
    private int maxSize;//栈的大小
    private int[] stack;
    private int top = -1;

    public ArrayStatck(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //栈满
    public boolean isNull(){
        return top == maxSize - 1;
    }
    //栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈-push
    public void push(int value){
        if(isNull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }
    //出栈-pop
    public int pop(){
        if(isEmpty()){
            //抛出异常
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //遍历时需要从栈顶开始显示数据
    public void list(){
        if(isEmpty()){
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}
