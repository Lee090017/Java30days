package com.atlishu.sort;

import java.util.Date;

/**
 * @author lishustart
 * @create 2021-05-26-19:55
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = new int[]{2,-1,3,0,-2,1};
//        insertSort(arr);
//        System.out.println(Arrays.toString(arr));

        //创建一个80000个随机数的数组
        int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr1[i] = (int)(Math.random()*80000);
        }

        Date date1 = new Date();
        insertSort(arr1);

        Date date2 = new Date();
        long t = date2.getTime()-date1.getTime();
//        System.out.println(Arrays.toString(arr1));
        System.out.println("插入排序耗时"+t+"ms");
    }

    //插入排序，移位法
    public static void insertSort(int[] arr){
        int insertVal;//要插入的数
        int insertIndex;//要插入的位置
        for (int i = 1; i < arr.length; i++) {//从第二个元素开始选择无序表
            //依次向前比较找到可以插入的元素位置，比该元素大的元素往后移动一位
            insertVal = arr[i];
            insertIndex = i-1;

            //寻找插入的位置
            while(insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex+1] = arr[insertIndex];
                insertIndex--;
            }

            if(insertIndex != i-1){
                arr[insertIndex+1] = insertVal;
            }

        }
    }
}
