package com.atlishu.java1;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @author lishustart
 * @create 2021-03-14-15:16
 */
/*
    可重复注解
 */
@Inherited
@Target({TYPE,FIELD,METHOD,PARAMETER,CONSTRUCTOR,LOCAL_VARIABLE,TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)//注解的生命周期
public @interface MyAnnotations {
    MyAnnotation[] value();
}
