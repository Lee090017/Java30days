package atlishu.com.java;


import java.util.Scanner;

public class Main {

    static int L;
    static int N;
    static int M;
    static String str;
    static int ans = 0;//查找到的有效字符串个数
//    static int c;


//    static ArrayList<Integer> lists;//记录所有质数
//    static StringBuilder str;
//    static int[][] arrays;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        L = sc.nextInt();//原串长L
        N = sc.nextInt();//原串中1的个数N
//        M = L - N;//原串中0的个数M
//        System.out.println(M);
        str = sc.next();
        dfs(0,0,0);
        //筛选可能是变换后的字符子串
        if(ans>=2){
            System.out.println("NOT UNIQUE");
        }else if(ans == 1){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }

    }

    //求字符串里1的个数
    public static int numOfOne(String str){
        int n = 0;
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i)=='1'){
                n++;
            }
        }
        return n;
    }

    private static void dfs(int start,int len,int sum){//len为字符串总长度，sum为字符串1的数量
        if(ans>1) return;
        if(len>L) return;
        if(sum>N) return;
        if(start >= str.length()){
            if(len == L && sum == N){
                ans++;
            }
            return;
        }

        if(str.charAt(start)=='0'){
            dfs(start+1,len+1,sum);
            return;
        }

        if(start!=0 && str.charAt(start-1)=='1'){
            dfs(start+1,len+1,sum+1);
            return;
        }
        dfs(start+1,len+1,sum+1);

        int l = 0;
        for (int i = start; i < str.length(); i++) {
            if(i!=start){
                l = Integer.valueOf(str.substring(start,i+1),2);//字符长度，也是1的数量
            }
            if(l+len>L || l+sum>N){
                break;
            }
            if(l>i-start+1&&(i+1==str.length()||(i+1<str.length()&&str.charAt(i+1)=='0'))){
                dfs(i+1,len+l,sum+l);
            }
        }
    }


}

