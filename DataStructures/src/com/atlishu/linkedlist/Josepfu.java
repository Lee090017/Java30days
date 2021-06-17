package com.atlishu.linkedlist;

/**
 * @author lishustart
 * @create 2021-05-17-21:10
 *
 * 约瑟夫问题
 * 单相环形链表
 */
public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(25);
        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(1,10,25);
    }
}

//创建一个环形链表
class CircleSingleLinkedList{
    //创建一个first节点
    private Boy first;
    //添加小孩，构成一个环形的链表
    public void addBoy(int num){
        if(num < 1){
            System.out.println("num值不正确");
            return;
        }
        Boy curBoy = null;//辅助指针
        for (int i = 1; i <= num; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            if(i==1){
                first = boy;
                first.setNext(first);//构成环
                curBoy = first;
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }

        }
    }

    //遍历当前环形链表
    public void showBoy(){
        if(first == null){
            System.out.println("没有任何小孩");
            return;
        }
        Boy curBoy = first;
        while (true){
            System.out.printf("小孩的编号%d\n",curBoy.getId());
            if(curBoy.getNext() == first){//说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    //学生出圈
    //startId表示第几个小孩开始数,countNum表示数几下,nums表示最初有多少个小孩在圈中
    public void countBoy(int startId,int countNum,int nums){
        //校验
        if(first == null || startId <1 || startId> nums){
            System.out.println("数据有误，请重新输入");
            return;
        }
        Boy helper = first;
        int n = 1;
        while (true) {
            if(helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，需要将first和helper移动startId-1次
        for (int i = 0; i < startId-1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first和helper指针同时移动m-1次，然后出圈
        while (true){
            if(helper==first){
                System.out.println(first.getId());
                break;
            }
            for (int i = 0; i < countNum-1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println(first.getId());

            first = first.getNext();
            helper.setNext(first);
        }
    }
}

class Boy{
    private int id;
    private Boy next;

    public Boy() {
    }

    public Boy(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
