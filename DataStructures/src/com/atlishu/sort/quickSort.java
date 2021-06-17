package com.atlishu.sort;

import java.util.Date;

/**
 * @author lishustart
 * @create 2021-05-27-9:02
 *
 * 快速排序
 *
 * 以第一个元素ref为参考，定义两个标志指针low和high，从high开始向前移动，交换ref与小于ref的
 * 元素位置，然后low向后移动，交换大于ref的元素位置，直到high<low结束查找
 */
public class quickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{49,38,65,97,76,13,27,49};
//        int[] arr = new int[]{1};
        quickSort(arr,0,arr.length-1);
//        System.out.println(Arrays.toString(arr));
        //创建一个80000个随机数的数组
        int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr1[i] = (int)(Math.random()*80000);
        }

        Date date1 = new Date();
        quickSort(arr1,0,arr1.length-1);

        Date date2 = new Date();
        long t = date2.getTime()-date1.getTime();
//        System.out.println(Arrays.toString(arr1));
        System.out.println("插入排序耗时"+t+"ms");
    }

    public static void quickSort(int[] arr,int start,int end){
        int ref = arr[start];
        int low = start;
        int high = end;
        while(low < high){
            while (arr[high] > ref && low < high){
                high--;
            }
            while (arr[low] < ref && low < high){
                low++;
            }
            if(arr[low] == arr[high] && low < high){
                high--;
            }else{
                swap(arr,low,high);
            }
//            System.out.println(Arrays.toString(arr)+','+low+','+high);
        }
        if(low-1 > start) quickSort(arr,start,low-1);
        if(low+1 < end) quickSort(arr,low+1,end);

    }

    //交换两个元素位置
    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
