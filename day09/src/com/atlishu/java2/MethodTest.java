package com.atlishu.java2;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author lishustart
 * @create 2021-04-29-17:19
 *
 * 获取运行时类的方法结构
 */
public class MethodTest {
    @Test
    public void test1() throws ClassNotFoundException {
        Class clazz = Class.forName("com.atlishu.java1.Person");

        //getMethods()：获取当前运行时类及其父类中声明为public访问权限的方法
        Method[] methods = clazz.getMethods();
        for (Method m:methods) {
            System.out.println(m);
        }
        System.out.println();
        //getDeclaredMethods()：获取当前运行时类中声明的所有方法
        Method[] declaredMethods= clazz.getDeclaredMethods();
        for (Method m:declaredMethods) {
            System.out.println(m);
        }
        System.out.println();
    }

    /*
    @Xxxx
    权限修饰符 返回值类型 方法名(参数类型1 形参名1，.....) throws XxxException{}
     */
    @Test
    public void test2() throws ClassNotFoundException {

        Class clazz = Class.forName("com.atlishu.java1.Person");
        Method[] declaredMethods= clazz.getDeclaredMethods();
        for (Method m:declaredMethods) {
            //获取方法声明的注解
            Annotation[] annotations = m.getAnnotations();
            for (Annotation anos:annotations) {
                System.out.print(anos+"\t");
            }
            //权限修饰符
            System.out.print(Modifier.toString(m.getModifiers())+"\t");
            //返回值类型
            System.out.print(m.getReturnType().getName()+"\t");
            //方法名
            System.out.print(m.getName());
            System.out.print("(");
            //形参列表
            Class[] parameterTypes = m.getParameterTypes();
            if(!(parameterTypes == null && parameterTypes.length==0)){
                for (int i = 0; i < parameterTypes.length; i++) {
                    if(i==parameterTypes.length-1){
                        System.out.print(parameterTypes[i].getName()+" args_"+i);
                        break;
                    }
                    System.out.print(parameterTypes[i].getName()+" args_"+i+",");
                }
            }
            System.out.print(")");

            //抛出异常
            Class[] exceptionTypes = m.getExceptionTypes();
            if(!(exceptionTypes == null || exceptionTypes.length==0)){
                System.out.print(" throws ");
                for (int i = 0; i < exceptionTypes.length; i++) {
                    if(i==exceptionTypes.length-1){
                        System.out.print(exceptionTypes[i].getName());
                        break;
                    }
                    System.out.print(exceptionTypes[i].getName()+",");
                }
            }

            System.out.println();
        }
    }
}
