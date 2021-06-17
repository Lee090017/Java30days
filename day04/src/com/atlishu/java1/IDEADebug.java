package com.atlishu.java1;

import org.junit.Test;

/**
 * @author lishustart
 * @create 2021-03-10-21:57
 */
public class IDEADebug {

    @Test
    public void testStringBuffer(){
        String str = null;
        StringBuffer sb = new StringBuffer();
        sb.append(str);//

        System.out.println(sb.length());//4

        System.out.println(sb);//"null"

        StringBuffer sb1 = new StringBuffer(str);//抛异常NullPointException
        System.out.println(sb1);
    }
}
