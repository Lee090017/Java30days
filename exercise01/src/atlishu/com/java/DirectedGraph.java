package atlishu.com.java;

import java.util.Scanner;

/**
 * @author lishustart
 * @create 2021-04-11-19:05
 *
 * 求有向图的最小路径
 */
public class DirectedGraph {
    static int n;//点数
    static int m;//边数
    static int[][] road;//路径
    static int[] r1;
    static int[] r2;
    static int[] r3;
    static int minRoad;
    static int [] flag;//避免出现重复的点
    static int[] everyPoint;//剪枝
//    static HashMap<Integer, ArrayList<Integer>[]> dictionary;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        road = new int[m][3];
        r1 = new int[m];
        r2 = new int[m];
        r3 = new int[m];
        flag = new int[n];
        everyPoint = new int[n];
//        dictionary = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                road[i][j] = sc.nextInt();
            }
            r1[i]=road[i][0];
            r2[i]=road[i][1];
            r3[i]=road[i][2];
        }
        for (int i = 2; i <= n; i++) {
            minRoad = 0;
            drop(1,i,0);
            System.out.println(minRoad);
        }



    }

    //深度搜索
    public static void drop(int cur,int end,int dis){

        if(dis > everyPoint[cur-1] && everyPoint[cur-1]!=0){
            return;
        }

        if(cur == end){
            if(minRoad > dis || minRoad==0){
                minRoad = dis;
            }
            return;
        }

        for (int i = 0; i < m; i++) {
            if(r1[i]==cur) {
                flag[cur - 1] = 1;//该点已走过
                if (flag[r2[i] - 1] == 0) {
                    drop(r2[i],end,dis+r3[i]);
                }
                flag[cur - 1] = 0;
            }
        }
        return;
    }
}
