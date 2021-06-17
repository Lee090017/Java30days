package com.atlishu.search;

import java.util.Arrays;

/**
 * @author lishustart
 * @create 2021-06-05-19:01
 *
 * 斐波那契查找
 */
public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};

        System.out.println(fibonacciSearch(arr,1));

    }

    //因为后面我们mid=low+F(k-1)-1，需要使用到斐波那契数列，因此我们需要先获取到一个斐波那契数列
    //非递归方式得到斐波那契数列
    public static int[] fib(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i-1] + f[i-2];
        }
        System.out.println(Arrays.toString(f));//打印斐波那契数组
        return f;
    }

    /**
     *使用非递归的方法
     * @param a 数组
     * @param key 我们需要查找的关键码
     * @return 返回对应的下标
     */
    public static int fibonacciSearch(int[] a,int key){
        int low = 0;
        int high = a.length-1;
        int k = 0;//表示斐波那契分割数值的下标
        int mid = 0;
        int[] f = fib();
        //获取到斐波那契分割数值的下标
        while(high > f[k]-1){
            k++;
        }
        //f[k]的值可能大于a的长度，因此我们需要使用Arrays类，构造一个新的数组，指向a[]
        int[] temp = Arrays.copyOf(a,f[k]-1);//不足的部分会用0填充
        //将后面所有的0用arr[length-1]替换
        for (int i = high+1; i < temp.length; i++) {
            temp[i] = a[high];
        }
        System.out.println(Arrays.toString(temp));//打印构造的数组

        //使用while循环查找
        while(low<=high){
            mid = low+f[k-1]-1;
            if(key < temp[mid]){
                high = mid - 1;
                /*
                向左查找时，当前数组长度为f[k-1]-1（前一段）
                f[k-1] = f[k-2] + f[k-3]
                f[k-2]为前一段，f[k-3]为后一段
                下次再查找的长度应该为f[k-2]，因此k--
                 */
                k--;
            }else if (key > temp[mid]){
                low = mid + 1;
                /*
                向右查找时，当前数组长度为f[k-2]-1（后一段）
                f[k-2] = f[k-3] + f[k-4]
                f[k-3]为前一段，f[k-4]为后一段
                下次再查找的长度应该为f[k-3]，原本给的是f[k-1]，因此是k-=2
                 */
                k-=2;
            }else{
                if(mid<=high){
                    return mid;
                }else{
                    return high;
                }
            }
        }
        return -1;

    }

}
