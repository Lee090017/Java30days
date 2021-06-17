package com.atlishu.java1;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author lishustart
 * @create 2021-03-11-17:38
 *
 * 说明：Java比较器实现Java对象的比较（多个对象进行排序）
 *
 * 使用两个接口：Comparalbe和Comparator
 * 
 */
public class CompareTest {
    @Test
    /*
        Comparable接口的使用举例
        1、像String、包装类实现了Comparable接口，重写了compareTo()方法，给出了比较两个对象大小
        2、像String、包装类重写了compareTo()方法后，进行了从小到大的排列
        3、重写compareTo(obj)的规则
            如果当前对象this大于形参对象obj，则返回正整数
            如果当前对象this小于形参对象obj，则返回负整数
            如果当前对象this等于形参对象obj，则返回0
        4、对于自定义类来说，如果需要排序，我们可以让自定义类实现comparable接口，重写compareTo()
           在compareTo()里重写排序的方法
     */
    public void test1(){
        String[] arr = new String[]{"EE","DD","CC","AA","BB"};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

    }

    /*
        自然排序的测试
     */
    @Test
    public void test2(){
        Goods[] goods = new Goods[5];
        goods[0] = new Goods("lenovoMouse",34);
        goods[1] = new Goods("dellMouse",43);
        goods[2] = new Goods("xioaMiMouse",12);
        goods[3] = new Goods("huaweiMouse",65);
        goods[4] = new Goods("hpMouse",12);

        Arrays.sort(goods);
        System.out.println(Arrays.toString(goods));

    }

    /*
        comparator的使用：定制排序
        1.compare(Object o1,Object o2)方法，比较o1和o2的大小：
            如果方法返回正整数，则表示o1大于o2
            如果返回0，表示相等
            如果返回负整数，表示o1小于o2
     */
    @Test
    public void test3(){
        String[] arr = new String[]{"EE","DD","CC","AA","BB"};
        //重大到小排列
        Arrays.sort(arr, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof String && o2 instanceof String){
                    String s1 = (String) o1;
                    String s2 = (String) o2;
                    return -s1.compareTo(s2);
                }

                throw new RuntimeException("输入的数据类型不一致");
            }
        });

        System.out.println(Arrays.toString(arr));
    }

    //练习，按照产品名称从低到高，再按照价格从高到低排序
    @Test
    public void test4(){
        Goods[] goods = new Goods[6];
        goods[0] = new Goods("lenovoMouse",34);
        goods[1] = new Goods("dellMouse",43);
        goods[2] = new Goods("xioaMiMouse",12);
        goods[3] = new Goods("xioaMiMouse",60);
        goods[4] = new Goods("huaweiMouse",65);
        goods[5] = new Goods("hpMouse",12);

        Arrays.sort(goods, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Goods && o2 instanceof Goods){
                    Goods g1 = (Goods) o1;
                    Goods g2 = (Goods) o2;

                    if(g1.getName().equals(g2.getName())){//如果两个名称相同
                        return -Double.compare(g1.getPrice(),g2.getPrice());
                    }else{
                        return g1.getName().compareTo(g2.getName());
                    }
                }
                throw new RuntimeException("输入的数据类型不一致");
            }
        });

        System.out.println(Arrays.toString(goods));
    }

}
