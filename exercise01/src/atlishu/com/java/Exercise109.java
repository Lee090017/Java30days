package atlishu.com.java;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lishustart
 * @create 2021-04-06-9:30
 *
 * 十一届蓝桥杯研究生组
 */
public class Exercise109 {

    public static void main(String[] args) {
        //统计每个字母在仅出现一次的情况下，能被多少子串所包含；
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
//        System.out.println(str.lastIndexOf("b",2));
        char[] c = str.toCharArray();
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            int pre = -1;
            int next = str.length();
            if(str.lastIndexOf(c[i]+"",i-1) >= 0){
                pre = str.lastIndexOf(c[i]+"",i-1);
            }
            if(str.indexOf(c[i]+"",i+1) >= 0){
                next = str.indexOf(c[i]+"",i+1);
            }
            int a = (i-pre)*(next-i);
            sum = sum + a;
        }
        System.out.println(sum);


    }

    //计算一个字符串的分值
    public static int fenzhi(String str){
        char[] chars = str.toCharArray();
        HashMap<Character,Integer> hashMap = new HashMap<>();
        for (char c : chars) {
            if(!hashMap.containsKey(c)){
                hashMap.put(c,1);
            }else{
                hashMap.put(c,hashMap.get(c)+1);
            }
        }
        Collection<Integer> values = hashMap.values();
        int n = Collections.frequency(values,1);
        return n;
    }

    //求最大路径
    public static int getMax(Treenode node){
        if (node == null){
            return 0;
        }else{
            int l = getMax(node.left);
            int r = getMax(node.right);
            return Math.max(node.left_length,node.right_length)+Math.max(l,r);
        }
    }

    @Test
    public void test1(){
        /*
            78120有多少约数
         */
        int n = 78120;
        double s = Math.sqrt(n);
        int count = 0;
        for (int i = 1; i < s; i++) {
            if(n % i == 0){
                count ++;
            }
        }
        System.out.println(count*2);//96
    }

    @Test
    public void test2() throws ParseException {
        /*
            2000.1.1到2020.10.1，跑步多少公里，周一或初一跑2千米，其余1千米
         */
        //用到时间类Date与SimpleDateFormat的格式化与解析
        String begin = "2000-01-01";
        String end = "2020-10-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date p1 = sdf.parse(begin);
//        System.out.println(p1.toString());
        Date p2 = sdf.parse(end);
        long t = p2.getTime() - p1.getTime();
        long l = t / 24 / 60 / 60 / 1000 + 1;
        System.out.println("总天数："+l);//7580
        //计算周一或初一的数量
        // 7578/7 = 1082 .... 4,周一数量为1083
        // 12*20+10 = 250，初一的数量
        //既是周一又是初一天数为
        int equalsNum = 0;
        for (int i = 0; i < 20; i++) {
            String year = null;
            if(i < 10) {
                year = "200" + i;
            }else {
                year = "20" + i;
            }
//            System.out.println(year);
            for (int j = 1; j <= 12; j++) {
                String month = null;
                if(j < 10) {
                    month =  "0" + j;
                }else {
                    month = String.valueOf(j);
                }
                String d = year+"-"+month+"-01";
                Date date = sdf.parse(d);
                String str = date.toString();
                String substring = str.substring(0, 3);
                if(substring.equals("Mon")){
                    equalsNum++;
                }
            }

        }
        for (int j = 1; j <= 10; j++) {
            String month = null;
            if(j < 10) {
                month =  "0" + j;
            }else {
                month = String.valueOf(j);
            }
            String d = "2020-"+month+"-01";
            Date date = sdf.parse(d);
            String str = date.toString();
            String substring = str.substring(0, 3);
            if(substring.equals("Mon")){
                equalsNum++;
            }
        }
        System.out.println(equalsNum);
        //7580+1083+250-34=8879
    }

    @Test
    public void test3(){
        //20个圆和20条直线最多能把平面分成多少个部分
        //f(n)=n(n-1)+2
        //20*19+2=382

    }

    @Test
    public void test4(){
        /*
            蛇型数
         */
        //761
    }

    @Test
    public void test5(){
        /*
            排序
         */
        //jonmlkihgfedcba
    }

    @Test
    public void test6(){
        /*
            成绩统计
         */
        Scanner in = new Scanner(System.in);
        int numOfStudents = in.nextInt();
        double pass = 0;
        double perfect = 0;
        for (int i = 0; i < numOfStudents; i++) {
            int score = in.nextInt();
            if(score >= 60){
                pass++;
            }
            if(score >= 85){
                perfect++;
            }
        }
        System.out.println(Math.round(pass/numOfStudents*100)+"%");
        System.out.println(Math.round(perfect/numOfStudents*100)+"%");
    }

    @Test
    public void test7(){
        /*
            输出两行，每行1个八位数。
            第一行表示下一个回文日期，第二行表示下一个  型的回文日期。
         */
        Scanner in = new Scanner(System.in);
        long date = in.nextLong();
        long year = date/10000;
        long monthdate = date - year*10000;
        String bbbb = "";
        String cccc = "";
        int flag = 0;
        long a1 = year%10;
        long a2 = year/10%10;
        long a3 = year/100%10;
        long a4 = year/1000;
        long dd = a1*1000+a2*100+a3*10+a4;
        if(dd!=monthdate){
            year--;
        }
        while(true){
            year++;
            long d1 = year%10;
            long d2 = year/10%10;
            long d3 = year/100%10;
            long d4 = year/1000;
            if(d1*10+d2 > 12 || d1*10+d2 ==0|| d3*10+d4 > 31){
                continue;
            }else if(flag == 0){
                bbbb = year+""+d1+d2+d3+d4;
                flag++;
            }
            if(d1==d3 && d2==d4){
                cccc = year+""+d1+d2+d3+d4;
                break;
            }
        }
        System.out.println(bbbb);
        System.out.println(cccc);
    }

    @Test
    public void test8(){
        /*
            作物杂交
         */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();//N表示作物种类总数
        int M = sc.nextInt();//M表示初始拥有的作物种子类型数量
        int K = sc.nextInt();//K表示可以杂交的方案数
        int T = sc.nextInt();//T表示目标种子的编号

        int[] times = new int[N];
        for (int i = 0; i < N; i++) {
            times[i] = sc.nextInt();
        }
//        System.out.println(Arrays.toString(times));
        Integer[] begin = new Integer[M];
        for (int i = 0; i < M; i++) {
            begin[i] = sc.nextInt();
        }
        int[][] plan = new int[K][3];
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < 3; j++) {
                plan[i][j] = sc.nextInt();
//               System.out.println(plan[i][j]);
            }
        }
        HashMap<Integer,Integer[]> dictionary = new HashMap<>();
