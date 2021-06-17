package com.atlishu.tree.avl;

/**
 * @author lishustart
 * @create 2021-06-15-20:34
 *
 * 平衡二叉树
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};//左旋转
//        int[] arr = {10, 12, 8, 9, 7, 6};//右旋转
//        int[] arr = {10, 11, 7, 6, 8, 9};//先左旋转再右旋转
        int[] arr = {2, 1, 6, 5, 7, 3};//先右旋转再左旋转

        // 创建一个AVTree
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        //遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();
        System.out.println();
        System.out.println("平衡处理后~~");
        System.out.println("树的高度="+avlTree.getRoot().height());//4
        System.out.println("左子树的高度="+avlTree.getRoot().leftHeight());//1
        System.out.println("右子树的高度="+avlTree.getRoot().rightHeight());//3
    }
}

//创建AVL树
class AVLTree{
    private Node root;

    public AVLTree() {
    }

    public AVLTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    //添加节点的方法
    public void add(Node Node){
        if(root==null){
            root = Node;
        }else{
            root.add(Node);
        }
    }

    //中序遍历
    public void infixOrder(){
        if(root!=null){
            root.infixOrder();
        }else{
            System.out.println("二叉排序树为空");
        }
    }

    //查找某一个节点
    public Node search(int value){
        Node Node = null;
        if(root!=null){
            Node = root.search(value);
        }else{
            System.out.println("二叉排序树为空");
        }
        if (Node == null){
            System.out.println("未找到值为"+value+"的节点");
        }else{
//            System.out.println("已找到值为"+value+"的节点");
        }
        return Node;
    }

    //查找某一个节点的父节点
    public Node searchParent(int value){
        Node Node = null;
        if(root!=null){
            if(root.value==value){
//                System.out.println("该节点为root节点，无父节点");
            }else{
                Node = root.searchParent(value);
            }
        }else{
            System.out.println("二叉排序树为空");
        }
        if (Node == null){
            System.out.println("未找到值为"+value+"的父节点");
        }else{
//            System.out.println("已找到值为"+value+"的父节点"+Node.value);
        }
        return Node;
    }


    //删除节点
    public void delete(int value){
        Node targetNode = search(value);//寻找目标节点
        Node parenttNode = searchParent(value);//寻找目标节点的父节点
        if(targetNode==null){
            System.out.println("删除失败");
        }else{
            //删除叶子节点
            if(targetNode.rightNode==null&&targetNode.leftNode==null){
                //1.如果该节点为根节点
                if(parenttNode==null){
                    root = null;
                }else{
                    //2.如果该节点为左子节点
                    if(parenttNode.leftNode == targetNode){
                        parenttNode.leftNode = null;
                    }else{//3.如果该节点为右子节点
                        parenttNode.rightNode = null;
                    }
                }//删除有一颗子树的节点
            }else if((targetNode.rightNode==null&&targetNode.leftNode!=null)||(targetNode.rightNode!=null&&targetNode.leftNode==null)) {
                //1.如果该节点为根节点
                if (parenttNode == null) {
                    //1.1 有左子树
                    if (targetNode.leftNode != null) {
                        root = targetNode.leftNode;
                    } else {//1.2 有右子树
                        root = targetNode.rightNode;
                    }
                } else {//2.如果不为根节点
                    //2.1如果该节点为左子节点
                    if (parenttNode.leftNode == targetNode) {
                        //2.1.1 有左子树
                        if (targetNode.leftNode != null) {
                            parenttNode.leftNode = targetNode.leftNode;
                        } else {//2.1.2 有右子树
                            parenttNode.leftNode = targetNode.rightNode;
                        }
                    } else {//2.2如果该节点为右子节点
                        //2.2.1 有左子树
                        if (targetNode.leftNode != null) {
                            parenttNode.rightNode = targetNode.leftNode;
                        } else {//2.2.2 有右子树
                            parenttNode.rightNode = targetNode.rightNode;
                        }
                    }
                }
            }else{//删除有两颗子树的节点
                //找到该节点右子树的最小节点值
                Node Node = targetNode.rightNode;
                while(true){
                    if(Node.leftNode!=null){
                        Node = Node.leftNode;
                    }else{
                        break;
                    }
                }
                int temp = Node.value;
                Node tempParent = searchParent(temp);
                if(tempParent.leftNode == Node){
                    tempParent.leftNode = null;
                }else{
                    tempParent.rightNode = null;
                }

                targetNode.value = temp;
            }
            System.out.println("删除成功");
        }
    }
}


//创建Node节点
class Node{
    int value;
    Node leftNode;
    Node rightNode;

    public Node(int value) {
        this.value = value;
    }

    //返回左子树的高度
    public int leftHeight(){
        if(leftNode==null){
            return 0;
        }
        return leftNode.height();
    }

    //返回右子树的高度
    public int rightHeight(){
        if(rightNode==null){
            return 0;
        }
        return rightNode.height();
    }

    //返回节点为根节点的高度
    public int height(){
        return Math.max(leftNode==null ? 0:leftNode.height(),rightNode==null ? 0:rightNode.height()) + 1;
    }

    //添加节点
    //递归的方式添加节点，需要满足二叉排序树
    public void add(Node Node){
        if(Node == null){
            return;
        }

        //判断传入的节点的值
        if(Node.value < this.value){
            if(this.leftNode == null){
                this.leftNode = Node;
                return;
            }else{
                this.leftNode.add(Node);
            }
        }else{
            if(this.rightNode == null){
                this.rightNode = Node;
                return;
            }else{
                this.rightNode.add(Node);
            }
        }

        //当添加完一个节点时，当右子树的高度-左子树高度 > 1，左旋转
        if(rightHeight() - leftHeight() > 1){
            //如果右子节点的左子树大于右子树高度，需要先对右子树进行右旋转
            if(rightNode != null && rightNode.leftHeight() - rightNode.rightHeight() > 0){
                rightNode.rightRotate();
            }

            leftRotate();//左旋转
            return;//避免无意义执行
        }

        //当添加完一个节点时，当左子树的高度-右子树高度 > 1，右旋转
        if(leftHeight() - rightHeight() > 1){
            //如果左子节点的右子树大于左子树高度，需要先对左子树进行左旋转
            if(leftNode != null && leftNode.rightHeight() - leftNode.leftHeight() > 0){
                leftNode.leftRotate();
            }
            rightRotate();//右旋转
        }
    }

    //中序遍历二叉树
    public void infixOrder(){
        if(this.leftNode != null){
            this.leftNode.infixOrder();
        }
        System.out.print(value+" ");
        if(this.rightNode != null){
            this.rightNode.infixOrder();
        }
    }

    //查找某一个要删除的节点
    public Node search(int value){
        if(this.value == value){
            return this;
        }
        Node Node = null;
        if(this.leftNode != null){
            Node = this.leftNode.search(value);
        }
        if(Node!=null){
            return Node;
        }
        if(this.rightNode != null){
            Node = this.rightNode.search(value);
        }
        return Node;
    }

    //查找某一个要删除的节点的父节点
    public Node searchParent(int value){
        if(this.leftNode==null && this.rightNode==null){
            return null;//为叶子节点
        }
        //判断当前节点是否为父节点
        if(this.leftNode!=null){
            if(this.leftNode.value == value){
                return this;
            }
        }
        if(this.rightNode!=null){
            if(this.rightNode.value == value){
                return this;
            }
        }
        Node Node = null;
        //向左遍历
        if(this.leftNode != null){
            Node = this.leftNode.searchParent(value);
        }
        if(Node!=null){
            return Node;
        }
        //向右遍历
        if(this.rightNode != null){
            Node = this.rightNode.searchParent(value);
        }
        return Node;
    }

    //左旋转
    private void leftRotate(){
        //创建新的节点，以当前根节点的值
        Node newNode = new Node(value);
        //把新的节点的左子树为当前节点的左子树
        newNode.leftNode = leftNode;
        //把新的节点的右子树设置成当前节点的右子树的左子树
        newNode.rightNode = rightNode.leftNode;
        //把当前节点的值替换为右子节点的值
        value = rightNode.value;
        //把当前节点的右子树设置为当前节点右子树的右子树
        rightNode = rightNode.rightNode;
        //当前节点的左子树设置为新的节点
        leftNode = newNode;
    }

    //右旋转
    public void rightRotate(){
        Node newNode = new Node(value);
        newNode.rightNode = rightNode;
        newNode.leftNode = leftNode.rightNode;
        value = leftNode.value;
        leftNode = leftNode.leftNode;
        rightNode = newNode;
    }
}