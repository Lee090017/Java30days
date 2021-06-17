package atlishu.com.java1;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author lishustart
 * @create 2021-04-22-8:44
 */
public class FileInputOutputStreamTest {
    /*
    结论：
    1.对于文本文件(.txt,.java,.c,.cpp)，使用字符流处理
    2.对于非文本文件(.jpg,.mp3,.mp4,.avi,.doc,.ppt)使用字节流处理
     */

    public void copyOfStream(String scrPath,String destPath) {
        FileInputStream fis1 = null;
        FileOutputStream fis2 = null;
        try {
            File file1 = new File(scrPath);
            File file2 = new File(destPath);

            fis1 = new FileInputStream(file1);
            fis2 = new FileOutputStream(file2,false);

            byte[] bytes = new byte[1024];
            int len;
            while((len=fis1.read(bytes))!=-1){
                    fis2.write(bytes,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fis1!=null)
                    fis1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fis2!=null)
                    fis2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test1(){
        long start = System.currentTimeMillis();

        copyOfStream("F:\\JAVA学习视频\\第07天视频\\05_servlet生命周期介绍.avi","c:\\io\\视频复制1.avi");
        long end = System.currentTimeMillis();
        System.out.println("数据流复制时间为："+(end - start)+"ms");//756ms
    }
}
