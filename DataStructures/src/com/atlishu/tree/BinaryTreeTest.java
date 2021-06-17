package com.atlishu.tree;

/**
 * @author lishustart
 * @create 2021-06-07-15:34
 */
public class BinaryTreeTest {
    public static void main(String args[]){
        //创建二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "公孙胜");
        HeroNode node5 = new HeroNode(5, "林冲");

        //手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);
        binaryTree.setRoot(root);

        //测试
        System.out.println("前序遍历：");
        binaryTree.preOrder();//1 2 3 5 4

        System.out.println("中序遍历：");//2 1 5 3 4
        binaryTree.infixOrder();

        System.out.println("后序遍历：");//2 5 4 3 1
        binaryTree.postOrder();

        System.out.println("======================================");
        System.out.println("前序查找：");
        System.out.println(binaryTree.preOrderSearch(5));//4次

        System.out.println("中序查找：");
        System.out.println(binaryTree.infixOrderSearch(5));//3次

        System.out.println("后序查找：");
        System.out.println(binaryTree.postOrderSearch(5));//2次

        System.out.println("======================================");
        System.out.println("删除叶子节点前，前序遍历：");
        binaryTree.preOrder();
        binaryTree.delNode(3);
        System.out.println("删除叶子节点后，前序遍历：");
        binaryTree.preOrder();

    }

}

//定义一个Binary Tree
class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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
    public HeroNode preOrderSearch(int id){
        if (this.root!=null)
            return this.root.preOrdersearch(id);
        else
            return null;
    }

    //中序查找
    public HeroNode infixOrderSearch(int id){
        if (this.root!=null)
            return this.root.infixOrdersearch(id);
        else
            return null;
    }

    //后序查找
    public HeroNode postOrderSearch(int id){
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

}

//创建HeroNode节点
class HeroNode{
    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode() {
    }

    public HeroNode(int id, String name) {
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
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
    public HeroNode preOrdersearch(int id){
        System.out.println("前序遍历~~~~");
        if(this.id==id){
            return this;
        }
        HeroNode resHeroNode = null;
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
    public HeroNode infixOrdersearch(int id){
        HeroNode resHeroNode = null;
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
    public HeroNode postOrdersearch(int id){
        HeroNode resHeroNode = null;
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
