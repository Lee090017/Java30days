package com.atlishu.java;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author lishustart
 * @create 2021-03-11-22:12
 *
 * BigInteger可以表示不可变的任意精度的整数，想表示多少位都可以。
 *
 * BigDecimal在商业计算中，要求数字的精度比较高，任意精度的有符号十进制定数
 */
public class BigIntegerAndBigDecimalTest {
    @Test
    public void test1(){
        BigInteger bi = new BigInteger("2315165661615656189498461519896515198991");
        BigDecimal bd = new BigDecimal("123.461");
        BigDecimal bd2 = new BigDecimal("11");
        System.out.println(bi);
        //支持任意精度的小数位
        System.out.println(bd.divide(bd2,25,BigDecimal.ROUND_HALF_UP));
    }
}
