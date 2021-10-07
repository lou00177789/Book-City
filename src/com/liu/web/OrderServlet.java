package com.liu.web;

import com.liu.pojo.Cart;
import com.liu.pojo.Order;
import com.liu.pojo.OrderItem;
import com.liu.pojo.User;
import com.liu.service.impl.OrderServiceImpl;
import com.liu.utils.JDBCUtils;
import com.liu.utils.WEBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liuliang
 * @create2021-09-2021/9/6-13:00
 * @email 2640336916@qq.com
 * @explain
 */
public class OrderServlet extends BaseServlet {
    private OrderServiceImpl orderService = new OrderServiceImpl();

    protected void receiverOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        orderService.receiverOrder(req.getParameter("orderId"));
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        orderService.sendOrder(req.getParameter("orderId"));
        resp.sendRedirect(req.getHeader("Referer"));

    }

    protected void orderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        req.getSession().setAttribute("orderId",req.getParameter("orderId"));
        req.getSession().setAttribute("orderCreateTime",req.getParameter("orderCreateTime"));
        req.getSession().setAttribute("orderTotalPrice",req.getParameter("orderTotalPrice"));
        req.getSession().setAttribute("orderStatus",req.getParameter("orderStatus"));
        System.out.println(orderService.orderDetail(orderId));
        req.getSession().setAttribute("orderDetail", orderService.orderDetail(orderId));
        req.getRequestDispatcher("/pages/order/orderDetail.jsp").forward(req,resp);
    }

    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("allOrders",orderService.showAllOrders());
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);

    }

    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入用户查看订单页面！");
        //判断是否登录
        User user = (User)req.getSession().getAttribute("user");
        if (user == null){
            //去登录吧那就
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
            //得到id
            List<Order> myOrders = orderService.showMyOrders(user.getId());

            req.getSession().setAttribute("userOrders",myOrders);
            req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
        }


    }

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        User user = (User)req.getSession().getAttribute("user");

        if (cart == null||cart.getTotalCount() == 0){
            req.getRequestDispatcher(req.getHeader("Referer")).forward(req,resp);
        }else {
            if (user ==null){
                req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            }else {
                String orderId = null;

                orderId = orderService.createOrder(cart, user.getId());
                JDBCUtils.commitAndClose();

                req.getSession().removeAttribute("lastAdd");
                req.getSession().setAttribute("orderId",orderId);
                resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
            }

        }
    }
}
