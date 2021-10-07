package com.liu.service;

import com.liu.pojo.Cart;
import com.liu.pojo.Order;

import com.liu.pojo.OrderItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuliang
 * @create2021-09-2021/9/6-12:19
 * @email 2640336916@qq.com
 * @explain
 */
public interface OrderService {
    public String createOrder(Cart cart,Integer userId);

    public List<Order> showMyOrders(Integer id);

    public List<Order> showAllOrders();

    public List<OrderItem> orderDetail(String orderId);

    public void sendOrder(String orderId);

    public void receiverOrder(String orderId);
}
