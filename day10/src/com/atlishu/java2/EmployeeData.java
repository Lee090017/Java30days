package com.atlishu.java2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lishustart
 * @create 2021-05-07-17:06
 *
 * 提供用于测试的数据
 */
public class EmployeeData {
    public static List<Employee> getEmployees(){
        ArrayList<Employee> list = new ArrayList<>();

        list.add(new Employee(1001,"马化腾",34,6000.38));
        list.add(new Employee(1002,"马云",50,2625.33));
        list.add(new Employee(1003,"刘强东",33,3000.38));
        list.add(new Employee(1004,"雷军",34,6663.19));
        list.add(new Employee(1005,"王健林",64,5555.55));
        list.add(new Employee(1006,"李彦宏",44,9500.36));
        list.add(new Employee(1007,"董明珠",55,4333.45));
        list.add(new Employee(1008,"任正非",70,2500.98));

        return list;
    }
}
