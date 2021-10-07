package com.liu.service.impl;

import com.liu.dao.UserDao;
import com.liu.dao.impl.UserDaoImpl;
import com.liu.pojo.User;
import com.liu.service.UserService;

/**
 * @author liuliang
 * @create2021-08-2021/8/27-13:50
 * @email 2640336916@qq.com
 * @explain
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);

    }

    @Override
    public User login(User user) {
        return userDao.queryUserByNameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if (userDao.queryUserByName(username) != null){
            return true;
        }
        return false;
    }
}
