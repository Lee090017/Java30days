package atlishu.com.java;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

/**
 * @author lishustart
 * @create 2021-04-02-21:21
 *
 * File类的使用
 *
 * 1.File类的对象代表一个文件或者一个文件目录（俗称，文件夹）
 * 2.File类在java.io.File
 */
public class FileTest {
    /*
        1.创建File类的实例

        2.路径
        相对路径：相较于某一路径下
        绝对路径：包含盘符在内的文件或者文件目录的路径

        3.路径分隔符：“/”  “\\”  “+File.separator+”
     */
    @Test
    public void test1(){
        //构造器1
        File file1 = new File("hello.txt");//相对路径
        //三种写法
        File file2 = new File("C:\\Program Files\\Java\\IDEA_project\\day07\\hello.txt");//绝对对路径
        File file3 = new File("C:"+File.separator+
                "Program Files"+File.separator+
                "Java"+File.separator+
                "IDEA_project"+File.separator+
                "day07");//最通用
        File file4 = new File("C:/Program Files/Java/IDEA_project/day07/hello.txt");//绝对对路径

        System.out.println(file1);
        System.out.println(file2);

        //构造器2
        File file5 = new File("C:\\Program Files\\Java\\IDEA_project\\day07","JavaSenior");//创建文件目录
        System.out.println(file5);

        //构造器3
        File file6 = new File(file5,"hi.txt");
        System.out.println(file6);
    }

    /*

     */
    @Test
    public void test2(){
        File file1 = new File("hello.txt");
        File file2 = new File("c:\\io\\hi.txt");

        //内存层面的调用，不需要硬盘一定要有文件
        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.getPath());
        System.out.println(file1.getName());
        System.out.println(file1.getParent());
        System.out.println(file1.length());
        System.out.println(new Date(file1.lastModified()));

        System.out.println("**********");
        System.out.println(file2.getAbsolutePath());
        System.out.println(file2.getPath());
        System.out.println(file2.getName());
        System.out.println(file2.getParent());
        System.out.println(file2.length());
        System.out.println(file2.lastModified());

        //如下方法适用于文件目录
        File file3 = new File("C:\\Program Files\\Java\\IDEA_project");

        String[] list = file3.list();//返回文件目录下的文件名字
        System.out.println(Arrays.toString(list));

        File[] files = file3.listFiles();
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i]);//返回文件目录下的文件名字(更加完整，有路径名)
        }

        //重命名
        //public boolean renameTo(File dest)：把文件重命名为指定的文件路径
        //比如file1.renameTo(file2)为例：
        File file4 = new File("hello.txt");//要求存在
        File file5 = new File("c:\\io\\hi.txt");//要求不存在

        boolean ren = file4.renameTo(file5);
        System.out.println(ren);

    }

    @Test
    public void test3(){
        File file1 = new File("c:\\io\\hi.txt");
        System.out.println(file1.isDirectory());//文件目录还是文件
        System.out.println(file1.isFile());
        System.out.println(file1.exists());//是否存在
        System.out.println(file1.canRead());//可读可写
        System.out.println(file1.canWrite());
        System.out.println(file1.isHidden());//是否隐藏
    }

    @Test
    public void test4() throws IOException {
        /*
            创建硬盘中对应的文件或文件目录
         */
        File file = new File("hello.txt");
        if(!file.exists()){
            file.createNewFile();
            System.out.println("创建成功");
        }else{
            file.delete();
            System.out.println("删除成功");
        }
        //文件目录创建
        File file1 = new File("c:\\io\\11\\o1");
        //假如上层文件夹存在mkdir与mkdirs相同
        System.out.println(file1.mkdir());//false
        System.out.println(file1.mkdirs());//true,将连通上层目录一并创建
    }
}
