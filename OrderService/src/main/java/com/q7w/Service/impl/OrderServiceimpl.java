package com.q7w.Service.impl;

import com.q7w.Dao.OrderDao;
import com.q7w.Entity.Order;
import com.q7w.Service.ItemsFeign;
import com.q7w.Service.OrderService;
import com.q7w.common.result.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/11 18:26
 **/
@Service
public class OrderServiceimpl implements OrderService {
    @Autowired
    ItemsFeign itemsFeign;
    @Autowired
    OrderDao orderDao;
    @Override
    public int createorder(Order order) {
        return 0;
    }
    public int createorder( int skuid,String buycode){
        ResponseData res = itemsFeign.skustockfeign();
        if (res.getRspCode().equals("502")){return 2;}
        Object stock = res.getData();
        return 0;
    }
    @Override
    public Page<Order> listall(Pageable pageable) {
        return orderDao.findAll(pageable);
    }

    @Override
    public List<Order> querybyid(Long oid) {
        return orderDao.findAllByOid(oid);
    }

    @Override
    public List<Order> querybyuser(Integer uid) {
        return null;
    }
}
