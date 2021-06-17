package com.atlishu.search;

/**
 * @author lishustart
 * @create 2021-06-05-18:40
 *
 * 插值查找
 *
 * 属于改进版的二分查找算法，在确定mid时不要直接用(left+right)/2
 * 改为left + (right - left)* (key - arr[left])/(arr[right] - arr[left])
 *
 * 当数字分布不均匀时，该方法不一定比二分查找的方法好
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        int index = insertValueSearch(arr, 1, 0, arr.length - 1);
        System.out.println(index);
    }

    public static int insertValueSearch(int[] arr,int value,int left,int right){

        //value < arr[0] || value > arr[arr.length-1]必须添加
        //当查找的值value特别大时会造成角标越界
        if(left>right || value<arr[0] || value>arr[arr.length-1]){
            return -1;//未找到
        }
        int mid = left + (right - left)* (value - arr[left])/(arr[right] - arr[left]);

        if(arr[mid] > value){
            return insertValueSearch(arr,value,left,mid-1);
        }else if(arr[mid] < value){
            return insertValueSearch(arr,value,mid+1,right);
        }else{
            return mid;
        }
    }
}
