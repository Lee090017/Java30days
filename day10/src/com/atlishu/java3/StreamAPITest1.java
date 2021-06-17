package com.atlishu.java3;

import com.atlishu.java2.Employee;
import com.atlishu.java2.EmployeeData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author lishustart
 * @create 2021-05-07-19:10
 *
 * 中间操作
 *
 */
public class StreamAPITest1 {

    //筛选与切片
    @Test
    public void test1(){

        List<Employee> employees = EmployeeData.getEmployees();
        Stream<Employee> stream = employees.stream();

        //过滤：filter(Predicate p)--接收Lambda，从流中排除某些元素
        stream.filter(e -> e.getAge()>50).forEach(System.out::println);//年龄大于50
        //一旦执行终止操作将关闭stream
        System.out.println();

        //截断流：limit(n)使元素不超过指定数量
        employees.stream().limit(3).forEach(System.out::println);//只输出前3条数据
        System.out.println();

        //跳过元素：skip(n)返回一个扔掉了前n个元素的流，若流中元素不足n个，则返回空流
        employees.stream().skip(3).forEach(System.out::println);//跳过前3条数据
        System.out.println();

        //筛选：distinct()通过流所生成元素的hashCode()和equals()去除重复元素
        employees.add(new Employee(1003,"刘强东",33,3000.38));
        employees.add(new Employee(1003,"刘强东",33,3000.38));
        employees.add(new Employee(1009,"蔡崇信",66,6628.14));

        employees.stream().distinct().forEach(System.out::println);//删除重复数据

    }

    //映射
    @Test
    public void test2(){
        /*
            map(Function f)接收一个函数作为参数，将元素转换成其他形式或提取信息，
            该函数会被应用在每个元素上，并将其映射成一个新的元素
         */
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        list.stream().map(str->str.toUpperCase()).forEach(System.out::println);//将原集合中所有元素变为大写

        //练习1：获取员工名字长度大于3的员工的姓名
        List<Employee> list1 = EmployeeData.getEmployees();
        Stream<String> nameStream = list1.stream().map(Employee::getName);
        nameStream.filter(e -> e.length()>2).forEach(System.out::println);
        System.out.println();

        //练习2：遍历集合每个元素的每个字符
        Stream<Stream<Character>> streamStream = list.stream().map(StreamAPITest1::fromStringToStream);
        streamStream.forEach(s->{
            s.forEach(System.out::println);
        });//for里嵌套for
        /*
            flatMap(Function f)接收一个函数作为参数，将流中的每个值都换成另一个流，
            然后把所有流连成一个流
            比较flatMap与Map的区别。
         */
        Stream<Character> characterStream = list.stream().flatMap(StreamAPITest1::fromStringToStream);
        characterStream.forEach(System.out::println);


    }

    public static Stream<Character> fromStringToStream(String str){
        ArrayList<Character> list = new ArrayList<>();
        for (Character c: str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    //排序
    @Test
    public void test3(){
        //sorted()自然排序
        List<Integer> list = Arrays.asList(12, 89, 45, 36, 47, 25, 48, 69);
        Stream<Integer> sortedList = list.stream().sorted();
        sortedList.forEach(System.out::println);
        System.out.println();

        //sorted(Comparator com)--定制排序
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<Employee> sortedStream = employees.stream().sorted((e1, e2) -> Integer.compare(e1.getAge(),e2.getAge()));
        sortedStream.forEach(System.out::println);//按年龄从小到大排序
    }
}
