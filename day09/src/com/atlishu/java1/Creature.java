package com.atlishu.java1;

import java.io.Serializable;

/**
 * @author lishustart
 * @create 2021-04-29-16:43
 */
public class Creature<T> implements Serializable {
    private char gender;
    public double weight;

    private void breath(){
        System.out.println("breath air");
    }

    private void eat(){
        System.out.println("eat food");
    }
}
