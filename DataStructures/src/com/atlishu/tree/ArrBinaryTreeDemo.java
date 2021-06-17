package com.atlishu.tree;

/**
 * @author lishustart
 * @create 2021-06-08-10:23
 *
 * 顺序存储二叉树：以数组的形式顺序存储二叉树的结构
 * 其特点有：
 * 1.第n个元素的左子节点为2*n+1
 * 1.第n个元素的右子节点为2*n+2
 * 1.第n个元素的父节点为(n-1)/2
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder();//1 2 4 5 3 6 7
        System.out.println();
        arrayBinaryTree.infixOrder();//4 2 5 1 6 3 7
        System.out.println();
        arrayBinaryTree.postOrder();//4 5 2 6 7 3 1

    }
}

//编写一个ArrayBinaryTree，实现顺序存储二叉树的遍历
class ArrayBinaryTree{
    private int[] arr;

    public ArrayBinaryTree(int[] arr){
        this.arr = arr;
    }

    //重载preOrder，让代码更简洁
    public void preOrder(){
        preOrder(0);
    }

    public void preOrder(int index){//index表示数组的下标
        if(arr == null || arr.length == 0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        System.out.print(arr[index]+" ");
        //向左递归遍历
        if ((2*index+1) < arr.length)
            preOrder(2*index+1);
        //向右递归遍历
        if ((2*index+2) < arr.length)
            preOrder(2*index+2);
    }

    public void infixOrder(){
        infixOrder(0);
    }

    public void infixOrder(int index){//index表示数组的下标
        //向左递归遍历
        if ((2*index+1) < arr.length) {
            infixOrder(2*index+1);
        }
        if(arr == null || arr.length == 0){
            System.out.println("数组为空，不能按照二叉树的中遍历");
        }
        System.out.print(arr[index]+" ");
        //向右递归遍历
        if ((2*index+2) < arr.length)
            infixOrder(2*index+2);
    }

    public void postOrder(){
        postOrder(0);
    }

    public void postOrder(int index){//index表示数组的下标
        //向左递归遍历
        if ((2*index+1) < arr.length) {
            postOrder(2*index+1);
        }
        //向右递归遍历
        if ((2*index+2) < arr.length) {
            postOrder(2*index+2);
        }
        if(arr == null || arr.length == 0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        System.out.print(arr[index]+" ");
    }
}
