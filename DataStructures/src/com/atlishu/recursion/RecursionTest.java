package com.atlishu.recursion;

/**
 * @author lishustart
 * @create 2021-05-25-17:30
 *
 * 递归机制回顾
 */
public class RecursionTest {
    public static void main(String[] args) {
        test(10);
        System.out.println(factorial(10));
    }

    //打印问题
    public static void test(int n){
        if(n > 2) {
            test(n-1);
        }//else
        System.out.println("n=" + n);
    }

    //阶乘问题
    public static int factorial(int n){
        if(n == 1){
            return 1;
        }else{
            return factorial(n-1) * n;
        }
    }
}


