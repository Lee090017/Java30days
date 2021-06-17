package com.atlishu.hashTable;

import java.util.Scanner;

/**
 * @author lishustart
 * @create 2021-06-07-10:12
 */
public class HashTableDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //输入初始化信息
//        hashTab.add(new Emp(1,"tom"));
//        hashTab.add(new Emp(2,"lee"));
//        hashTab.add(new Emp(7,"mary"));
//        hashTab.add(new Emp(11,"rose"));
//        hashTab.add(new Emp(20,"jack"));
//        hashTab.add(new Emp(23,"bob"));
//        hashTab.add(new Emp(17,"james"));
//        hashTab.add(new Emp(16,"curry"));
//        hashTab.add(new Emp(3,"rick"));
//        hashTab.add(new Emp(10,"king"));
//        hashTab.add(new Emp(13,"lucy"));
//        hashTab.add(new Emp(6,"ace"));

        hashTab.addOrderById(new Emp(1,"tom"));
        hashTab.addOrderById(new Emp(2,"lee"));
        hashTab.addOrderById(new Emp(7,"mary"));
        hashTab.addOrderById(new Emp(11,"rose"));
        hashTab.addOrderById(new Emp(20,"jack"));
        hashTab.addOrderById(new Emp(23,"bob"));
        hashTab.addOrderById(new Emp(17,"james"));
        hashTab.addOrderById(new Emp(16,"curry"));
        hashTab.addOrderById(new Emp(30,"rick"));
        hashTab.addOrderById(new Emp(100,"king"));
        hashTab.addOrderById(new Emp(13,"lucy"));
        hashTab.addOrderById(new Emp(6,"ace"));

        //写一个简单点菜单
        String key = "";
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("add:   添加雇员");
            System.out.println("list:  显示雇员");
            System.out.println("find:  查找雇员");
            System.out.println("delete:删除雇员");
            System.out.println("exit:  退出系统");

            key = sc.next();
            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id = sc.nextInt();
                    System.out.println("输入名字");
                    String name = sc.next();
                    Emp emp = new Emp(id, name);
//                    hashTab.add(emp);
                    hashTab.addOrderById(emp);//按顺序添加雇员
                    break;

                case "list":
                    hashTab.list();
                    break;

                case "find":
                    System.out.println("请输入要查找的id：");
                    int fid = sc.nextInt();
                    hashTab.findEmpById(fid);
                    break;

                case "delete":
                    System.out.println("请输入要删除的id：");
                    int did = sc.nextInt();
                    hashTab.deleteEmpById(did);
                    break;

                case "exit":
                    loop = false;
                    break;

                default:
                    System.out.println("输入指令有误，请重新输入！");
                    break;
            }
        }

    }
}

//创建hash表
class HashTab{
    private EmpLinkedList[] empLinkedListArray;
    private int size;

    //构造器
    public HashTab(int size){
        this.size = size;
        //初始化empLinkedListArry
        empLinkedListArray = new EmpLinkedList[size];
        //注意：这里不要忘记初始化每个链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //编写散列函数
    public int hashFun(int id){
        return id  % size;
    }

    //添加雇员
    public void add(Emp emp){
        //计算要传入的链表号
        int empLinkedListNO = hashFun(emp.id);
        //添加雇员到指定的链表中
        empLinkedListArray[empLinkedListNO].add(emp);
    }

    //按顺序添加雇员
    public void addOrderById(Emp emp){
        int empLinkedListNo = hashFun(emp.id);
        empLinkedListArray[empLinkedListNo].addOrderById(emp);
    }

    //遍历所有链表
    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i+1);
        }
    }

    //根据输入id查找雇员
    public void findEmpById(int id){
        //使用散列函数，确定在哪条链表去查找
        int newId = hashFun(id);
        Emp emp = empLinkedListArray[newId].findEmpById(id);
        if(emp==null){
            System.out.println("未找到id为"+id+"的雇员");
        }else{
            System.out.println("在第"+(newId+1)+"条链表中找到 雇员 id = "+emp.id+" name = "+ emp.name);
        }
    }

    public void deleteEmpById(int id){
        int newId = hashFun(id);
        empLinkedListArray[newId].deleteEmpById(id);
    }
}

//创建一个雇员
class Emp{
    public int id;
    public String name;
    public Emp next;//next默认为空

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkedList,表示链表
class EmpLinkedList{
    //头指针，指向第一个Emp
    private Emp head;//默认为null

    //添加雇员到链表
    public void add(Emp emp){
        //如果头结点为空
        if(head == null){
            head = emp;
            return;
        }
        //如果不是第一个雇员，需要定位到最后的位置
        Emp curEmp = head;
        while(true){
            if(curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    //添加雇员的信息，要求按id号从小到大排序
    public void addOrderById(Emp emp){
        if(head==null){
            head = emp;
            return;
        }
        Emp curEmp = head;
        //判断第一个是否大于插入的雇员id
        if(curEmp.id>emp.id){
            emp.next = curEmp;
            head = emp;
            return;
        }
        while(true){
            if(curEmp.next==null){//到末尾
                curEmp.next = emp;
                return;
            }
            if (curEmp.id<emp.id && emp.id<curEmp.next.id){
                emp.next = curEmp.next;
                curEmp.next = emp;
                return;
            }
            curEmp = curEmp.next;
        }

    }

    //遍历链表的雇员信息
    public void list(int no){
        if(head == null){
            System.out.println("第"+no +"条链表为空");
            return;
        }
        System.out.println("第"+no +"条链表的信息为");
        Emp curEmp = head;
        while (curEmp!=null){
            System.out.printf("=> id=%d name=%s\t",curEmp.id,curEmp.name);
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    //根据id查找雇员
    public Emp findEmpById(int id){
        //判断链表是否为空
        if(head==null){
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while(true){
            if(curEmp.id == id){
                break;
            }
            if(curEmp.next==null){
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

    //根据id删除哈希表里对应的数据
    public void deleteEmpById(int id){
        if(head==null){
            System.out.println("链表为空");
            return;
        }
        Emp curEmp= head;
        //判断开头是否为要删除的雇员
        if(curEmp.id==id){
            head = curEmp.next;
            return;
        }
        while (true){
            if(curEmp.next.id==id){
                curEmp.next = curEmp.next.next;
                break;
            }
            if(curEmp.next==null){
                System.out.println("未找到指定id的雇员");
                break;
            }
            curEmp = curEmp.next;
        }
    }
}