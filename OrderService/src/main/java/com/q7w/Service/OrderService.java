package com.q7w.Service;

import com.q7w.Entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/11 18:25
 **/
public interface OrderService {
    public int createorder(Order order);
    public int createorder( int skuid,String buycode);
    public Page<Order> listall(Pageable pageable);
    public List<Order> querybyid(Long oid);
    public List<Order> querybyuser(Integer uid);
}
