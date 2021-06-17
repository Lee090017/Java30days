package com.atlishu.sparsearry;

import org.junit.Test;

import java.io.*;

/**
 * @author lishustart
 * @create 2021-05-13-22:01
 *
 * 稀疏数组
 *
 */
public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组11*11
        //0：表示没有棋子，1表示黑子，2表示蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始二维数组
        System.out.println("原始的二维数组：");
        for (int[] row:chessArr1) {
            for (int col:row) {
                System.out.print(col+" ");
            }
            System.out.println();
        }

        //将二维数组变为稀疏数组的思路
        //1.先遍历原来的二维数组，得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if(chessArr1[i][j]!=0){
                    sum++;
                }
            }
        }
        //2.创建对应的稀疏数组
        int[][] sparseArr = new int[sum+1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;

        //遍历稀疏数组，将非零的值放入稀疏数组
        int r = 1;//用于记录第几行
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if(chessArr1[i][j]!=0){
                    sparseArr[r][0] = i;
                    sparseArr[r][1] = j;
                    sparseArr[r][2] = chessArr1[i][j];
                    r++;
                }
            }
        }

        //输出稀疏数组的形式
        System.out.println("得到的稀疏数组：");
        for (int[] row:sparseArr) {
            for (int col:row) {
                System.out.print(col+"\t");
            }
            System.out.println();
        }

        //将稀疏数组恢复成原始数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i <= sparseArr[0][2]; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println("恢复的二维数组：");
        for (int[] row:chessArr2) {
            for (int col:row) {
                System.out.print(col+" ");
            }
            System.out.println();
        }

        saveFile(sparseArr);

    }

    //将稀疏数组保存到磁盘上，如sparseArr.data
    public static void saveFile(int[][] data){
        ObjectOutputStream oos = null;
        try {
            File file = new File("DataStructures\\sparseArr.data");
            FileOutputStream fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(oos!=null)
                    oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //读取硬盘上的数据进行恢复
    //为简洁，省略try-catch
    public static int[][] loadFile(String pathname) throws IOException, ClassNotFoundException {
        File file = new File(pathname);
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object obj = ois.readObject();
//        System.out.println(obj);
        int[][] arr = null;
        if(obj instanceof int[][]){
            arr = (int[][])obj;
        }
        return arr;
    }

    @Test
    public void test() throws IOException, ClassNotFoundException {
        int[][] sparseArr = loadFile("sparseArr.data");
        for (int[] row:sparseArr) {
            for (int col:row) {
                System.out.print(col+" ");
            }
            System.out.println();
        }
    }
}
