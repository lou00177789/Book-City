package com.liu.dao.impl;

import com.liu.dao.OrderItemDao;
import com.liu.pojo.OrderItem;

import java.util.List;

/**
 * @author liuliang
 * @create2021-09-2021/9/5-23:17
 * @email 2640336916@qq.com
 * @explain
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`)values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(), orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> orderDetail(String orderId) {
        String sql = "select `id`,`name`,`count`,`price`,`total_price` totalPrice,`order_id` orderId from t_order_item where order_id = ?";
        return queryForList(OrderItem.class,sql,orderId);
    }
}
