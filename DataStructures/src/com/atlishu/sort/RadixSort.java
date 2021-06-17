package com.atlishu.sort;

import java.util.Arrays;
import java.util.Date;

/**
 * @author lishustart
 * @create 2021-05-27-16:42
 *
 * 基数排序（桶排序）
 *
 * 将序列按照个、十、百、千...依次放入对应的0~9的桶（一个二维数组）中，再依次取出
 * 构成新的序列，其放入的轮数与数组最高位有关
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = new int[]{53,3,542,748,14,214};
        int[] temp = new int[arr.length];
        radixSort(arr);
        System.out.println(Arrays.toString(arr));

        //桶所占用的内存
        //80000*11*4/1024/1024 = 3.3M
        //创建一个80000个随机数的数组
        int[] arr1 = new int[80000];
        int[] temp1 = new int[arr1.length];
        for (int i = 0; i < 80000; i++) {
            arr1[i] = (int)(Math.random()*80000);
        }

        Date date1 = new Date();
        radixSort(arr1);

        Date date2 = new Date();
        long t = date2.getTime()-date1.getTime();
        System.out.println("归并排序耗时"+t+"ms");
    }

    //基数排序
    public static void radixSort(int[] arr){
        //找出该序列的最大值，得到其位数
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > max){
                max = arr[i];
            }
        }
        int maxLength = (max+"").length();//得到其位数

        //牺牲空间节约时间
        int[][] bucket = new int[10][arr.length];//定义一个二维数组表示桶，桶的深度用arr.length表示，防止溢出
        int[] bucketElementCounts = new int[10];//因为要从桶里取出数据，因此记录每个桶实际存放的元素个数

        int n = 1;//每轮要计算的位数
        for (int i = 0; i < maxLength; i++) {//每轮排序
            //1.将元素放入对应的桶中
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //2.将各个桶里的数据取出放入原数组中
            int index = 0;
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < bucketElementCounts[j]; k++) {
                    arr[index] = bucket[j][k];
                    index++;
                }
                bucketElementCounts[j] = 0;//将原计数桶清空
            }
            n *=10;
        }
    }

}
