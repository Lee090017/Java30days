package com.atlishu.linkedlist;

import java.util.Stack;

/**
 * @author lishustart
 * @create 2021-05-16-14:40
 */
public class SingleLinkedListExercise {

    public static void main(String[] args) {
        //创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "公孙胜", "入云龙");
        HeroNode hero5 = new HeroNode(6, "林冲", "豹子头");

        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addOrder(hero1);
        singleLinkedList.addOrder(hero2);
        singleLinkedList.addOrder(hero4);
        singleLinkedList.addOrder(hero3);
        singleLinkedList.addOrder(hero5);

//        singleLinkedList.list();
        System.out.println("单链表节点数："+getLength(singleLinkedList.getHead()));

        System.out.println(findLastIndexNode(singleLinkedList.getHead(),3));

//        System.out.println("反转链表：");
//        reverseList(singleLinkedList.getHead());
//        singleLinkedList.list();

        System.out.println("逆序打印链表：");
        reversePrint(singleLinkedList.getHead());//该方法不改变原来链表结构

        //================================
        //创建节点
        HeroNode hero6 = new HeroNode(5, "关胜", "大刀");
        HeroNode hero7 = new HeroNode(7, "秦明", "霹雳火");
        HeroNode hero8 = new HeroNode(8, "呼延灼", "双鞭");

        //创建链表
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        singleLinkedList1.addOrder(hero8);
        singleLinkedList1.addOrder(hero6);
        singleLinkedList1.addOrder(hero7);

//        singleLinkedList1.list();

        System.out.println("合并之后的链表：");
        mergeLinkedList(singleLinkedList.getHead(),singleLinkedList1.getHead());
        singleLinkedList.list();
    }

    //求单链表中节点的个数
    public static int getLength(HeroNode head){
        int sum = 0;
        HeroNode temp = head.next;
        if (temp==null){
            return 0;
        }
        while(temp!=null){
            sum++;
            temp = temp.next;
        }
        return sum;
    }

    //查找单链表中倒数第k个节点
    //先遍历得到长度，再去找这个节点正数的位置
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        if(head.next==null){
            return null;
        }
        int length = getLength(head);
        if(index<0 || index>length){
            return null;
        }
        HeroNode temp = head.next;
        for (int i = 0; i < length-index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    //单链表的反转
    //遍历原来的链表，每遍历一个节点就将这个节点取出来放在新的链表头
    public static void reverseList(HeroNode head){
        //如果链表为空或只有一个节点，链表均不用反转
        if (head.next==null||head.next.next==null){
            return;
        }
        HeroNode cur = head.next;
        HeroNode next = null;//指当前节点的下一个节点
        HeroNode reverseHead = new HeroNode(0," "," ");
        while(cur!=null){
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        head.next = reverseHead.next;
    }

    //从尾到头打印单链表
    //用栈的方式实现（先进后出）
    public static void reversePrint(HeroNode head){
        if(head.next==null){
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = head.next;
        while(temp!=null){
            stack.add(temp);
//            stack.push(temp);
            temp = temp.next;
        }

        while(stack.size()>0){
            System.out.println(stack.pop());
        }

    }

    //合并两个有序的单链表，合并之后的链表依然有序
    public static void mergeLinkedList(HeroNode head1,HeroNode head2){
        if(head2.next == null){
            return;
        }
        if(head1.next == null){
            head1 = head2;
        }
        HeroNode temp = head2.next;//链表2的第一个节点
        while(temp!=null){
            HeroNode cur = head1;//链表1的头结点
            while (true){
                if(cur.next==null && cur.id != temp.id){
                    cur.next = temp;
                    break;
                }
                if(cur.id==temp.id){
                    break;
                }
                if(cur.id<temp.id && cur.next.id>temp.id){
                    temp.next = cur.next;
                    cur.next = temp;
                    break;
                }
                cur = cur.next;
            }
            temp = temp.next;
        }
    }
}
