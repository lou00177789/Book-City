package com.liu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author liuliang
 * @create2021-08-2021/8/26-21:59
 * @email 2640336916@qq.com
 * @explain
 */
public class JDBCUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();


    static{
        try {
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");

//            FileReader is = new FileReader("jdbc.properties");//这里不晓得为啥不行
            Properties properties = new Properties();
            properties.load(is);

            dataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取数据库连接池中的连接
     * 返回null代表获取连接失败
     * @return
     */
    public static Connection getConnection() {
        Connection conn = threadLocal.get();

        if(conn == null){
            try {
                conn = dataSource.getConnection();
                threadLocal.set(conn);
                conn.setAutoCommit(false);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

        return conn;
    }

    public static void commitAndClose(){
        Connection conn = threadLocal.get();
        if (conn != null){
            try {
                conn.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally{
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        threadLocal.remove();

    }
    public static void rollbackAndClose(){
        Connection conn = threadLocal.get();
        if (conn != null){
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally{
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        threadLocal.remove();

    }

/*    *//**
     * 放回数据库连接
     * @return
     *//*
    public static void close(Connection conn){

        if(conn != null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }*/


}
