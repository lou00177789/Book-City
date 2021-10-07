package com.liu.pojo;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;

/**
 * @author liuliang
 * @create2021-09-2021/9/3-16:19
 * @email 2640336916@qq.com
 * @explain 购物车对象
 */
public class Cart {
    //    private Integer totalCount;
//    private BigDecimal totalPrice;
    private Map<Integer, CartItem> items = new LinkedHashMap<>();

    /**
     * 添加商品项
     *
     * @param cartItem
     */
    public void addItem(CartItem cartItem) {
        //如果添加过，就数量增加，金额刷新，没添加过，就新建一个
        CartItem item = items.get(cartItem.getId());
        if (item == null) {
            items.put(cartItem.getId(), cartItem);
            return;
        }
        item.setCount(item.getCount() + 1);//数量累加
        item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));//金额更新

    }

    /**
     * 删除商品项
     *
     * @param id
     */
    public void deleteItem(Integer id) {
        items.remove(id);

    }

    /**
     * 清空购物车
     */
    public void clear() {

        items.clear();
    }

    /**
     * 修改商品数量
     */
    public void updateCount(Integer id, Integer count) {
        CartItem item = items.get(id);
        if (item != null) {
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }

    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + this.getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        if (items != null){
            for (CartItem value : items.values()) {
                totalCount += value.getCount();
            }
        }

        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);

        if (items != null){
            for (CartItem value : items.values()) {
                totalPrice = totalPrice.add(value.getTotalPrice());
            }
        }

        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }
}
