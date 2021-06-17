package atlishu.com.java;

/**
 * @author lishustart
 * @create 2021-04-11-17:26
 * 树的遍历：前、中、后序遍历
 *
 */
import java.util.Arrays;
import java.util.Scanner;
public class Tree {
    static int n;
    static int[] preOrder;
    static int[] inOrder;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        preOrder = new int[n];
        inOrder = new int[n];
        for (int i = 0; i < n; i++) {
            preOrder[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            inOrder[i] = sc.nextInt();
        }
//        System.out.println();
        TreeNode treeNode = reConstructBinaryTree(preOrder,inOrder);
        lastOrderShow(treeNode);
    }

    //已知前序和中序遍历递归重建二叉树
    public static TreeNode reConstructBinaryTree(int[] pre,int[] in){
        if(pre.length==0 || in.length==0){
            return null;
        }
        TreeNode node = new TreeNode(pre[0]);
        for (int i = 0; i < in.length; i++) {
            if(pre[0]==in[i]){//寻找中序遍历的根节点位置
                node.leftTree = reConstructBinaryTree(Arrays.copyOfRange(pre,1,i+1),Arrays.copyOfRange(in,0,i));
                node.rightTree = reConstructBinaryTree(Arrays.copyOfRange(pre,i+1,pre.length),Arrays.copyOfRange(in,i+1,in.length));
            }
        }
        return node;
    }

    //递归后序遍历二叉树
    public static void lastOrderShow(TreeNode node){
        if(node == null){
            return;
        }
        lastOrderShow(node.leftTree);
        lastOrderShow(node.rightTree);
        System.out.print(node.value+" ");
    }
}

//创建一个二叉树的结构
class TreeNode{
    int value;
    TreeNode leftTree;
    TreeNode rightTree;

    public TreeNode(int value) {
        this.value = value;
    }

}