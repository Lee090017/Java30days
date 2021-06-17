package com.atlishu.queue;

import java.util.Scanner;

/**
 * @author lishustart
 * @create 2021-05-14-17:18
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //创建一个队列
        CircleArrayQueue arrayQueue = new CircleArrayQueue(4);//其队列的有效数据最大是3
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

class CircleArrayQueue{
    private int maxSize;//队列容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//模拟队列的数组

    //创建队列
    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    //判断队列是否满
    public boolean isFull(){
        return (rear+1) % maxSize == front;
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
        arr[rear] = n;
        //将rear后移，这里必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据，出队列
    public int getQueue(){
        //判断队列是否空
        if(isEmpty()){
            //通过抛出异常
            throw new RuntimeException("队列为空，不能取数据");
        }
        //这里需要分析front是指向队列的第一个元素
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列的所有数据
    public void showQueue(){
        //遍历
        if(isEmpty()){
            System.out.println("队列空，没有数据");
            return;
        }
        //从front开始遍历，遍历多少个元素
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i % maxSize]);
        }
    }

    //当前队列有效数据的个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列的头数据，注意不是取出数据
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("没有数据");
        }
        return arr[front];
    }
}