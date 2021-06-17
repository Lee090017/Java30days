package atlishu.com.java;

import java.util.Scanner;

/**
 * @author lishustart
 * @create 2021-04-17-21:37
 *
 * 背包问题
 */
public class PackageProblem {

    static int N;//N件物品
    static int V;//容量为V的背包
    static int[][] A;//A为对应的物品体积和价值
    static int[][] packageArray;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        V = sc.nextInt();
        A = new int[N][2];
        packageArray = new int[N+1][V+1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2; j++) {
                A[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < N+1; i++) {
            for (int j = 0; j < V+1; j++) {
                if(i==0 || j==0){
                    packageArray[i][j] = 0;
                    continue;
                }
                if(A[i-1][0]>j){
                    packageArray[i][j] = packageArray[i-1][j];
                }else{
                    packageArray[i][j] = Math.max(packageArray[i-1][j],packageArray[i-1][j-A[i-1][0]]+A[i-1][1]);
                }
            }
        }
        System.out.println(packageArray[N][V]);
    }


}
