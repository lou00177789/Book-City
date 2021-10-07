package com.liu.service.impl;


import com.liu.dao.impl.BookDaoImpl;
import com.liu.dao.impl.OrderDaoImpl;
import com.liu.dao.impl.OrderItemDaoImpl;
import com.liu.pojo.*;
import com.liu.service.OrderService;
import com.liu.utils.WEBUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author liuliang
 * @create2021-09-2021/9/6-12:22
 * @email 2640336916@qq.com
 * @explain
 */
public class OrderServiceImpl implements OrderService {
    private OrderDaoImpl orderDao = new OrderDaoImpl();
    private OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();
    private BookDaoImpl bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {

        String orderId = System.currentTimeMillis() + "" + userId;
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        orderDao.saveOrder(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(orderId);

        for (Map.Entry<Integer,CartItem> entry:cart.getItems().entrySet()){
            orderItem.setId(entry.getKey());
            orderItem.setName(entry.getValue().getName());
            orderItem.setCount(entry.getValue().getCount());
            orderItem.setPrice(entry.getValue().getPrice());
            orderItem.setTotalPrice(entry.getValue().getTotalPrice());
            orderItemDao.saveOrderItem(orderItem);
            //更新库存和销量
            Book book = bookDao.queryBookById(entry.getKey());
            book.setSales(book.getSales() + entry.getValue().getCount());
            book.setStock(book.getStock() - entry.getValue().getCount());
            bookDao.updateBook(book);
        }

        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> showMyOrders(Integer id) {
        return orderDao.queryMyOrders(id);
    }

    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryAllOrders();
    }

    @Override
    public List<OrderItem> orderDetail(String orderId) {
        return orderItemDao.orderDetail(orderId);
    }

    @Override
    public void sendOrder(String orderId) {
        orderDao.sendOrder(orderId);
    }

    @Override
    public void receiverOrder(String orderId) {
        orderDao.receiverOrder(orderId);
    }
}