//        HashMap<Integer,Integer[]> dTime = new HashMap<>();
        for (int i = 0; i < K; i++) {
            dictionary.put(plan[i][2],new Integer[]{plan[i][0],plan[i][1]});
//            int m = Math.max(times[plan[i][0]],times[plan[i][1]]);
//            dTime.put(plan[i][2],new Integer[]{m});
        }

        List list = Arrays.asList(begin);
//        System.out.println(list);

        //创建数节点
        HashMap<Integer,Treenode> dic = new HashMap<>();
        for(int i = 0;i < M;i++){
            int index = begin[i];
            System.out.println(index);
            dic.put(index,new Treenode(index,0,0,null,null));
        }

        for (int i = 1; i <= N; i++) {
            if(list.contains(i)){
//                dic.put(i,new Treenode(i,0,0,null,null));
            }else{

                try {
                    dic.put(i,new Treenode(i,times[dictionary.get(i)[0]-1],times[dictionary.get(i)[1]-1],dic.get(dictionary.get(i)[0]),dic.get(dictionary.get(i)[1])));
                } catch (NullPointerException e) {
                    System.out.println("空指针异常");
                }

            }
            System.out.println(dic.get(i));
        }

        System.out.println(getMax(dic.get(T)));


        //递归测试
//        Treenode n1 = new Treenode(1,0,0,null,null);
//        Treenode n2 = new Treenode(2,0,0,null,null);
//        Treenode n3 = new Treenode(3,5,3,n1,n2);
//        Treenode n4 = new Treenode(4,5,4,n1,n3);
//        Treenode n5 = new Treenode(5,3,3,n2,n3);
//        Treenode n6 = new Treenode(6,6,4,n4,n5);
//
//        System.out.println(getMax(n6));//16
    }

    @Test
    public void test9(){
        /*
            子串分值
         */
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
//        System.out.println(str);
//        System.out.println(fenzhi(str));
        int sum = 0;
        //获取子字符串
        for (int i = 0; i < str.length(); i++) {//字符串起始位置
            for (int j = 1; j <= str.length()-i; j++) {//窗的长度
                String s = str.substring(i,i+j);
//                System.out.println(s);
                int a = fenzhi(s);
                sum = sum + a;
            }
        }
        System.out.println(sum);

    }
}

//创建一个二叉树
class Treenode{
    int value;
    int left_length;
    int right_length;
    Treenode left = null;
    Treenode right = null;

    public Treenode(int value, int left_length, int right_length, Treenode left, Treenode right) {
        this.value = value;
        this.left_length = left_length;
        this.right_length = right_length;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "Treenode{" +
                "value=" + value +
                ", left_length=" + left_length +
                ", right_length=" + right_length +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}