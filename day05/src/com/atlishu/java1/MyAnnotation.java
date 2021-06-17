package com.atlishu.java1;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * @author lishustart
 * @create 2021-03-12-17:33
 *
 * 自定义注解
 */
@Inherited//可继承性
@Target({TYPE,FIELD,METHOD,PARAMETER,CONSTRUCTOR,LOCAL_VARIABLE,TYPE_PARAMETER,ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)//注解的生命周期
@Repeatable(MyAnnotations.class)//可重复定义
public @interface MyAnnotation {

    String value() default "hello";//使用默认值

}
