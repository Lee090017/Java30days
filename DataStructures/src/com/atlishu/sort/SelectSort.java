package com.atlishu.sort;

import java.util.Date;

/**
 * @author lishustart
 * @create 2021-05-26-19:23
 *
 * 选择排序
 *
 * 先找arr[0]-arr[n-1]里最小的数将其与arr[0]交换，再找arr[1]-arr[n-1]里最小的数将其与arr[1]交换
 * 依次类推，直到比较arr[n-2]-arr[n-1]
 *
 */
public class SelectSort {
    public static void main(String[] args) {
//        int[] arr = new int[]{2,-1,3,0,-2,1};
//        selectSort(arr);
//        System.out.println(Arrays.toString(arr));

        //创建一个80000个随机数的数组
        int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr1[i] = (int)(Math.random()*80000);
        }

        Date date1 = new Date();
        selectSort(arr1);

        Date date2 = new Date();
        long t = date2.getTime()-date1.getTime();
        System.out.println("选择排序耗时"+t+"ms");
    }

    //选择排序
    public static void selectSort(int[] arr){
        int temp;
        int index;
        //时间复杂度为O(n^2)
        for(int i = 0;i < arr.length-1;i++){//要交换的最小元素的位置
            //假设该位置的数为最小
            index = i;
            //找出该元素到arr[n-1]里最小的元素索引
            for (int j = i+1; j < arr.length; j++) {//要比较的元素的位置
                if(arr[j] < arr[index]){
                    index = j;
                }
            }

            //由于交换次数较少，所以总体的执行时间比冒泡排序要短
            if(index != i){//找到了最小的元素索引
                temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
            }
        }
    }
}
