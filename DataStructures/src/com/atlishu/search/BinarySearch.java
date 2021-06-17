package com.atlishu.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lishustart
 * @create 2021-06-05-17:49
 *
 * 二分法查找--只适用于顺序数列
 */
public class BinarySearch {
    public static void main(String[] args) {
//        int[] arr = new int[]{1,8,10,1000,1000,1000,1000,1000,1000,1234};
        int[] arr = new int[]{1000,1000,1000,1000,1000,1000,1000,1000};
        int index = binarySearch(arr, 1000, 0, arr.length - 1);
        System.out.println(index);

        List<Integer> list = binarySearchAll(arr, 1000, 0, arr.length - 1);
        System.out.println(list);
    }

    /**
     * 二分查找算法
     * @param arr 需要查找的有序数组
     * @param value 需要查找的值
     * @param left 数组开始查找的起始索引
     * @param right 数组查找的结束索引
     * @return
     */
    public static int binarySearch(int[] arr,int value,int left,int right){
        if(left>right){
            return -1;//未找到
        }
        int mid = (left + right)/2;

        if(arr[mid] > value){
            return binarySearch(arr,value,left,mid-1);
        }else if(arr[mid] < value){
            return binarySearch(arr,value,mid+1,right);
        }else{
            return mid;
        }
    }

    //查找数组中所有的某个值
    public static List<Integer> binarySearchAll(int[] arr, int value, int left, int right){
        if(left>right){
            return new ArrayList<>();
        }
        int mid = (left + right)/2;

        if(arr[mid] > value){
            return binarySearchAll(arr,value,left,mid-1);
        }else if(arr[mid] < value){
            return binarySearchAll(arr,value,mid+1,right);
        }else{
            //将该索引位置左右两边相同的值对应的索引也放入集合
            ArrayList<Integer> list = new ArrayList<>();
            list.add(mid);
            int l = mid - 1;
            int r = mid + 1;

            //向左找
            while(l>=0 && arr[l]==value){
                list.add(l);
                l--;
            }
            //向右找
            while(r<arr.length && arr[r]==value){
                list.add(r);
                r++;
            }
            return list;
        }
    }

}
