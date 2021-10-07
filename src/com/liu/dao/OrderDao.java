package com.liu.dao;

import com.liu.pojo.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuliang
 * @create2021-09-2021/9/5-22:56
 * @email 2640336916@qq.com
 * @explain
 */
public interface OrderDao {

    public int saveOrder(Order order);

    public List<Order> queryMyOrders(Integer id);

    public List<Order> queryAllOrders();

    public void sendOrder(String orderId);

    public void receiverOrder(String orderId);
}
