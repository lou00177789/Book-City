package com.liu.dao;

import com.liu.pojo.User;

/**
 * @author liuliang
 * @create2021-08-2021/8/27-12:57
 * @email 2640336916@qq.com
 * @explain
 */
public interface UserDao {


    /**
     * 根据用户名查询用户信息
     *
     * @param name
     * @return null说明没有这个用户
     */
    public User queryUserByName(String name);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public int saveUser(User user);

    /**
     * 根据用户名和密码查询用户信息
     * @param name
     * @param password
     * @return null代表用户名或密码错误
     */
    public User queryUserByNameAndPassword(String name,String password);


}
