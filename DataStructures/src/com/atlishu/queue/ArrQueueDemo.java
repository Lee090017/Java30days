package com.atlishu.queue;

import java.util.Scanner;

/**
 * @author lishustart
 * @create 2021-05-14-10:29
 *
 * 用数组实现队列
 */
public class ArrQueueDemo {
    public static void main(String[] args) {
        //创建一个队列
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';//接收用户输入
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while(loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头对的数据");
            key = sc.next().charAt(0);
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = sc.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出数据是%d\n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头的数据是%d\n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    sc.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

//使用数组模拟队列
class ArrayQueue{
    private int maxSize;//队列容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//模拟队列的数组

    //创建队列
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;//指向队列头部的前一个位置
        rear = -1;//指向队列的尾部(当前位置)
    }

    //判断队列是否满
    public boolean isFull(){
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n){
        //判断队列是否满
        if(isFull()){
            System.out.println("队列满，不能加入数据");
            return;
        }
        rear++;//让rear后移
        arr[rear] = n;
    }

    //获取队列的数据，出队列
    public int getQueue(){
        //判断队列是否空
        if(isEmpty()){
            //通过抛出异常
            throw new RuntimeException("队列为空，不能取数据");
        }
        front++;
        return arr[front];
    }

    //显示队列的所有数据
    public void showQueue(){
        //遍历
        if(isEmpty()){
            System.out.println("队列空，没有数据");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    //显示队列的头数据，注意不是取出数据
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("没有数据");
        }
        return arr[front+1];
    }
}
