package com.atlishu.sort;

import java.util.Date;

/**
 * @author lishustart
 * @create 2021-05-26-10:03
 *
 * 冒泡排序
 *
 * 从后往前依次比较相邻两个元素的大小，将大的数交换到最后面，在确定每一轮的最大数以后
 * 每轮比较的次数减一
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = new int[]{2,-1,3,0,-2,1};
//        bubbleSort(arr);
//        System.out.println(Arrays.toString(arr));

        //创建一个80000个随机数的数组
        int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr1[i] = (int)(Math.random()*80000);
        }

        Date date1 = new Date();
        bubbleSort(arr1);

        Date date2 = new Date();
        long t = date2.getTime()-date1.getTime();
        System.out.println("冒泡排序耗时"+t+"ms");
    }

    //冒泡排序
    public static void bubbleSort(int[] arr){
        int temp;
        //时间复杂度为O(n^2)
        for (int i = 0; i < arr.length-1; i++) {//需要比较的轮数
            boolean flag = false;//如果该轮没有发生交换
            for (int j = 0; j < arr.length-1-i; j++) {//该轮需要比较的次数
                if(arr[j]>arr[j+1]){//交换位置
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    flag = true;
                }
            }
            if(!flag){
                break;
            }
//            System.out.printf("第%d轮排序结果："+ Arrays.toString(arr)+"\n",i+1);
        }

    }
}
