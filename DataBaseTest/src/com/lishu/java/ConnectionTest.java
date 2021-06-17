package com.lishu.java;

import com.lishu.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author lishustart
 * @create 2021-06-17-17:25
 */
public class ConnectionTest {

    @Test
    public void testConnection() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        System.out.println(connection);
    }
}
