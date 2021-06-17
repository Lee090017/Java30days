package com.atlishu.sort;

import java.util.Arrays;
import java.util.Date;

/**
 * @author lishustart
 * @create 2021-06-08-22:31
 *
 * 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4,6,8,5,9};
        heapsortASC(arr);
//        heapsortDESC(arr);
        System.out.println(Arrays.toString(arr));

        //创建一个80000个随机数的数组
        int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr1[i] = (int)(Math.random()*80000);
        }

        Date date1 = new Date();
//        heapsortASC(arr1);
        heapsortDESC(arr1);

        Date date2 = new Date();
        long t = date2.getTime()-date1.getTime();
//        System.out.println(Arrays.toString(arr1));
        System.out.println("插入排序耗时"+t+"ms");
    }

    //升序堆排序
    public static void heapsortASC(int[] arr){
        System.out.println("升序堆排序");
        //分布测试
        //先调整非叶子节点arr.length/2-1=1
//        adjustHeap(arr,1,arr.length);
//        System.out.println("调整叶子节点1："+ Arrays.toString(arr));

        //再调整非叶子节点1-1=0
//        adjustHeap(arr,0,arr.length);
//        System.out.println("调整叶子节点0："+ Arrays.toString(arr));

        //将数组变为大顶堆
        for (int i = arr.length/2-1; i >= 0; i--) {
            adjustHeapASC(arr,i,arr.length);
        }

        int temp = 0;//定义一个临时变量
        for (int i = arr.length-1; i > 0; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;

            adjustHeapASC(arr,0,i);
//            System.out.println(Arrays.toString(arr));
        }
    }

    //将一个数组（二叉树），调整成一个大顶堆

    /**
     * 功能：完成将以i指向的对应的非叶子节点的子树调整为局部大顶堆
     *      这里需要在外部从下到上调整
     * @param arr 待调整的数组
     * @param i 表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素继续调整
     */
    public static void adjustHeapASC(int[] arr,int i,int length){
        int temp = arr[i];
        for (int j = 2*i+1; j < length; j = 2*j+1) {//左子节点的索引
            if(j+1<length && arr[j+1]>arr[j]){//右子节点大于左子节点
                j++;
            }
            if(arr[j]>temp){
                arr[i] = arr[j];
                i = j;
            }else{
                break;//当从下至上调整时，当某父节点大于两个子节点时可以直接退出
            }
        }
        arr[i] = temp;
    }

    public static void heapsortDESC(int[] arr){
        System.out.println("降序堆排序");
        //分步测试
//        adjustHeapDESC(arr,1,arr.length);
//        System.out.println(Arrays.toString(arr));
//
//        adjustHeapDESC(arr,0,arr.length);
//        System.out.println(Arrays.toString(arr));
        for (int i = arr.length/2-1; i >= 0; i--) {
            adjustHeapDESC(arr,i,arr.length);
        }
        int temp = 0;
        for (int i = arr.length-1; i > 0; i--) {
            temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            adjustHeapDESC(arr,0,i);
        }
    }

    //降序排列
    //将原来的堆变为小顶堆
    public static void adjustHeapDESC(int[] arr,int i,int length){
        int temp = arr[i];
        for (int j = 2*i+1; j < length; j = j*2+1) {
            if(j+1<length && arr[j+1]<arr[j]){
                j++;
            }
            if(arr[j]<temp){
                arr[i] = arr[j];
                i = j;
            }else{
                break;
            }
        }
        arr[i] = temp;
    }

}
