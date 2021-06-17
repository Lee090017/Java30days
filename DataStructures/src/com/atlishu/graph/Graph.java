package com.atlishu.graph;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author lishustart
 * @create 2021-06-16-16:23
 */
public class Graph {

    private ArrayList<String> vertexList;//存储顶点的集合
    private int[][] edges;//存储图对应的邻接矩阵
    private int numOfEdges;//表示边的数目
    //定义数组记录某个顶点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args){
        int n = 8;
        String[] vertex = {"V0","V1","V2","V3","V4","V5","V6","V7"};
        Graph graph = new Graph(n);
        for (int i = 0; i < n; i++) {
            graph.insertVertex(vertex[i]);
        }

        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        graph.insertEdge(3,7,1);
        graph.insertEdge(4,7,1);
        graph.insertEdge(2,5,1);
        graph.insertEdge(5,6,1);
        graph.insertEdge(2,6,1);
        graph.showGraph();
        //老师代码
//        graph.dfs();//V0->V1->V3->V7->V4->V2->V5->V6
//        graph.bfs();//V0->V1->V2->V3->V4->V5->V6->V7

        //自己代码
//        graph.dfsTest();//V0->V1->V3->V7->V4->V2->V5->V6
        graph.bfsTest();//V0->V1->V2->V3->V4->V5->V6->V7

    }

    //构造器
    public Graph(int n){//n是顶点的个数
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    //插入节点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     *
     * @param v1 表示第一个点的下标
     * @param v2 表示第二个点的下标
     * @param weight 权值
     */
    //添加边
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;//无向图
        numOfEdges++;
    }

    //图中常用的方法

    //返回节点的个数
    public int getNumOfVertex(){
        return vertexList.size();
    }

    //发回边的个数
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回节点i（下标）对应的数据
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    //返回两节点的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGraph(){
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                System.out.print(edges[i][j]+" ");
            }
            System.out.println();
        }
    }

    //得到第一个邻接节点的下标
    public int getFirstNeighbor(int index){
        for (int i = 0; i < vertexList.size(); i++) {
            if(edges[index][i] > 0){
               return i;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下标，返回下一个邻接节点的下标
    public int getNextNeighbor(int v1,int v2){
        for (int i = v2+1; i < vertexList.size(); i++) {
            if(edges[v1][i] > 0){
                return i;
            }
        }
        return -1;
    }

    //广度优先遍历算法
    private void bfs(boolean[] isVisited,int i){
        int u;//表示队列的头结点对应的下标
        int w;//邻接节点w
        //队列，记录节点的访问顺序
        LinkedList queue = new LinkedList();
        //访问节点，输出节点信息
        System.out.print(getValueByIndex(i)+"->");
        isVisited[i] = true;
        queue.addLast(i);

        while (!queue.isEmpty()){
            //取出队列的头结点下标
            u = (Integer) queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w!=-1){
                if(!isVisited[w]){
                    System.out.print(getValueByIndex(w)+"->");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                //以u为前驱点，找w后面的下一个邻接节点
                w = getNextNeighbor(u,w);//体现出广度优先
            }
        }
    }

    //遍历所有的节点都进行广度优先
    public void bfs(){
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

    //深度优先遍历算法
    private void dfs(boolean[] isVisited,int i){
        System.out.print(getValueByIndex(i)+"->");
        isVisited[i] = true;
        int w = getFirstNeighbor(i);
        while (w!=-1){
            if(!isVisited[w]){
                dfs(isVisited,w);
            }
            w = getNextNeighbor(i,w);
        }
    }

    //对dfs进行重载，遍历所有节点
    public void dfs(){
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    //dfs简写版
    public void dfs2(boolean[] isVisited,int i){
        if(i==0 && !isVisited[i]){
            System.out.print(getValueByIndex(i)+"->");
            isVisited[i] = true;
        }
        for (int j = 0; j < isVisited.length; j++) {
            if(!isVisited[j] && edges[i][j] > 0){
                System.out.print(getValueByIndex(j)+"->");
                isVisited[j] = true;
                dfs2(isVisited,j);
            }
        }
        return;
    }

    public void dfsTest(){
        dfs2(isVisited,0);
    }

    //bfs简写版
    public void bfs2(boolean[] isVisited,int i){
        LinkedList<Integer> list = new LinkedList<>();//存放队列

        if(i==0 && !isVisited[i]){
            System.out.print(getValueByIndex(i)+"->");
            isVisited[i] = true;
            list.addLast(i);
        }

        int w = 0;
        while (!list.isEmpty()) {
            w = list.removeFirst();
            for (int j = 0; j < isVisited.length; j++) {
                if (!isVisited[j] && edges[w][j] > 0) {
                    System.out.print(getValueByIndex(j) + "->");
                    isVisited[j] = true;
                    list.addLast(j);
                }
            }
        }
    }

    public void bfsTest(){
        bfs2(isVisited,0);
    }

}
