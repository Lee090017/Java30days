package atlishu.com.java;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class NetTraffic {

    static int n;//点数
    static int m;//边数
    static int[][] graph;
    static int maxLiu;
    static ArrayList<Integer> node;
    static ArrayList<Integer> pipe;
    static int[] roadFlag;
//    static ArrayList<Integer> lists;//记录所有质数
//    static StringBuilder str;
//    static int[][] arrays;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        graph = new int[m][3];
        roadFlag = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                graph[i][j] = sc.nextInt();
            }
        }
        node = new ArrayList<>();
        pipe = new ArrayList<>();
        dfsMaxLiu(1);
        System.out.println(maxLiu);


    }

    public static void dfsMaxLiu(int cur){
        if(roadFlag[cur-1]!=0 || pipe.contains(0)){
            return;
        }
        if(cur == n){
            int a = Collections.min(pipe);
//            System.out.println(cur);
            for (int i : node) {
                graph[i][2] = graph[i][2] - a;//该管道的流量已经占用
            }
            for (int i = 0; i < pipe.size(); i++) {
                pipe.set(i,pipe.get(i)-a);
            }
            maxLiu = maxLiu + a;
//            System.out.println(maxLiu);
            return;
        }else{
            for (int i = 0; i < m; i++) {
                //该管道的流量不为0
                if(graph[i][0]==cur && graph[i][2]!=0){
                    roadFlag[cur-1] = 1;
                    if(roadFlag[graph[i][1]-1]==0){
                        node.add(i);
                        pipe.add(graph[i][2]);
                        dfsMaxLiu(graph[i][1]);
                        pipe.remove(pipe.size()-1);
                        node.remove(node.size()-1);
                    }
                    roadFlag[cur-1] = 0;
                }
            }
        }
    }
}

