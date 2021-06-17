package atlishu.com.java;

import org.junit.Test;

import java.util.*;
/**
 * @author lishustart
 * @create 2021-04-02-9:40
 *
 * 泛型的使用
 * 1.jdk 5.0新增的特性
 *
 *
 * 2.在集合中使用泛型：
 * 总结：
 * ① 集合接口或者集合类在jdk5.0后都修改为带泛型的结构
 * ② 在实例化集合时，可以指明具体的泛型类型
 * ③ 指明完以后，在集合类或者接口中凡是定义类或者接口时，内部结构使用到的类的泛型的位置都指定为实例化的泛型位置
 *      比如：add(E e)--->实例化以后：add(Integer e)
 * ④ 注意点：泛型的类型必须是类，不能是基本数据类型，需要用到基本数据类型的位置
 * ⑤ 如果实例化时，没有指明泛型的类型，默认类型为java.lang.Object类型
 *
 * 3.如何自定义泛型结构：泛型类、泛型接口；泛型方法
 */
public class GenericTest {
    @Test
    public void test1(){
        ArrayList list = new ArrayList();
        //需求：存放学生的成绩
        list.add(78);
        list.add(66);
        list.add(80);
        list.add(88);
        //问题一：类型不安全
//        list.add("Tom");

        for (Object obj : list){
            //问题二：强制转换时格式错误
            int studentscore = (Integer)obj;
            System.out.println(studentscore);
        }
    }

    //在集合中使用泛型的情况,以ArrayList为例
    @Test
    public void test2() {
//        ArrayList<Integer> list = new ArrayList<Integer>();//不能是基本数据类型，只能是包装类
        //jdk7的新特性
        ArrayList<Integer> list = new ArrayList<>();
        list.add(78);
        list.add(66);
        list.add(80);
        list.add(88);
        //编译时就会进行数据检测，保证数据的安全
//        list.add("Tom");

        for (Integer score:list) {
            //避免了强转操作
            int stuScore = score;
            System.out.println(stuScore);
        }

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

    @Test
    public void test3(){
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("lishu",25);
        map.put("yanming",27);
        map.put("tangyige",26);
        map.put("pengdaoming",24);

        //Entry为Map的内部类
        Set<Map.Entry<String, Integer>> entries = map.entrySet();//泛型的嵌套

        Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Integer> next = iterator.next();
            String key = next.getKey();
            Integer value = next.getValue();
            System.out.println(key+":"+value);

        }
    }

    @Test
    public void test4(){
        //测试自定义泛型类
        //如果定义了泛型类，实例化没有指明泛型，则认为此泛型类为Object类型
        Order<String> order = new Order<>();
        order.setOrderT("自定义泛型");
//        order.setOrderT(1111);

        //子类在继承带泛型的父类时，指明了泛型类型，则实例化子类对象时，不用再申明泛型
        SubOrder1 sub1= new SubOrder1();//不是泛型类
        sub1.setOrderT(12);

        SubOrder2<Float> sub2 = new SubOrder2<>();//仍为泛型类
        SubOrder2<Double> sub3 = new SubOrder2<>();
//        sub2 = sub3;//泛型不同的引用不能相互赋值

        sub2.setOrderT(12.22F);

        //测试泛型方法
        //泛型方法在调用时，指明泛型参数的类型
        List<String> strings = sub1.copyFromArrayToList(new String[]{"abc", "def"});

        System.out.println(strings);

    }
}
