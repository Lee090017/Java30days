package com.atlishu.java4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author lishustart
 * @create 2021-04-01-20:04
 *
 * 集合练习题
 */
public class Exercise {

    public static void main(String[] args) throws FileNotFoundException {
//        test1();
//        test2();
//        test3();
        test4();
    }
    /*
        1.请从键盘随机输入10个整数保存到List中，并按倒序、从大到小的顺序显示出来
     */
    public static void test1(){
        Scanner in = new Scanner(System.in);
        List list = new ArrayList();
        System.out.println("Please input 10 numbers:");
        for (int i = 0; i < 10; i++) {
            int num = in.nextInt();
            list.add(num);
        }
        Collections.sort(list);
        Collections.reverse(list);
        System.out.println(list);
    }

    /*
        2.请把学生名与考试分数录入到集合中，并按分数显示前三名成绩学员的名字。
        TreeSet（Student（name，score，id））;
     */
    public static void test2(){

        Set set = new TreeSet();
        set.add(new Student("Tom",90,"123123"));
        set.add(new Student("Jerry",87,"456456"));
        set.add(new Student("Jack",95,"789789"));
        set.add(new Student("Mary",100,"147147"));
        set.add(new Student("Bob",85,"258258"));

        int i = 0;
        Iterator iterator = set.iterator();
        while (iterator.hasNext() && i<3){
            Object obj = iterator.next();
            if(obj instanceof Student) {
                Student stu = (Student)obj;
                System.out.println(stu.getName());
            }
            i++;
        }
    }

    /*
        姓氏统计，统计一个文件文本中所有姓氏的数量，姓名以空格分离
        如：
        李 三
        张 三
        李 四
        王 五
     */
    public static void test3(){
        String[] name = new String[]{"李 三","张 三","李 四","王 五","赵 四","司马 光","张 伯伦","李 曙","王 以"};
        List list = new ArrayList();
        for (int i = 0; i < name.length; i++) {
            String m = name[i];
            //提取姓
            int index = m.indexOf(" ");
            String x = m.substring(0,index);
            list.add(x);
        }
        System.out.println(list);
        //将list变为set
        Set set = new HashSet();
        set.addAll(list);
//        System.out.println(set);
        Object[] array = set.toArray();
        for (int i = 0; i < array.length; i++) {
            int num = Collections.frequency(list,array[i]);
            array[i] = array[i] + ":" + num;
        }
        System.out.println(Arrays.toString(array));
    }

    /*
        计数java源文件的关键字
     */
    public static void test4() throws FileNotFoundException {
        List list = Arrays.asList("ded","key");//关键词
        HashSet hashSet = new HashSet();
        hashSet.addAll(list);
        System.out.println(hashSet);

        File file = new File("Test.java");
        Scanner scanner = new Scanner(file);
        int count = 0;
        while(scanner.hasNext()){
            String word = scanner.next();
//            System.out.println(word);
            if(hashSet.contains(word)){
                count++;
            }
        }
        System.out.println("关键词有"+count+"个");
    }
}

class Student implements Comparable{
    private String name;
    private int score;
    private String id;

    public Student(String name, int score, String id) {
        this.name = name;
        this.score = score;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return score == student.score &&
                Objects.equals(name, student.name) &&
                Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score, id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Student){
            Student stu = (Student)o;
            if(this.score > stu.score){
                return -1;
            }else if(this.score < stu.score){
                return 1;
            }else return 0;
        }
        throw new RuntimeException("...");
    }
}