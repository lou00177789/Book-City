package com.liu.test;

import com.liu.pojo.Cart;
import com.liu.pojo.CartItem;
import com.liu.service.impl.OrderServiceImpl;
import org.junit.Test;


import java.math.BigDecimal;


import static org.junit.Assert.*;

/**
 * @author liuliang
 * @create2021-09-2021/9/6-12:52
 * @email 2640336916@qq.com
 * @explain
 */
public class OrderServiceTest {

    private OrderServiceImpl orderService = new OrderServiceImpl();
    @Test
    public void createOrder() {
        Cart cart = new Cart();
        CartItem item = new CartItem(1, "大话西游", 1, new BigDecimal("37.7"), new BigDecimal("37.7"));
        CartItem item2 = new CartItem(1, "大话西游", 1, new BigDecimal("37.7"), new BigDecimal("37.7"));
        CartItem item3 = new CartItem(2, "数据结构", 1, new BigDecimal("57.3"), new BigDecimal("57.3"));


        cart.addItem(item);
        cart.addItem(item2);
        cart.addItem(item2);
        cart.addItem(item3);

        String order = orderService.createOrder(cart, 1);
        System.out.println(order);
    }
}