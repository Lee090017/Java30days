package atlishu.com.java;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author lishustart
 * @create 2021-04-20-20:37
 */
public class FileExercise {

    static int length;

    @Test
    public void test1() throws IOException {
        File file = new File("C:\\io\\hi.txt");
        //创建与file相同路径下名为haha.txt的文件
        String str = file.getParent();
        System.out.println(str);
        File file1 = new File(str,"haha.txt");
        if(!file1.exists()){
            file1.createNewFile();
            System.out.println("创建成功");
        }
    }

    @Test
    public void test2(){
        //判断指定目录下是否有后缀为.jpg的文件，如果有，就输出文件的名称
        File file = new File("c:\\io");
        String[] list = file.list();
        System.out.println(Arrays.toString(list));
        for (String s:list) {
            if(s.length()>4) {
                if (s.substring(s.length() - 4, s.length()).equals(".jpg")) {
                    System.out.println(s);
                }
            }
        }
    }

    @Test
    public void test3(){
        //遍历指定目录下的所有文件名称，包括子文件目录下的文件
        File file = new File("c:\\io");
        dfsShow(file);
        //计算指定目录占用的空间大小
        System.out.println("计算结果："+length);
        System.out.println("实际大小："+10907);
//        File file1 = new File("c:\\io\\hi.txt");
//        System.out.println(file1.);
    }

    //遍历指定文件夹下所有文件
    public void dfsShow(File file){
        if(file.isFile()){
            return;
        }
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if(files[i].isFile()) {
                System.out.println(files[i].getName());
            }
            length += files[i].length();
            dfsShow(files[i]);
        }
    }

    @Test
    public void test4(){
        //删除指定文件目录及其下的所有文件
        File file = new File("c:\\io\\o3");
        if(file.isDirectory()){
            System.out.println(file.delete());//false
        }
//        File file1 = new File("c:\\io\\o2");
//        File[] f = file1.listFiles();
//        System.out.println(f.length);
//        dfsDelete(file);
        try {
            while (file.exists()){
                dfsDelete(file);
            }
        } catch (Exception e) {
            System.out.println("空指针异常");
        }
    }

    //删除指定文件夹下所有文件
    public void dfsDelete(File file){
        if(file.isFile()){
            file.delete();
            return;
        }
        File[] files = file.listFiles();
        if(files.length==0){//该文件夹为空
            file.delete();
            return;
        }
        for (int i = 0; i < files.length; i++) {
            dfsDelete(files[i]);
        }
    }
}
