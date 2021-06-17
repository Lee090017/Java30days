package com.atlishu.linkedlist;

import java.util.Stack;

/**
 * @author lishustart
 * @create 2021-05-17-16:59
 *
 * 栈的使用
 */
public class StackTest {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        //入栈
        stack.add("aaa");
        stack.add("bbb");
        stack.add("ccc");
        //出栈
        while(stack.size()>0){
            System.out.println(stack.pop());//ccc、bbb、aaa
        }
    }
}
