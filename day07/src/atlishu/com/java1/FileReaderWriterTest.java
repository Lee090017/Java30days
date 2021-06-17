package atlishu.com.java1;

import org.junit.Test;

import java.io.*;

/**
 * @author lishustart
 * @create 2021-04-21-17:12
 *
 * 一、流的分类
 * 1.操作数据的单位：字节流、字符流
 * 2.流向：输入流输出流
 * 3.流的角色：节点流、处理流
 *
 * 二、流的体系结构
 * 抽象基类          节点流（文件流）       缓冲流(处理流的一种)
 * InputStream       FileInputStream      BufferedInputStream
 * OutputSteam       FileOutputStream     BufferedOutputStream
 * Reader            FileReader           BufferedReader
 * Writer            FileWriter           BufferedWriter
 * */
public class FileReaderWriterTest {

    /*
        1.read()的理解
        2.异常处理，为保证流的关闭，要用try-catch-final处理，否则流可能不回关闭
        3.读入文件一定要存在，否则会报文件找不到的异常
     */
    @Test//单元测试属于module下，可以直接用相对路径进行访问
    public void testFileReader(){
        FileReader fr = null;
        try {
            //1.实例化File类的对象，指明要操作的文件
            File file = new File("hello.txt");
            //2.具体的流
            fr = new FileReader(file);

            //3.数据的读入过程
            //read():返回读入的一个字符，如果达到了文件末尾，返回-1
            int data = fr.read();
            while(data != -1){
                System.out.print((char)data);
                data = fr.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.流的关闭操作
            try {
                if(fr!=null)
                    fr.close();//对于物理连接JVM的垃圾回收机制无能为力，需要手动关闭资源
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    /*
        对read()方法的操作升级,使用read的重载方法
    */
    @Test
    public void testFileReader1() throws IOException {
        //1.File类的实例化
        File file = new File("hello.txt");
        //2.FileReader流的实例化
        FileReader fr = new FileReader(file);
        //3.读入操作
        //read(char[] cbuf):返回每次读入cbuf数组中字符的个数，达到文件末尾返回-1
        char[] cbuf = new char[5];
        int len;
        while ((len = fr.read(cbuf))!=-1){
            //方式一
//            for (int i = 0; i < len; i++) {
//                System.out.print(cbuf[i]);
//            }
            //方式二
            String str = new String(cbuf,0,len);
            System.out.print(str);
        }
        //4.资源关闭
        fr.close();
    }

    /*
    从内存写出数据到硬盘的文件里

   说明：
   1.输出操作，对应的File如果不存在，将创建新的文件，
            如果存在将根据原本实例化的append是否是true来覆盖或者添加
     */
    @Test
    public void testFileWriter() throws IOException {
        //1.提供File类的对象，指明写出到的文件
        File file = new File("hi.txt");//写出时不需要原来的文件存在，系统会自动创建

        //2.提供FileWriter的对象，用于数据的写出
        FileWriter fw = new FileWriter(file);

        //3.写出的操作
        fw.write("I have a dream!\n");
        fw.write("I love China.");//没有换行

        //4.流的关闭
        fw.close();
    }

    /*
        使用FileReader和FileWriter实现文本的复制
     */
    @Test
    public void testFileReaderFileWriter(){
        FileReader fr = null;
        FileWriter fw = null;
        try {
            File file1 = new File("a.txt");
            File file2 = new File("b.txt");

            fr = new FileReader(file1);
            fw = new FileWriter(file2,true);

            char[] cbuf = new char[5];
            int len;
            while ((len = fr.read(cbuf))!=-1){
                fw.write(cbuf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fr!=null)
                    fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fw!=null)
                    fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
