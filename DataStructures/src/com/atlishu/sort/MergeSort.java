package com.atlishu.sort;

import java.util.Arrays;
import java.util.Date;

/**
 * @author lishustart
 * @create 2021-05-27-15:26
 *
 * 归并排序
 *
 * 利用分治策略，分解为一些小问题，利用递归解决
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{49,38,65,97,76,13,27,49};
        int[] temp = new int[arr.length];
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(arr));

        //创建一个80000个随机数的数组
        int[] arr1 = new int[80000];
        int[] temp1 = new int[arr1.length];
        for (int i = 0; i < 80000; i++) {
            arr1[i] = (int)(Math.random()*80000);
        }

        Date date1 = new Date();
        mergeSort(arr1,0,arr1.length-1,temp1);

        Date date2 = new Date();
        long t = date2.getTime()-date1.getTime();
        System.out.println("归并排序耗时"+t+"ms");
    }

    //分+治
    //left和right表示两组元素的开始索引
    //temp为用于存储合并结果的临时数组
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        if(left < right){
            int middle = (left + right)/2;//中间索引
            mergeSort(arr,left,middle,temp);//左分解
            mergeSort(arr,middle+1,right,temp);//右分解
            merge(arr,left,middle,right,temp);//合并
        }
    }

    //合并算法
    public static void merge(int[] arr,int left,int middle,int right,int[] temp){
        int i = left;//第一组元素的起始索引
        int j = middle+1;//第二组元素的起始索引
        int t = 0;//temp数组的起始索引

        //1.先交替将两组有序序列的元素放入temp数组，直达某一组元素为空
        while(i <= middle && j <= right){
            if(arr[i] < arr[j]){
                temp[t] = arr[i];
                i++;
            }else{
                temp[t] = arr[j];
                j++;
            }
            t++;
        }

        //2.将不为空的数组元素全添加到temp数组
        while(i <= middle){//第一组数据还未传完
            temp[t] = arr[i];
            i++;
            t++;
        }

        while(j <= right){//第二组数据还未传完
            temp[t] = arr[j];
            j++;
            t++;
        }

        //3.将temp的元素复制到arr里（并不是复制所有数据）
        for (int k = 0; k < t; k++) {
            arr[left+k] = temp[k];
        }

    }
}
