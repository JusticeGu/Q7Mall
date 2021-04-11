package com.q7w.Service;

import com.q7w.Entity.Order;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/11 18:25
 **/
public interface OrderService {
    public int createorder(Order order);
    public List<Order> listall();
}
