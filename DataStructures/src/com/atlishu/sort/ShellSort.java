package com.atlishu.sort;


import java.util.Arrays;
import java.util.Date;

/**
 * @author lishustart
 * @create 2021-05-26-21:02
 *
 * 希尔排序
 *
 * 增强版的插入排序，利用动态增量分组，然后对组内的元素进行插入排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2,-1,3,0,-2,1,0,3};
        shellSort2(arr);
        System.out.println(Arrays.toString(arr));

        //创建一个80000个随机数的数组
        int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr1[i] = (int)(Math.random()*80000);
        }

        Date date1 = new Date();
        shellSort2(arr1);

        Date date2 = new Date();
        long t = date2.getTime()-date1.getTime();
//        System.out.println(Arrays.toString(arr1));
        System.out.println("希尔排序耗时"+t+"ms");

    }

    //shell排序实现1：交换法
    public static void shellSort(int[] arr){
        int temp = 0;
        //缩小增量的过程
        for (int gap = arr.length/2; gap > 0; gap /= 2) {//gap为步长
            //对每组分别进行插入排序(交换法)
            for (int i = gap; i < arr.length; i++) {
                for (int j = i-gap; j >= 0; j-=gap) {//该元素交换到改组的合适位置
                    //如果在某一组里当前元素大于相邻(相隔gap)元素，则交换
                    if(arr[j] > arr[j+gap]){
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }

        }
    }

    //shell排序实现2：位移法
    public static void shellSort2(int[] arr){
        int invertVal;
        int invertIndex;

        for(int gap = arr.length/2;gap > 0;gap/=2){
            for (int i = gap; i < arr.length; i++) {
                invertVal = arr[i];
                invertIndex = i - gap;
                while (invertIndex >= 0 && invertVal < arr[invertIndex]){
                    arr[invertIndex+gap] = arr[invertIndex];
                    invertIndex -= gap;
                }
                if(invertIndex != i - gap) {
                    arr[invertIndex+gap] = invertVal;
                }
            }

        }
    }
}
