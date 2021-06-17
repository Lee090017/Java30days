package atlishu.com.java1;

import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author lishustart
 * @create 2021-04-22-11:22
 *
 * 缓冲流练习题
 */
public class BufferExercise {

    //实现图片的加密操作
    @Test
    public void test1(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File file1 = new File("c:\\io\\故障电弧探测器1.jpg");
            File file2 = new File("c:\\io\\故障电弧探测器1(加密).jpg");

            fis = new FileInputStream(file1);
            fos = new FileOutputStream(file2,false);

            int len;
            while((len=fis.read())!=-1){
                fos.write(len^3);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fis!=null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fos!=null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test2() throws IOException {
        File file = new File("hello.txt");
        FileReader fileReader = new FileReader(file);
        int l;
       while ((l=fileReader.read())!=-1){//read()返回的是对应字符的ASCII码值
           System.out.println(l);
       }
    }

    @Test
    /*
        统计每个字符出现的次数
     */
    public void test3(){
        BufferedReader br = null;
        HashMap<Character,Integer> hashMap = new HashMap<>();
        try {
            File file1 = new File("hello.txt");
            FileReader fr = new FileReader(file1);
            br = new BufferedReader(fr);

            char[] cbuf = new char[1024];
            int len;
            while((len=br.read(cbuf))!=-1){
                for (int i = 0; i < len; i++) {
                    if(hashMap.containsKey(cbuf[i])){
                        hashMap.put(cbuf[i],hashMap.get(cbuf[i])+1);
                    }else{
                        hashMap.put(cbuf[i],1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br!=null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedWriter bw = null;
        try {
            File file2 = new File("frequencyOfhello.txt");
            FileWriter fw = new FileWriter(file2);
            bw = new BufferedWriter(fw);
            Set<Map.Entry<Character, Integer>> entries = hashMap.entrySet();
            Iterator<Map.Entry<Character, Integer>> iterator = entries.iterator();
            while (iterator.hasNext()){
                bw.write(iterator.next().toString()+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bw!=null)
                    bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
