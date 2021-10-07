package com.liu.test;

import com.liu.dao.impl.OrderDaoImpl;
import com.liu.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


import static org.junit.Assert.*;

/**
 * @author liuliang
 * @create2021-09-2021/9/5-23:36
 * @email 2640336916@qq.com
 * @explain
 */
public class OrderDaoTest {

    OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
    @Test
    public void saveOrder() {
        Order order = new Order("2021031201", new Date(), new BigDecimal(377), 1, 1);
        orderDaoImpl.saveOrder(order);
    }
    @Test
    public void queryMyOrders() {

        List<Order> orders = orderDaoImpl.queryMyOrders(1);
        System.out.println(orders);
    }
}