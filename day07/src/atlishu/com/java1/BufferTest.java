package atlishu.com.java1;

import org.junit.Test;

import java.io.*;

/**
 * @author lishustart
 * @create 2021-04-22-9:37
 *
 * 缓冲流的测试-提高文件的读写效率
 * 原因：提供内部的缓冲区
 *
 * BufferedInputStream
 * BufferedOutputStream
 * BufferedRead
 * BufferedWrite
 *
 * 处理流就是“包”在已有流的基础上
 */
public class BufferTest {

    /*
        非文本文件的复制
     */
    public void copyOfBufferStream(String srcPath,String desPath){
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            //1.造文件
            File file1 = new File(srcPath);
            File file2 = new File(desPath);
            //2.造流
            //2.1 造节点流
            FileInputStream fis = new FileInputStream(file1);
            FileOutputStream fos = new FileOutputStream(file2);
            //2.2 造缓冲流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);
            //3.读写操作
            byte[] bytes = new byte[1024];
            int len;
            while((len = bis.read(bytes))!=-1){
                bos.write(bytes,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭流
            //要求：先关闭外层的流，再关内层
            try {
                if(bis!=null)
                    bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(bos!=null)
                    bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //说明：在关闭外层流的同时，内层流也会自动关闭，因此内层流的关闭可以省略
//        fis.close();
//        fos.close();
        }

    }

    @Test
    public void test1(){
        long start = System.currentTimeMillis();

        copyOfBufferStream("F:\\JAVA学习视频\\第07天视频\\05_servlet生命周期介绍.avi","c:\\io\\视频复制2.avi");

        long end = System.currentTimeMillis();
        System.out.println("数据流复制时间为："+(end - start)+"ms");//272ms
    }

    public void copyOfBufferFile(String srcPath,String desPath){
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(new File(srcPath)));
            bw = new BufferedWriter(new FileWriter(new File(desPath)));

            String s;
            while((s=br.readLine())!=null){
                bw.write(s+"\n");//s中不包含换行符
//                System.out.println(s);
            }
            System.out.println("复制成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br!=null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(bw!=null)
                    bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test2(){
        long start = System.currentTimeMillis();

        copyOfBufferFile("file1.txt","file2.txt");

        long end = System.currentTimeMillis();
        System.out.println("数据流复制时间为："+(end - start)+"ms");//272ms
    }

}
