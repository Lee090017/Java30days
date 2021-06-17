package com.atlishu.recursion;

/**
 * @author lishustart
 * @create 2021-05-25-19:51
 *
 * 迷宫问题
 */
public class MiGong {

    static int count = 0;

    public static void main(String[] args) {
        //先创建二维数组模拟迷宫
        int[][] map = new int[8][7];
        //使用1表示墙
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(i==0 || i==7 || j==0 || j==6){
                    map[i][j] = 1;
                }
            }
        }
        map[3][1] = 1;//设置挡板
        map[3][2] = 1;
        System.out.println("原地图：");
        showMap(map);

        //使用递归回溯给小球找路
//        setWay2(map,1,1);
//        System.out.println("走过后的地图：");
//        showMap(map);

        //求最短路径的条数
        dfs(map,1,1);
        System.out.println("最短路径的条数："+count);

    }

    //输出地图
    public static void showMap(int[][] map){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    //使用递归给小球找路
    //从[1,1]到[6,5],当map[i][j] = 0表示该点没走过
    //1表示墙，2表示通路可以走，3表示该点已经走过但是走不通
    //走迷宫的策略为下->右->上->左,如果走不通再回溯
    public static boolean setWay(int[][] map,int i,int j){
        if(i==6 && j==5){//通路已经找到
            map[i][j] = 2;
            return true;
        }else{
            if(map[i][j]==0){
                map[i][j] = 2;
                if(setWay(map,i+1,j)){//向下
                    return true;
                }else if(setWay(map,i,j+1)){//向右
                    return true;
                }else if(setWay(map,i-1,j)){//向上
                    return true;
                }else if(setWay(map,i,j-1)){//向左
                    return true;
                }else{
                    map[i][j] = 3;//该点走不通
                    return false;
                }
            }else{//map[i][j]！=0
                return false;
            }
        }
    }

    //修改策略：上->右->下->左
    public static boolean setWay2(int[][] map,int i,int j){
        if(i==6 && j==5){//通路已经找到
            map[i][j] = 2;
            return true;
        }else{
            if(map[i][j]==0){
                map[i][j] = 2;
                if(setWay2(map,i-1,j)){
                    return true;
                }else if(setWay2(map,i,j+1)){
                    return true;
                }else if(setWay2(map,i+1,j)){
                    return true;
                }else if(setWay2(map,i,j-1)){
                    return true;
                }else{
                    map[i][j] = 3;//该点走不通
                    return false;
                }
            }else{//map[i][j]！=0
                return false;
            }
        }
    }

    //求该迷宫问题最短路径有多少种？
    //该棋子只能向下或向右走
    public static void dfs(int[][] map,int i,int j){
        if(i == 1 && j == 1){
            map[i][j] = 2;
        }
        if(map[6][5] == 2){
//            System.out.println("==================");
//            showMap(map);
            count++;
            return;
        }else{
            for (int k = 0; k < 2; k++) {//每个棋子可以向下(0)走或向右(1)走
                if(k == 0 && map[i+1][j] == 0){
                    i++;
                    map[i][j] = 2;
                    dfs(map,i,j);
                    map[i][j] = 0;
                    i--;
                }
                if(k == 1 && map[i][j+1] == 0){
                    j++;
                    map[i][j] = 2;
                    dfs(map,i,j);
                    map[i][j] = 0;
                    j--;
                }
            }
            return;
        }
    }
}
