package com.atlishu.sort;

import java.util.Arrays;

/**
 * @author lishustart
 * @create 2021-05-27-11:14
 *
 * 总结各类排序算法
 */
public class SortExercise {
    public static void main(String[] args) {
        int[] arr = new int[]{49,38,65,97,76,13,27,49};
//        quickSort(arr,0,arr.length-1);//快速排序
//        shellSort(arr);//希尔排序
//        bubbleSort(arr);//冒泡排序
//        selectSort(arr);//选择排序
//        insertSort(arr);//插入排序
//        int[] temp = new int[arr.length];
//        mergeSort(arr,0,arr.length-1,temp);//归并排序
//        radixSort(arr);//基数排序
        heapSort(arr);//堆排序
        System.out.println(Arrays.toString(arr));
    }
    
    //堆排序
    public static void heapSort(int[] arr){
        for (int i = arr.length/2-1; i >= 0; i--) {
            adjustHeap(arr,i,arr.length);
        }
        int temp;
        for (int i = arr.length-1; i > 0; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,i);
        }
    }
    
    //将堆变为大顶堆
    //i为要调整的节点，length为要调整的数组的长度
    public static void adjustHeap(int[] arr,int i,int length){
        int temp = arr[i];
        for (int j = 2*i+1; j < length; j = j*2+1) {
            if(j+1<length && arr[j]<arr[j+1]){
                j++;
            }
            if(arr[j]>temp){
                arr[i] = arr[j];
                i = j;
            }else{
                break;
            }
        }
        arr[i] = temp;
    }

    //基数排序
    public static void radixSort(int[] arr){
        int maxValue = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxValue){
                maxValue = arr[i];
            }
        }
        int countOfElement = (maxValue+"").length();

        int[][] bucket = new int[10][countOfElement];
        int[] countOfBucket = new int[10];

        for (int i = 0,n = 1; i < countOfElement; i++,n*=10) {//轮数
            //装桶
            int index = 0;
            for (int j = 0; j < arr.length; j++) {
                index = arr[j]/n%10;
                bucket[index][countOfBucket[index]] = arr[j];
                countOfBucket[index]++;
            }
            //取数
            int x = 0;
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < countOfBucket[j]; k++) {
                    arr[x] = bucket[j][k];
                    x++;
                }
                countOfBucket[j] = 0;
            }
        }
    }

    //归并排序
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        if(left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;
        int j = mid+1;
        int t = 0;

        //将两个数组的元素依次放入temp
        while(i <= mid && j <= right){
            if(arr[i] < arr[j]){
                temp[t] = arr[i];
                i++;
                t++;
            }else{
                temp[t] = arr[j];
                j++;
                t++;
            }
        }

        //将非空的那个序列放入temp
        if(i <= mid){
            while (i <= mid){
                temp[t] = arr[i];
                i++;
                t++;
            }
        }else if(j <= right){
            while (j <= right){
                temp[t] = arr[j];
                j++;
                t++;
            }
        }

        //将temp给arr
        for (int k = 0; k < t; k++) {
            arr[left+k] = temp[k];
        }

//        System.out.println(Arrays.toString(arr));
    }

    //交换数组元素的位置
    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //快速排序
    public static void quickSort(int[] arr,int start,int end){
        int ref = arr[start];
        int low = start;
        int high = end;
        while(low < high){
            while(ref < arr[high] && low < high){
                high--;
            }
            while(ref > arr[low] && low < high){
                low++;
            }
            if(arr[low] == arr[high] && low < high){
                high--;
            }else{
                swap(arr,low,high);
            }
        }
        if(low-1 > start) quickSort(arr,start,low-1);
        if(low+1 < end) quickSort(arr,low+1,end);
    }

    //shell排序
    public static void shellSort(int[] arr){
        int insertVal;
        int insertIndex;
        for (int gap = arr.length/2; gap > 0; gap/=2) {//增量
            for (int i = gap; i < arr.length; i++) {
                insertVal = arr[i];
                insertIndex = i - gap;//同组相邻元素
                while(insertIndex >= 0 && insertVal < arr[insertIndex]){
                    arr[insertIndex+gap] = arr[insertIndex];
                    insertIndex -= gap;
                }
                if(insertIndex != i - gap){//发生了位移
                    arr[insertIndex+gap] = insertVal;
                }
            }
        }
    }

    //插入排序
    public static void insertSort(int[] arr){
        int insertVal;
        int insertIndex;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;
            while(insertIndex>=0 && insertVal < arr[insertIndex]){
                arr[insertIndex+1] = arr[insertIndex];
                insertIndex--;
            }
            if(insertIndex != i - 1){
                arr[insertIndex+1] = insertVal;
            }
        }
    }

    //冒泡排序
    public static void bubbleSort(int[] arr){
        boolean flag;
        for (int i = 0; i < arr.length-1; i++) {//要进行arr.length-1轮交换
            flag = false;
            for (int j = 0; j < arr.length-1-i; j++) {
                if(arr[j] > arr[j+1]){
                    swap(arr,j,j+1);
                    flag = true;//发生了交换
                }
            }
            if(!flag) break;
        }
    }

    //选择排序
    public static void selectSort(int[] arr){
        int minVal;
        int minIndex;
        for (int i = 0; i < arr.length-1; i++) {//一共arr.length-1轮
            minVal = arr[i];
            minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if(minVal > arr[j]){
                    minVal = arr[j];
                    minIndex = j;
                }
            }
            if(minIndex != i){
                swap(arr,i,minIndex);
            }
        }
    }

}
