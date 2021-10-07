package com.liu.test;

import com.liu.pojo.Cart;
import com.liu.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author liuliang
 * @create2021-09-2021/9/3-17:00
 * @email 2640336916@qq.com
 * @explain
 */
public class CartTest {
    public static Cart cart = new Cart();

    @Test
    public void addItem() {

/*        private Integer id;
        private String name;
        private Integer count;
        private BigDecimal price;
        private BigDecimal totalPrice;*/
        CartItem item = new CartItem(1, "大话西游", 1, new BigDecimal("37.7"), new BigDecimal("37.7"));
        CartItem item2 = new CartItem(1, "大话西游", 1, new BigDecimal("37.7"), new BigDecimal("37.7"));
        CartItem item3 = new CartItem(2, "数据结构", 1, new BigDecimal("57.3"), new BigDecimal("57.3"));

        cart.addItem(item);
        cart.addItem(item2);
        cart.addItem(item3);

        cart.getItems().forEach((key,value) -> System.out.println(key + ":" + value));

        System.out.println("******************");
        cart.getItems().entrySet().forEach(a->System.out.println(a.getKey()+"="+a.getValue()));

        System.out.println("******************");

        cart.getItems().keySet().forEach(c->System.out.println(c+"="+cart.getItems().get(c)));

        System.out.println("******************");
        for (Map.Entry<Integer,CartItem> entry:cart.getItems().entrySet()){
            System.out.println(entry.getKey() + "=" +entry.getValue());
        }
        System.out.println("******************");
        for (Integer key:cart.getItems().keySet()){
            System.out.println(key + "=" +cart.getItems().get(key));
        }
        System.out.println("******************");

        Iterator<Map.Entry<Integer, CartItem>> iterator = cart.getItems().entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, CartItem> next = iterator.next();
            System.out.println(next.getKey()+"="+next.getValue());
        }
        System.out.println("******************");

        Iterator<Integer> iterator1 = cart.getItems().keySet().iterator();
        while (iterator1.hasNext()){
            Integer next = iterator1.next();
            System.out.println(next + "=" +cart.getItems().get(next));
        }
        System.out.println("******************");

        cart.getItems().values().forEach(c->System.out.println(c));
        System.out.println("******************");
        System.out.println("TotalCount=" + cart.getTotalCount());
        System.out.println("TotalPrice=" + cart.getTotalPrice());
    }

    @Test
    public void deleteItem() {
        CartItem item = new CartItem(1, "大话西游", 1, new BigDecimal("37.7"), new BigDecimal("37.7"));
        CartItem item2 = new CartItem(1, "大话西游", 1, new BigDecimal("37.7"), new BigDecimal("37.7"));
        CartItem item3 = new CartItem(2, "数据结构", 1, new BigDecimal("57.3"), new BigDecimal("57.3"));
        CartItem item4 = new CartItem(1, "大话西游", 1, new BigDecimal("37.7"), new BigDecimal("37.7"));
        cart.addItem(item);
        cart.addItem(item2);
        cart.addItem(item3);
        cart.addItem(item4);

        cart.getItems().values().forEach(c->System.out.println(c));
        System.out.println(cart.getTotalCount());
        System.out.println(cart.getTotalPrice());
        System.out.println(cart);
        System.out.println("******************");
        cart.deleteItem(1);

        cart.getItems().values().forEach(c->System.out.println(c));
        System.out.println(cart.getTotalCount());
        System.out.println(cart.getTotalPrice());
        System.out.println(cart);

    }

    @Test
    public void clear() {
        CartItem item = new CartItem(1, "大话西游", 1, new BigDecimal("37.7"), new BigDecimal("37.7"));
        CartItem item2 = new CartItem(1, "大话西游", 1, new BigDecimal("37.7"), new BigDecimal("37.7"));
        CartItem item3 = new CartItem(2, "数据结构", 1, new BigDecimal("57.3"), new BigDecimal("57.3"));
        CartItem item4 = new CartItem(1, "大话西游", 1, new BigDecimal("37.7"), new BigDecimal("37.7"));
        cart.addItem(item);
        cart.addItem(item2);
        cart.addItem(item3);
        cart.addItem(item4);

        cart.getItems().values().forEach(c->System.out.println(c));
        System.out.println(cart.getTotalCount());
        System.out.println(cart.getTotalPrice());
        System.out.println(cart);
        System.out.println("******************");
        cart.clear();
        cart.getItems().values().forEach(c->System.out.println(c));
        System.out.println(cart.getTotalCount());
        System.out.println(cart.getTotalPrice());
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        CartItem item = new CartItem(1, "大话西游", 1, new BigDecimal("37.7"), new BigDecimal("37.7"));
        CartItem item2 = new CartItem(1, "大话西游", 1, new BigDecimal("37.7"), new BigDecimal("37.7"));
        CartItem item3 = new CartItem(2, "数据结构", 1, new BigDecimal("57.3"), new BigDecimal("57.3"));
        CartItem item4 = new CartItem(1, "大话西游", 1, new BigDecimal("37.7"), new BigDecimal("37.7"));
        cart.addItem(item);
        cart.addItem(item2);
        cart.addItem(item3);
        cart.addItem(item4);

        cart.getItems().values().forEach(c->System.out.println(c));
        System.out.println(cart.getTotalCount());
        System.out.println(cart.getTotalPrice());
        System.out.println(cart);
        System.out.println("******************");
        cart.updateCount(2,3);
        cart.getItems().values().forEach(c->System.out.println(c));
        System.out.println(cart.getTotalCount());
        System.out.println(cart.getTotalPrice());
        System.out.println(cart);
    }
}