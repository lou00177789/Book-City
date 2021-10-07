package com.liu.dao;

import com.liu.pojo.OrderItem;

import java.util.List;

/**
 * @author liuliang
 * @create2021-09-2021/9/5-23:13
 * @email 2640336916@qq.com
 * @explain
 */
public interface OrderItemDao {
    public int saveOrderItem(OrderItem orderItem);

    public List<OrderItem> orderDetail(String orderId);

}
