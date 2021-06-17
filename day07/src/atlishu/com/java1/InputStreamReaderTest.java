package atlishu.com.java1;

import org.junit.Test;

import java.io.*;

/**
 * @author lishustart
 * @create 2021-04-22-15:45
 *
 * 处理流之二，转换流的使用
 *
 * InputStreamReader：将一个字节的输入流转换为字符的输入流（解码）
 * OutputStreamWriter：将一个字符的输出流转换为字节的输出流（编码）
 * 两者均属于字符流
 *
 * 字符集（编码解码集）
 *
 */
public class InputStreamReaderTest {
    @Test
    public void test1() throws IOException {
        FileInputStream fis = new FileInputStream("dbcp.txt");
        //参数二指明了字符集，具体使用哪个字符集取决于文件dbcp.txt保持时使用的字符集
        InputStreamReader isr = new InputStreamReader(fis,"UTF-8");//需要字符集
        char[] ch = new char[1024];
        int len;
        while ((len = isr.read(ch))!=-1){
            for (int i = 0; i < len; i++) {
                System.out.print(ch[i]);
            }
        }
        isr.close();
    }

    /*
       综合使用InputStreamReader和OutputStreamWriter
       将dbcp.txt-->dbcp_gbk.txt
     */
    @Test
    //这里简化将异常处理直接用throws代替，实际要使用try-catch
    public void test2() throws IOException {
        FileInputStream fis = new FileInputStream("dbcp_utf8.txt");
        FileOutputStream fos = new FileOutputStream("dbcp_gbk.txt");
        InputStreamReader isr = new InputStreamReader(fis,"UTF-8");//需要字符集
        OutputStreamWriter osw = new OutputStreamWriter(fos,"gbk");//需要字符集

        char[] ch = new char[1024];
        int len;
        while ((len = isr.read(ch))!=-1){
            osw.write(ch,0,len);
        }
        isr.close();
        osw.close();
    }

}
