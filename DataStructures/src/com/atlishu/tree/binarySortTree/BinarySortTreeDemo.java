package com.atlishu.tree.binarySortTree;

/**
 * @author lishustart
 * @create 2021-06-10-22:11
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int[] arr = new int[]{7,3,10,12,5,1,9,2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i:arr) {
            binarySortTree.add(new Node(i));
        }

        System.out.println("删除前中序排序：");
        binarySortTree.infixOrder();
        System.out.println();

//        binarySortTree.search(12);
//        binarySortTree.searchParent(5);

        binarySortTree.delete(2);
        binarySortTree.delete(5);
        binarySortTree.delete(9);
        binarySortTree.delete(12);
        binarySortTree.delete(7);
        binarySortTree.delete(3);
        binarySortTree.delete(10);
        binarySortTree.delete(1);
        System.out.println("删除后中序排序：");
        binarySortTree.infixOrder();
        System.out.println();

    }
}

//创建二叉排序树
class BinarySortTree{
    private Node root;
    //添加节点的方法
    public void add(Node node){
        if(root==null){
            root = node;
        }else{
            root.add(node);
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
        Node node = null;
        if(root!=null){
            node = root.search(value);
        }else{
            System.out.println("二叉排序树为空");
        }
        if (node == null){
            System.out.println("未找到值为"+value+"的节点");
        }else{
//            System.out.println("已找到值为"+value+"的节点");
        }
        return node;
    }

    //查找某一个节点的父节点
    public Node searchParent(int value){
        Node node = null;
        if(root!=null){
            if(root.value==value){
//                System.out.println("该节点为root节点，无父节点");
            }else{
                node = root.searchParent(value);
            }
        }else{
            System.out.println("二叉排序树为空");
        }
        if (node == null){
            System.out.println("未找到值为"+value+"的父节点");
        }else{
//            System.out.println("已找到值为"+value+"的父节点"+node.value);
        }
        return node;
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
                Node node = targetNode.rightNode;
                while(true){
                    if(node.leftNode!=null){
                        node = node.leftNode;
                    }else{
                        break;
                    }
                }
                int temp = node.value;
                Node tempParent = searchParent(temp);
                if(tempParent.leftNode == node){
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

    //添加节点
    //递归的方式添加节点，需要满足二叉排序树
    public void add(Node node){
        if(node == null){
            return;
        }

        //判断传入的节点的值
        if(node.value < this.value){
            if(this.leftNode == null){
                this.leftNode = node;
                return;
            }else{
                this.leftNode.add(node);
            }
        }else{
            if(this.rightNode == null){
                this.rightNode = node;
                return;
            }else{
                this.rightNode.add(node);
            }
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
        Node node = null;
        if(this.leftNode != null){
            node = this.leftNode.search(value);
        }
        if(node!=null){
            return node;
        }
        if(this.rightNode != null){
            node = this.rightNode.search(value);
        }
        return node;
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
        Node node = null;
        //向左遍历
        if(this.leftNode != null){
            node = this.leftNode.searchParent(value);
        }
        if(node!=null){
            return node;
        }
        //向右遍历
        if(this.rightNode != null){
            node = this.rightNode.searchParent(value);
        }
        return node;
    }


}
