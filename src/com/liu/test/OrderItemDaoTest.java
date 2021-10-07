package com.liu.test;

import com.liu.dao.impl.OrderItemDaoImpl;
import com.liu.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author liuliang
 * @create2021-09-2021/9/6-11:54
 * @email 2640336916@qq.com
 * @explain
 */
public class OrderItemDaoTest {
    OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();
    @Test
    public void saveOrderItem() {
        OrderItem orderItem = new OrderItem(1,"javaStudy",3,new BigDecimal(33),new BigDecimal(99),"2021031201");
        orderItemDao.saveOrderItem(orderItem);
    }
    @Test
    public void orderDetail() {
        System.out.println(orderItemDao.orderDetail("16311644930201"));
    }
}