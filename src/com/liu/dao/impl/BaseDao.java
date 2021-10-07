package com.liu.dao.impl;

import com.liu.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author liuliang
 * @create2021-08-2021/8/27-11:13
 * @email 2640336916@qq.com
 * @explain
 */
public class BaseDao {

    private QueryRunner queryRunner = new QueryRunner();

    /**
     * insert,delete,insert操作
     * @return -1代表失败了
     */
    public int update(String sql,Object ...args){

        Connection conn = null;

        try {
            conn = JDBCUtils.getConnection();
            return (queryRunner.update(conn,sql,args));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);

        }

    }


    /**
     * 单个值的查询操作
     * @return
     */
    public <T> T queryForOne(Class<T> type,String sql,Object ...args){

        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();

            ResultSetHandler<T> beanHandler =  new BeanHandler<T>(type);
            return (queryRunner.query(conn,sql,beanHandler,args));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);

        }

    }



    /**
     * 多个值的查询操作
     * @return
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object ...args){

        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();

            BeanListHandler<T> beanListHandler =  new BeanListHandler<T>(type);
            return (queryRunner.query(conn,sql,beanListHandler,args));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);

        }

    }

    /**
     * 返回一行一列的数据
     * @param sql
     * @param args
     * @return
     */
    public Object queryForSingleValue(String sql, Object ...args){
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();

            return (queryRunner.query(conn,sql,new ScalarHandler(),args));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }

    }




}
