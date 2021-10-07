package com.liu.web;

import com.google.gson.Gson;
import com.liu.pojo.User;
import com.liu.service.impl.UserServiceImpl;
import com.liu.utils.WEBUtils;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author liuliang
 * @create2021-08-2021/8/29-19:54
 * @email 2640336916@qq.com
 * @explain
 */
public class UserServlet extends BaseServlet {
    private UserServiceImpl userService = new UserServiceImpl();
    protected void AjaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        UserServiceImpl userService = new UserServiceImpl();
        boolean isExists = userService.existsUsername(userName);
        Map<String,Boolean> resultMap = new HashMap<>();
        resultMap.put("isExists",isExists);
        Gson gson = new Gson();
        String resultMapToJson = gson.toJson(resultMap);
        resp.getWriter().write(resultMapToJson);



    }

    public void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("注册");

        Map<String, String[]> parameterMap = req.getParameterMap();

        User user = WEBUtils.copyParamToBean(new User(),parameterMap);
        req.setAttribute("username",user.getUsername());
        req.setAttribute("password",user.getPassword());
        req.setAttribute("email",user.getEmail());
        String kaptcha_session_key = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        String code = req.getParameter("code");
        req.setAttribute("code",code);

        //判断验证码是否正确
        if(kaptcha_session_key != null && !kaptcha_session_key.equalsIgnoreCase(code)){
            System.out.println("验证码" + code + "错误");
            req.setAttribute("msg","验证码错误！");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }else {

            //判断用户名是否可用

            if(userService.existsUsername(user.getUsername())){
                System.out.println("用户名" + user.getUsername() + "存在");
                req.setAttribute("msg","该用户名已被占用！");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else {

                //添加用户
                userService.registUser(user);
                System.out.println("用户" + user.getUsername() + "添加成功！");
                req.setAttribute("msg","注册成功！！");
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }

        }


    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("登录");


        //获取数据
        Map<String, String[]> parameterMap = req.getParameterMap();

        User userTest = WEBUtils.copyParamToBean(new User(),parameterMap);


        //判断
        User loginUser = userService.login(userTest);
        if(loginUser != null){
            System.out.println("登录成功！");
            req.getSession().setAttribute("user",loginUser);
        req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }else {
            System.out.println("登录失败！");
            req.setAttribute("msg","用户名或密码错误！");
            req.setAttribute("username",userTest.getUsername());
            req.setAttribute("password",userTest.getPassword());
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
    }

    public void loginOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }
}
