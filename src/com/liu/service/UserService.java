package com.liu.service;

import com.liu.pojo.User;

/**
 * @author liuliang
 * @create2021-08-2021/8/27-13:42
 * @email 2640336916@qq.com
 * @explain
 */
public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);


    /**
     * 登录
     * @param user
     * 返回null登录失败，返回对象说明登录成功
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * 返回true说明已存在该用户
     */
    public boolean existsUsername(String username);
}
