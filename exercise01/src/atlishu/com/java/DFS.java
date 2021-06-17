package atlishu.com.java;

import java.util.Scanner;

/**
 * @author lishustart
 * @create 2021-04-08-20:53
 *
 * 2n皇后（DFS深度优先搜索算法）
 */
public class DFS {

    static int n,sum=0;
    static int[][] chess;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        chess = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chess[i][j] = sc.nextInt();
            }
        }

        drop(0,2);
        System.out.println(sum);
    }

    //h为行数，queen为皇后编号：2黑/3白
    private static void drop(int h,int queen){
        if(h == n){
            if(queen == 2){
                drop(0,3);
            }else{
                sum++;
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if(chess[h][i]!=1) continue;
            if(Check(h,i,queen)){
                chess[h][i] = queen;
            }else{
                continue;
            }
            //进行下一层搜索
            drop(h+1,queen);
            //将已经放置的位置还原
            chess[h][i] = 1;
        }
        return;
    }

    //校验该位置是否可以放皇后
    private static boolean Check(int hang,int lie,int queen){
        //校验同列是否重复
        for (int i = 0; i < hang; i++) {
            if(chess[i][lie]==queen){
                return false;
            }
        }
        //校验对角线是否重复
        for (int i = hang-1,j = lie-1; i >=0 &&j >= 0; i--,j--) {
            if(chess[i][j]==queen){
                return false;
            }
        }
        for (int i = hang-1,j = lie+1; i >=0 &&j <= n-1; i--,j++) {
            if(chess[i][j]==queen){
                return false;
            }
        }
        return true;
    }
}
