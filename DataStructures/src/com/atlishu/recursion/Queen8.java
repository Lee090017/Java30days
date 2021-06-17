package com.atlishu.recursion;

/**
 * @author lishustart
 * @create 2021-05-25-21:23
 *
 * 八皇后问题
 *
 * 8×8的国际象棋盘，任意两个皇后不能处于同一行、同一列或同一条斜线上，问有多少种摆法
 */
public class Queen8 {

    static int count;
    static int[][] cheese = new int[8][8];

    public static void main(String[] args) {
//        System.out.println(check(1,1));
        dfs(0);
        System.out.println(count);
    }

    //递归搜索
    public static void dfs(int i){//起始行
        if(i == 7){//到最后一行
            //求出唯一剩的棋子列
            for (int j = 0; j < 8; j++) {
                if(check(i,j)){
                    count++;
                    return;
                }
            }
            return;
        }

        for (int j = 0; j < 8; j++) {
            if(cheese[i][j]==1) continue;
            if(check(i,j)){
                cheese[i][j] = 1;
            }else continue;
            //进行下一层搜索
            dfs(i+1);
            //还原放置的位置
            cheese[i][j] = 0;
        }
        return;
    }

    //某一个棋子要放的位置是否符合八皇后规则
    public static boolean check(int i,int j){//i行，j列
        //遍历已经存在的棋子
        for (int k = 0; k < i; k++) {
            for (int l = 0; l < 8; l++) {
                if(cheese[k][l] == 1) {//有棋子
                    if(k == i || l == j){//同行同列
                        return false;
                    }
                    if(i-k==j-l || i-k==l-j){//同斜线
                        return false;
                    }
                }
            }
        }
        return true;
    }
}




