package com.atlishu.linkedlist;

/**
 * @author lishustart
 * @create 2021-05-14-19:40
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "公孙胜", "入云龙");
        HeroNode hero5 = new HeroNode(4, "林冲", "豹子头");

        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addOrder(hero1);
        singleLinkedList.addOrder(hero2);
        singleLinkedList.addOrder(hero4);
        singleLinkedList.addOrder(hero3);
//        singleLinkedList.add(hero2);
        singleLinkedList.list();
        System.out.println("=============================================");

        //修改链表
        singleLinkedList.update(hero5);
        singleLinkedList.list();
        System.out.println("=============================================");

        //删除链表
        singleLinkedList.del(2);
        singleLinkedList.list();
    }
}

//定义SingleLinkedList管理我们的英雄
class SingleLinkedList{
    //先初始化一个头结点
    private HeroNode head = new HeroNode(0," "," ");

    //添加节点到单相列表(根据先后顺序添加链表)
    public void add(HeroNode heroNode){
        //
        HeroNode temp = head;
        //遍历节点，找到最后
        while(true){
            if(temp.next==null){//遍历到最后
                temp.next = heroNode;
                break;
            }
            temp = temp.next;//temp后移
        }
    }

    //添加节点到单相列表(根据id号添加链表)
    public void addOrder(HeroNode heroNode){
        //
        HeroNode temp = head;
        //遍历节点，找到最后
        while(true){
            if(temp.next==null && temp.id != heroNode.id){//遍历到最后
                temp.next = heroNode;
                break;
            }
            if(heroNode.id == temp.id) {//排名重复
                System.out.printf("添加失败，成员%d已存在\n",heroNode.id);
                break;
            }else if(heroNode.id > temp.id && heroNode.id < temp.next.id){
                heroNode.next = temp.next;
                temp.next = heroNode;
                break;
            }

            temp = temp.next;//temp后移
        }
    }

    //根据id号更改节点信息
    public void update(HeroNode newHeroNode){
       if(head.next == null){
           System.out.println("链表为空");
           return;
       }
       HeroNode temp = head.next;
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

    //根据id号删除节点
    public void del(int id){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head;
        boolean flag = false;
        while(true){
            if(temp.next == null){
                break;
            }
            if(temp.next.id == id){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.next = temp.next.next;
        }else{
            System.out.printf("没找到编号为%d的节点，不能删除\n",id);
        }
    }

    public void list(){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while(true){
            if(temp==null){
                break;
            }
            System.out.println(temp.toString());
            //将next后移
            temp = temp.next;
        }
    }

    public HeroNode getHead() {
        return head;
    }
}

//定义HeroNode,每个HeroNode对象是一个节点
class HeroNode{
    public int id;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点
    //构造器

    public HeroNode(int id, String name, String nickname) {
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
