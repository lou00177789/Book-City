package com.liu.test;

import com.liu.pojo.User;
import com.liu.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liuliang
 * @create2021-08-2021/8/27-14:01
 * @email 2640336916@qq.com
 * @explain
 */
public class UserServiceTest {

    @Test
    public void registUser() {
        User user = new User(null,"ad3min","pass","admins@as.com");
        new UserServiceImpl().registUser(user);

    }

    @Test
    public void login() {
        User user = new User(null,"ad4min","pass","admins@as.com");
        User login = new UserServiceImpl().login(user);
        System.out.println(login);
    }

    @Test
    public void existsUsername() {
        boolean admin = new UserServiceImpl().existsUsername("adomin");
        System.out.println(admin);
    }
}