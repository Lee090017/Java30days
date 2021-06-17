package atlishu.com.java1;

import org.junit.Test;

import java.io.*;

/**
 * @author lishustart
 * @create 2021-04-22-17:09
 *
 * 其他流的使用
 * 1.标准的输入输出流
 * 2.打印流
 * 3.数据流
 */
public class OtherStreamTest {
    /*
    1.标准的输入输出流
    1.1
    System.in默认从键盘输入
    System.out默认从控制台输出
    1.2
    System的setIn和setOut可以对默认进行修改
     */

    //1.3练习一：从键盘输入字符串，要求将要求的字符串转化为大写输出，然后继续进行输入操作，直到输入
    //e或者exit时退出程序
    public static void main(String[] args) throws IOException {
        //使用System.in实现
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s;
        while (!(s = br.readLine()).equalsIgnoreCase("exit")){
            System.out.println(s.toUpperCase());
        }
        br.close();
    }

    /*
        2.打印流
        实现基本数据类型的数据转换为字符串输出
        PrintStream和PrintWriter
        2.1 提供了一系列重载的print()
     */

    /*
        3.数据流
        3.1 DataInputStream和DataOutputStream
        3.2 作用：用于读取或写出基本数据类型的变量或字符串
     */

    //练习：将内存中的字符串、基本数据类型写出到文件中
    @Test
    public void test() throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("doc.txt"));

        dos.writeUTF("lishu");
        dos.flush();//刷新操作，将内存中的数据写入文件
        dos.writeInt(25);
        dos.flush();
        dos.writeBoolean(true);
        dos.flush();

        dos.close();
    }
    //练习：将文件中存储的字符串、基本数据类型读取到内存变量中
    @Test
    public void test1() throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream("doc.txt"));
        //按照上面的写入的顺序去读取
        String s = dis.readUTF();
        int i = dis.readInt();
        boolean b = dis.readBoolean();

        System.out.println(s);
        System.out.println(i);
        System.out.println(b);
    }
}
