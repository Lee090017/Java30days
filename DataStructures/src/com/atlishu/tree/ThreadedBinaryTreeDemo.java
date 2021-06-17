package com.atlishu.tree;

/**
 * @author lishustart
 * @create 2021-06-08-11:17
 *
 * 线索二叉树
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        PersonNode root = new PersonNode(1, "tom");
        PersonNode node2 = new PersonNode(3, "jack");
        PersonNode node3 = new PersonNode(6, "smith");
        PersonNode node4 = new PersonNode(8, "mary");
        PersonNode node5 = new PersonNode(10, "king");
        PersonNode node6 = new PersonNode(14, "bob");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
//        threadedBinaryTree.infixthreadedNodes();//中序
//        threadedBinaryTree.prethreadedNodes();//前序
        threadedBinaryTree.postthreadedNodes();//后序

        //测试：以10号节点测试
        PersonNode leftNode = node6.getLeft();
        System.out.println("14号节点的前驱节点是"+leftNode);
        PersonNode rightNode = node6.getRight();
        System.out.println("14号节点的后继节点是"+rightNode);

        //线索的方式遍历线索二叉树
//        threadedBinaryTree.infixthreadedList();//中序遍历
//        threadedBinaryTree.prethreadedList();//前序遍历
        threadedBinaryTree.postthreadedList(root);//后序遍历

    }
}

//定义一个Binary Tree
class ThreadedBinaryTree{
    private PersonNode root;

    //为实现线索化，需要添加一个保存前一个节点的额指针
    private PersonNode pre = null;

    public void setRoot(PersonNode root) {
        this.root = root;
    }

    public void postthreadedNodes(){
        this.postthreadedNodes(root);
    }

    //编写对二叉树进行后序线索化的方法
    public void postthreadedNodes(PersonNode node){
        if(node==null){
            return;
        }
        postthreadedNodes(node.getLeft());
        postthreadedNodes(node.getRight());

        if(node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if(pre!=null && pre.getLeftType() == 1){
            pre.setRight(node);
            pre.setRightType(1);
        }

        pre = node;//每处理一个节点让当前节点为下一个节点的前驱节点


    }

    public void prethreadedNodes(){
        this.prethreadedNodes(root);
    }

    //编写对二叉树进行前序线索化的方法
    public void prethreadedNodes(PersonNode node){
        if(node==null){
            return;
        }

        if(node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if(pre!=null && pre.getLeftType() == 1){
            pre.setRight(node);
            pre.setRightType(1);
        }

        pre = node;//每处理一个节点让当前节点为下一个节点的前驱节点

        if(node.getLeftType()==1){
            return;
        }

        prethreadedNodes(node.getLeft());
        prethreadedNodes(node.getRight());
    }

    public void infixthreadedNodes(){
        this.infixthreadedNodes(root);
    }

    //编写对二叉树进行中序线索化的方法
    public void infixthreadedNodes(PersonNode node){
        if(node==null){
            return;
        }
        //先线索化左子树
        infixthreadedNodes(node.getLeft());
        //线索化当前节点
        if(node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if(pre!=null && pre.getRight()==null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;//每处理一个节点让当前节点为下一个节点的前驱节点
        //线索右子树
        infixthreadedNodes(node.getRight());
    }

    //前序遍历
    public void preOrder(){
        if (this.root!=null)
            this.root.preOrder();
        else
            System.out.println("当前二叉树为空，无法遍历");
    }

    //中序遍历
    public void infixOrder(){
        if (this.root!=null)
            this.root.infixOrder();
        else
            System.out.println("当前二叉树为空，无法遍历");
    }

    //后序遍历
    public void postOrder(){
        if (this.root!=null)
            this.root.postOrder();
        else
            System.out.println("当前二叉树为空，无法遍历");
    }

    //前序查找
    public PersonNode preOrderSearch(int id){
        if (this.root!=null)
            return this.root.preOrdersearch(id);
        else
            return null;
    }

    //中序查找
    public PersonNode infixOrderSearch(int id){
        if (this.root!=null)
            return this.root.infixOrdersearch(id);
        else
            return null;
    }

    //后序查找
    public PersonNode postOrderSearch(int id){
        if (this.root!=null)
            return this.root.postOrdersearch(id);
        else
            return null;
    }

    //删除节点
    public void delNode(int id){
        if (root!=null) {
            if(root.getId() == id){
                root = null;
            }else{
                root.deleteNode(id);
            }
        }else{
            System.out.println("空树,不能删除");
        }
    }

    //前序遍历线索化二叉树的方法
    public void prethreadedList(){
        PersonNode node = root;
        while(node!=null){
            while(node.getLeftType()==0){
                System.out.println(node);
                node = node.getLeft();
            }
            while (node!=null && node.getLeftType()==1){
                System.out.println(node);
                node = node.getRight();
            }
        }
    }

    //中序遍历线索化二叉树的方法
    public void infixthreadedList(){
        PersonNode node = root;
        while(node!=null){
            //循环找到leftTpye==1的节点，第一个找到的就是8节点
            while(node.getLeftType()==0){
                node = node.getLeft();
            }
            //打印当前节点
            System.out.println(node);
            while(node.getRightType()==1){
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    //后序遍历线索化二叉树的方法(递归)
    public void postthreadedList(PersonNode node){
        if(node==null){
            return;
        }
        if(node.getLeftType()==0) {
            postthreadedList(node.getLeft());
        }
        if(node.getRightType()==0){//避免右边的重复遍历
            postthreadedList(node.getRight());
        }
//        else{
//            while (node.getRightType()==1){
//                System.out.println(node);
//                node = node.getRight();
//            }
//            return;
//        }
        System.out.println(node);//打印顶点节点

    }
}

//创建HeroNode节点
class PersonNode{
    private int id;
    private String name;
    private PersonNode left;
    private PersonNode right;

    //1.如果leftType=0表示指向的是左子树，如果为1表示指向前驱结点
    //2.如果rightType=0表示指向的是右子树，如果为1表示指向后继结点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public PersonNode() {
    }

    public PersonNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonNode getLeft() {
        return left;
    }

    public void setLeft(PersonNode left) {
        this.left = left;
    }

    public PersonNode getRight() {
        return right;
    }

    public void setRight(PersonNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    //编写前序遍历的方法
    public void preOrder(){
        System.out.println(this);//先输出父节点
        //递归向左子树前序遍历
        if(this.left!=null){
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if(this.right!=null){
            this.right.preOrder();
        }
    }

    //编写中序遍历的方法
    public void infixOrder(){
        //递归向左子树前序遍历
        if(this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);//先输出父节点
        //递归向右子树前序遍历
        if(this.right!=null){
            this.right.infixOrder();
        }
    }

    //编写中序遍历的方法
    public void postOrder(){
        //递归向左子树前序遍历
        if(this.left!=null){
            this.left.postOrder();
        }
        //递归向右子树前序遍历
        if(this.right!=null){
            this.right.postOrder();
        }
        System.out.println(this);//先输出父节点
    }

    //前序查找
    public PersonNode preOrdersearch(int id){
        System.out.println("前序遍历~~~~");
        if(this.id==id){
            return this;
        }
        PersonNode resHeroNode = null;
        //递归向左子树前序遍历
        if(this.left!=null){
            resHeroNode = this.left.preOrdersearch(id);
        }
        if (resHeroNode != null){
            return resHeroNode;
        }
        //递归向右子树前序遍历
        if(this.right!=null){
            resHeroNode = this.right.preOrdersearch(id);
        }
        return resHeroNode;
    }

    //中序查找
    public PersonNode infixOrdersearch(int id){
        PersonNode resHeroNode = null;
        //递归向左子树前序遍历
        if(this.left!=null){
            resHeroNode = this.left.infixOrdersearch(id);
        }
        if (resHeroNode != null){
            return resHeroNode;
        }
        System.out.println("中序遍历~~~~");
        if(this.id==id){
            return this;
        }
        //递归向右子树前序遍历
        if(this.right!=null){
            resHeroNode = this.right.infixOrdersearch(id);
        }
        return resHeroNode;
    }

    //后序查找
    public PersonNode postOrdersearch(int id){
        PersonNode resHeroNode = null;
        //递归向左子树前序遍历
        if(this.left!=null){
            resHeroNode = this.left.postOrdersearch(id);
        }
        if (resHeroNode != null){
            return resHeroNode;
        }
        //递归向右子树前序遍历
        if(this.right!=null){
            resHeroNode = this.right.postOrdersearch(id);
        }
        if(resHeroNode!=null){
            return resHeroNode;
        }
        System.out.println("后序遍历~~~~");
        if(this.id==id){
            return this;
        }
        return resHeroNode;
    }

    //递归删除节点
    //1.如果删除的是叶子节点，就删除该节点
    //2.如果删除的是非叶子节点，则删除该子树
    public void deleteNode(int id){
        if (this.left != null && this.left.id == id){
            this.left = null;
            return;
        }

        if (this.right!= null && this.right.id == id){
            this.right = null;
            return;
        }

        if (this.left != null)
            this.left.deleteNode(id);

        if (this.right != null)
            this.right.deleteNode(id);
    }
}
