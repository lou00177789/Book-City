package com.liu.test;

import com.liu.dao.impl.UserDaoImpl;
import com.liu.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liuliang
 * @create2021-08-2021/8/27-13:24
 * @email 2640336916@qq.com
 * @explain
 */
public class UserDaoTest {

    @Test
    public void queryUserByName() {
        User admin = new UserDaoImpl().queryUserByName("admn");

        if(admin != null){
            System.out.println("用户名已存在！");
        }else {
            System.out.println("用户名可用！");
        }
        System.out.println(admin);

        admin = new UserDaoImpl().queryUserByName("admin");

        if(admin != null){
            System.out.println("用户名已存在！");
        }else {
            System.out.println("用户名可用！");
        }
        System.out.println(admin);
    }

    @Test
    public void saveUser() {
        User admin = new User(null, "liang", "123", "admin@qq.com");
        System.out.println(new UserDaoImpl().saveUser(admin));
    }

    @Test
    public void queryUserByNameAndPassword() {
        User user = new UserDaoImpl().queryUserByNameAndPassword("admin", "adin");
        System.out.println(user);

        user = new UserDaoImpl().queryUserByNameAndPassword("admin", "admin");
        System.out.println(user);

    }
}