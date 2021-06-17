package com.atlishu.linkedlist;

/**
 * @author lishustart
 * @create 2021-05-17-19:37
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //创建节点
        HeroNode1 hero1 = new HeroNode1(1, "宋江", "及时雨");
        HeroNode1 hero2 = new HeroNode1(2, "卢俊义", "玉麒麟");
        HeroNode1 hero3 = new HeroNode1(3, "吴用", "智多星");
        HeroNode1 hero4 = new HeroNode1(4, "公孙胜", "入云龙");
        HeroNode1 hero5 = new HeroNode1(5, "林冲", "豹子头");

        //创建链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addOrder(hero5);
        doubleLinkedList.addOrder(hero2);
        doubleLinkedList.addOrder(hero3);
        doubleLinkedList.addOrder(hero4);
        doubleLinkedList.addOrder(hero1);

        doubleLinkedList.list();

        HeroNode1 hero6 = new HeroNode1(5, "关胜", "大刀");
        doubleLinkedList.update(hero6);
        System.out.println("修改后的链表情况：");
        doubleLinkedList.list();

        doubleLinkedList.del(3);
        System.out.println("删除之后的链表情况：");
        doubleLinkedList.list();


    }
}

class DoubleLinkedList{
    //先初始化一个头结点
    private HeroNode1 head = new HeroNode1(0," "," ");

    public HeroNode1 getHead() {
        return head;
    }

    //遍历双向链表的方法
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode1 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp.toString());
            //将next后移
            temp = temp.next;
        }
    }

    //添加一个节点到双向链表的最后
    public void add(HeroNode1 heroNode){
        //
        HeroNode1 temp = head;
        //遍历节点，找到最后
        while(true){
            if(temp.next==null){//遍历到最后
                break;
            }
            temp = temp.next;//temp后移
        }

        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //按照编号顺序添加
    public void addOrder(HeroNode1 heroNode){
        if (head.next == null){
            head.next = heroNode;
            heroNode.pre = head;
            return;
        }

        HeroNode1 temp = head;
        while(true){
            if(temp.next==null && temp.id != heroNode.id){
                temp.next = heroNode;
                heroNode.pre = temp;
                break;
            }
            if(temp.id == heroNode.id){
                System.out.printf("该编号%d已经存在\n",temp.id);
                break;
            }
            if(temp.id < heroNode.id && temp.next.id > heroNode.id){
                heroNode.next = temp.next;
                temp.next = heroNode;
                heroNode.next.pre = heroNode;
                heroNode.pre = temp;
                break;
            }
            temp = temp.next;
        }
    }

    //修改一个节点的内容，可以看到双向链表的节点内容修改与单向链表一样
    public void update(HeroNode1 newHeroNode){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode1 temp = head.next;
        boolean flag = false;
        while(true){
            if(temp == null){
                break;
            }
            if(temp.id == newHeroNode.id){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else{
            System.out.printf("没找到编号为%d的节点，不能修改\n",newHeroNode.id);
        }
    }

    //删除
    //双向链表可以直接找到要删除的这个节点，直接删除
    public void del(int id){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode1 temp = head.next;
        boolean flag = false;
        while(true){
            if(temp == null){
                break;
            }
            if(temp.id == id){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.pre.next = temp.next;
            if(temp.next!=null) {
                temp.next.pre = temp.pre;//假如是最后一个节点,
            }
        }else{
            System.out.printf("没找到编号为%d的节点，不能删除\n",id);
        }
    }

}


//创建一个双向链表的类
class HeroNode1{
    public int id;
    public String name;
    public String nickname;
    public HeroNode1 next;//指向下一个节点
    public HeroNode1 pre;//指向下一个节点
    //构造器

    public HeroNode1(int id, String name, String nickname) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方法，我们重写

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
