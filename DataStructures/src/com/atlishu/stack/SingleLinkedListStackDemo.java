package com.atlishu.stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author lishustart
 * @create 2021-05-18-20:30
 *
 * 用链表来模拟栈
 *
 */
public class SingleLinkedListStackDemo {
    public static void main(String[] args) {
        SingleLinkedListStack stack = new SingleLinkedListStack(4);
//        NumNode newNode = new NumNode(10);
//        stack.push(newNode);
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
                    stack.show();
                    break;
                case "3":
                    System.out.println("请输入一个数");
                    int value = sc.nextInt();
                    NumNode newNode = new NumNode(value);
                    stack.push(newNode);
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

class SingleLinkedListStack{
    private int maxSize;//栈的大小
    private NumNode head = new NumNode(0);
    private int top = -1;

    public SingleLinkedListStack(int maxSize) {
        this.maxSize = maxSize;
    }

    //栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }
    //栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈
    public void push(NumNode node){
        if(isFull()){
            System.out.println("栈满，无法添加数据");
            return;
        }
        NumNode temp = head;
        for (int i = 0; i <=top; i++) {
           temp = temp.next;
        }
        temp.next = node;
        top++;
    }
    //出栈
    public int pop(){
        if(isEmpty()){
//            System.out.println("栈空，无法导出数据");
            throw new RuntimeException("无法导出数据");
        }
        NumNode temp = head.next;
        for (int i = 0; i < top; i++) {
            temp = temp.next;
        }
        int value = temp.getValue();
        top--;
        return value;
    }

    //显示，逆序显示链表
    public void show(){
        if(isEmpty()){
            System.out.println("栈空，无法显示");
            return;
        }
        Stack<NumNode> stack = new Stack<>();
        NumNode temp = head.next;
        for (int i = 0; i <= top; i++) {
            stack.push(temp);
            temp = temp.next;
        }
        while (stack.size()!=0){
            System.out.println("linkedlist:"+stack.pop().getValue());
        }
    }
}

class NumNode{
    private int value;
    public NumNode next;

    public NumNode() {
    }

    public NumNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}