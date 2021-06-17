package com.lishu.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author lishustart
 * @create 2021-06-17-17:26
 */
public class JDBCUtils {

    public static Connection getConnection() throws Exception {
        //1.读取配置文件的基本信息
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

        Properties properties = new Properties();
        properties.load(is);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");

        //2.加载驱动
        Class.forName(driverClass);

        //3.获取连接
        Connection connection = DriverManager.getConnection(url, user, password);

        return connection;
    }

    /*
     * 关闭资源的操作
     *
     */
    public static void closeResource(Connection con, Statement ps) {
        try {
            if(ps != null)
                ps.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            if(con != null)
                con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*
     * 关闭资源的操作
     *
     */
    public static void closeResource(Connection con, Statement ps, ResultSet rs) {
        try {
            if(ps != null)
                ps.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            if(con != null)
                con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            if(rs != null)
                rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
