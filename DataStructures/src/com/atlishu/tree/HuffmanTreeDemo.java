package com.atlishu.tree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author lishustart
 * @create 2021-06-09-16:08
 *
 * 树的应用：HuffmanTree
 */
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        Node huffmanTree = createHuffmanTree(arr);

        //测试霍夫曼树
        huffmanTree.preOrder();
    }

    //创建赫夫曼树
    public static Node createHuffmanTree(int[] arr){
        //1.遍历arr数组
        //2.将arr的每个元素构成一个Node
        //3.将Node放入到ArrayList中
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            nodes.add(new Node(arr[i]));
        }

        //处理的过程是一个循环的过程
        while(nodes.size()>1) {

            //排序
            Collections.sort(nodes);

            //取出根节点权值最小的两个二叉树
            Node left = nodes.get(0);
            Node right = nodes.get(1);

            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;

            //删除已经处理过的二叉树
            nodes.remove(left);
            nodes.remove(right);

            //将parent加入Nodes
            nodes.add(parent);

//            System.out.println(nodes);
        }

        return nodes.get(0);
    }
}

//创建节点类
class Node implements Comparable<Node>{
    int value;//节点权值
    Node left;//左右子节点
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;//从小到大进行排列
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null)
            this.left.preOrder();
        if(this.right!=null)
            this.right.preOrder();
    }
}
