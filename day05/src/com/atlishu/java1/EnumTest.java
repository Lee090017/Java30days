package com.atlishu.java1;

/**
 * @author lishustart
 * @create 2021-03-12-15:03
 *
 * 一、枚举类的使用
 * 1.类的对象是有限个，确定的，我们称此类为枚举类
 * 2.当需要定义一组常量时，建议使用枚举类
 * 3.当类的对象只有一个时，则可以作为单例模式的一种实现方式
 *
 * 二、如何定义枚举类
 * 方式一：jdk5.0之前，自定义枚举类
 * 方式二：jdk5.0时可以使用enum关键字，定义枚举类
 *
 * 三、enum类的常见方法
 * toString()、values()、valueOf(String str)
 *
 * 四、使用enum定义的枚举类实现接口
 *     情况一：实现接口，在enum类中重写抽象方法（这种方式将使得所有不同对象执行相同的重写方法）
 *     情况二：让枚举类的对象分别实现接口中的抽象方法
 */
public class EnumTest {
    public static void main(String[] args) {
        //自定义枚举类
        Season spring = Season.SPRING;
        System.out.println(spring.toString());

        //使用enum关键字定义枚举类
        BasketballPlayer pg = BasketballPlayer.PG;
        System.out.println(pg.toString());//PG(对象名)

        System.out.println("**************");
        //values()，返回枚举类的数组
        BasketballPlayer[] basketballPlayers = BasketballPlayer.values();
        for (int i = 0; i <basketballPlayers.length ; i++) {
            System.out.println(basketballPlayers[i]);
        }

        //valueOf():返回枚举类中指定对象名的对象
        BasketballPlayer c = BasketballPlayer.valueOf("C");
//        BasketballPlayer c = BasketballPlayer.valueOf("C11");//没找到指定对象名，抛出异常IllegalArgumentException
        System.out.println(c);

        c.show();

    }
}

//自定义枚举类
class Season{
    //1.声明Season对象的属性
    private final String seasonName;
    private final String seasonDesc;

    //2.私有化类的构造器
    private Season(String seasonName,String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    //3.提供当前枚举类的多个对象
    public static final Season SPRING = new Season("春天","春乱花开");
    public static final Season SUMMER = new Season("夏天","烈日炎炎");
    public static final Season AUTUMN = new Season("秋天","秋高气爽");
    public static final Season WINTER = new Season("冬天","冰天雪地");

    //4.其他诉求：获取对象属性


    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    //5.提供toString方法

    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}' ;
    }
}

//用enum关键字定义枚举类
//定义的枚举类继承于java.lang.Enum类

enum BasketballPlayer implements Info{
    //1.提供当前枚举类的对象，多个对象之间用“，”隔开，最后一个对象用“；”结束
    PG("控球后卫","组织进攻"){
        //每个对象都重写一次show方法
        @Override
        public void show() {
            System.out.println("我是库里");
        }
    },
    SG("得分后卫","定点投手"){
        //每个对象都重写一次show方法
        @Override
        public void show() {
            System.out.println("我是哈登");
        }
    },
    SF("小前锋","单打突击"){
        //每个对象都重写一次show方法
        @Override
        public void show() {
            System.out.println("我是杜兰特");
        }
    },
    PF("大前锋","防守卡位"){
        //每个对象都重写一次show方法
        @Override
        public void show() {
            System.out.println("我是阿德托昆博");
        }
    },
    C("中锋","篮板强打"){
        //每个对象都重写一次show方法
        @Override
        public void show() {
            System.out.println("我是恩比德");
        }
    };
    //2.声明该类属性
    private final String name;
    private final String describe;
    //3.私有化构造器
    private BasketballPlayer(String name,String describe){
        this.name = name;
        this.describe = describe;
    }
    //4.其他诉求

    public String getName() {
        return name;
    }

    public String getDescribe() {
        return describe;
    }

//    @Override
//    //该方法所有对象都打印的是同一句话
//    public void show() {
//        System.out.println("这是一支篮球队");
//    }
}