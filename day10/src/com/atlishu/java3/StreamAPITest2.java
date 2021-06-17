package com.atlishu.java3;

import com.atlishu.java2.Employee;
import com.atlishu.java2.EmployeeData;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lishustart
 * @create 2021-05-07-21:42
 *
 * Stream的终止操作
 */
public class StreamAPITest2 {
    //匹配与查找
    @Test
    public void test1(){
        List<Employee> list = EmployeeData.getEmployees();
        //allMatch()检测匹配所有元素
        //练习：是否所有员工年龄都大于18
        boolean a = list.stream().allMatch(e -> e.getAge() > 18);
        System.out.println(a);

        //anyMatch()检测是否至少匹配一个元素
        //练习：是否存在员工工资大于10000
        boolean b = list.stream().anyMatch(e -> e.getSalary() > 10000);
        System.out.println(b);

        //noneMatch()检测是否没有匹配元素
        //是否存在员工姓“雷”
        boolean c = list.stream().noneMatch(e -> e.getName().startsWith("雷"));
        System.out.println(c);

        //findFirst()返回第一个元素
        Optional<Employee> first = list.stream().findFirst();
        System.out.println(first);

        //findAny()返回任意元素
        Optional<Employee> any = list.parallelStream().findAny();
        System.out.println(any);

        //count()返回元素总个数
        long count = list.stream().count();
        System.out.println(count);

        //max(Comparator c)返回最大值
        Optional<Employee> max = list.stream().max((c1, c2) -> Double.compare(c1.getSalary(), c2.getSalary()));
        System.out.println(max);

        //min(Comparator c)返回最小值
        Optional<Employee> min = list.stream().min((c1, c2) -> Double.compare(c1.getSalary(), c2.getSalary()));
        System.out.println(min);

        //foreach(Consumer c)内部迭代
        list.stream().forEach(System.out::println);
    }

    //归约
    @Test
    public void test2(){
        //reduce()将流中的元素反复结合起来，得到一个值返回T
        //计算1-10自然数的和
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream().reduce(0, Integer::sum);//identity为初始值
        System.out.println(sum);

        //工资求和
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<Double> doubleStream = employees.stream().map(Employee::getSalary);
//        Optional<Double> reduce = doubleStream.reduce(Double::sum);
        Optional<Double> reduce = doubleStream.reduce((s1, s2) -> s1 + s2);//要求输入的值与返回的值数据类型一致
        System.out.println(reduce);

    }

    //收集
    @Test
    public void test3(){
        //collect(Collector c)收集
        //查找工资大于6000的员工，结果返回一个list或者set
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<Employee> employeeStream = employees.stream().filter(e -> e.getSalary() > 6000);
//        List<Employee> collect = employeeStream.collect(Collectors.toList());
//        collect.stream().forEach(System.out::println);
        System.out.println();

        //返回一个collection
        HashSet<Employee> collect1 = employeeStream.collect(Collectors.toCollection(HashSet::new));
        collect1.stream().forEach(System.out::println);


    }
}
