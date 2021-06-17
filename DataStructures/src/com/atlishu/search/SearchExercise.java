package com.atlishu.search;

import java.util.Arrays;

/**
 * @author lishustart
 * @create 2021-06-07-8:46
 *
 * 总结各种排序算法
 *
 */
public class SearchExercise {

    public static int countForbinarySearch = 0;
    public static int countForinsertValueSearch = 0;
    public static int countForfibonacciSearch = 0;

    public static void main(String[] args) {
        int[] arr = new int[]{-3,0,1,3,5,6,9,12,18,30,80,150,500,1130,1500,2000};
        System.out.println("二分查找："+binarySearch(arr,2000,0,arr.length-1)+"，查找次数："+countForbinarySearch);
        System.out.println("插值算法："+insertValueSearch(arr,2000,0,arr.length-1)+"，查找次数："+countForinsertValueSearch);
        System.out.println("斐波那契："+fibonacciSearch(arr,2000)+"，查找次数："+countForfibonacciSearch);

    }

    //线性查找
    //略

    //二分查找
    public static int binarySearch(int[] arr,int value,int left,int right){
        if(left>right){
            return -1;
        }
        int mid = (left+right)/2;
        countForbinarySearch++;
        if (arr[mid] > value){
            return binarySearch(arr,value,left,mid-1);
        }else if (arr[mid] < value){
            return binarySearch(arr,value,mid+1,right);
        }else{
            return mid;
        }
    }

    //插值算法
    public static int insertValueSearch(int[] arr,int value,int left,int right){
        if(left>right){
            return -1;
        }
        //
        int mid = left + (right - left) *(value - arr[left])/(arr[right] - arr[left]);
        countForinsertValueSearch++;
        if (arr[mid] > value || mid < 0 || mid > arr.length-1){
            return binarySearch(arr,value,left,mid-1);
        }else if (arr[mid] < value){
            return binarySearch(arr,value,mid+1,right);
        }else{
            return mid;
        }
    }

    //斐波那契（黄金分割）查找
    public static int fibonacciSearch(int[] arr,int value){
        //创建一个Fibonacci数组
        int[] f = new int[20];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < f.length; i++) {
            f[i] = f[i-1] + f[i-2];
        }
//        System.out.println(Arrays.toString(f));
        //将原数组扩充为f[k]-1数量的数组
        int k = 0;
        while (arr.length > f[k]-1){
            k++;
        }
        int[] newArr = Arrays.copyOf(arr, f[k] - 1);
        for (int i = arr.length; i < newArr.length; i++) {
            newArr[i] = arr[arr.length-1];
        }
//        System.out.println(Arrays.toString(newArr));

        int left = 0;
        int right = arr.length-1;
        int mid = 0;

        while(left < right){
            countForfibonacciSearch++;
            mid = left + f[k-1] - 1;
            if (newArr[mid] > value){
                k--;
            }else if (newArr[mid] < value){
                k-=2;
                left = mid + 1;
            }else{
                if(mid < arr.length){
                    return mid;
                }else{
                    return arr.length-1;
                }
            }
        }
        return -1;
    }
}
