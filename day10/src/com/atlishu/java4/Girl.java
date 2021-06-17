package com.atlishu.java4;

/**
 * @author lishustart
 * @create 2021-05-11-15:27
 */
public class Girl {
    private Boy boy;

    @Override
    public String toString() {
        return "Girl{" +
                "boy=" + boy +
                '}';
    }

    public Girl() {
    }

    public Boy getBoy() {
        return boy;
    }

    public Girl(Boy boy) {
        this.boy = boy;
    }

    public void setBoy(Boy boy) {
        this.boy = boy;
    }
}
