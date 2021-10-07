package com.liu.dao.impl;

import com.liu.dao.OrderDao;
import com.liu.pojo.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liuliang
 * @create2021-09-2021/9/5-23:15
 * @email 2640336916@qq.com
 * @explain
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`)values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryMyOrders(Integer id) {
        String sql = "SELECT `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId FROM t_order WHERE user_id = ?";
        return queryForList(Order.class,sql,id);
    }

    @Override
    public List<Order> queryAllOrders() {
        String sql = "SELECT `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId FROM t_order";
        return queryForList(Order.class,sql);
    }

    @Override
    public void sendOrder(String orderId) {
        String sql = "update t_order set `status`=1 where `order_id`=?";
        update(sql,orderId);
    }

    @Override
    public void receiverOrder(String orderId) {
        String sql = "update t_order set `status`=2 where `order_id`=?";
        update(sql,orderId);
    }
}
