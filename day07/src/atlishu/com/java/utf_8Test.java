package atlishu.com.java;

import java.io.UnsupportedEncodingException;

/**
 * @author lishustart
 * @create 2021-04-21-14:33
 */
public class utf_8Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "{\"datastreams\":[{\"id\":\"led\", \"datapoints\":[{\"value\": 30.2}]}]}";
        System.out.println(str.length()/256);
        System.out.println(str.length()%256);
    }
}
