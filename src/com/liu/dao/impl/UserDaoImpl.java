package com.liu.dao.impl;

import com.liu.dao.UserDao;
import com.liu.pojo.User;

/**
 * @author liuliang
 * @create2021-08-2021/8/27-13:06
 * @email 2640336916@qq.com
 * @explain
 */
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public User queryUserByName(String username) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username = ?";
        return(queryForOne(User.class,sql,username));
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(`username`,`password`,`email`)values(?,?,?)";
        return (update(sql,user.getUsername(),user.getPassword(),user.getEmail()));
    }

    @Override
    public User queryUserByNameAndPassword(String username, String password) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username = ? and password = ?";
        return(queryForOne(User.class,sql,username,password));
    }
}
