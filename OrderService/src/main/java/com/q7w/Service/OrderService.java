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
    public int test(Integer sid);
    public Order querybuid(Long oid);
    public int createorder(Order order);
    public int createorder(int skuid,String buycode);
    public int createorder(int skuid,int num);
    public Long corder(Long sku);
    public int cancelorder(Long oid);
    public Page<Order> listall(Pageable pageable);
    public List<Order> querybyid(Long oid);
    public List<Order> querybyuser(Integer uid);
    public int updateorder(Long orderid,Long payid,String content,int status);
    public int paidorder(Long orderid,Long payid,String content);
    public int getorderstatus(Long orderid);
    public boolean message(Long oid);
}
