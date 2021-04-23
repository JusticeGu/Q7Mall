package com.q7w.Service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.q7w.Dao.OrderDao;
import com.q7w.Entity.Order;
import com.q7w.Service.ItemService;
import com.q7w.Service.ItemsFeign;
import com.q7w.Service.OrderService;
import com.q7w.Service.UserFeign;
import com.q7w.common.exception.GlobalException;
import com.q7w.mq.CancelOrderSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/11 18:26
 **/
@Service
public class OrderServiceimpl implements OrderService {
    @Autowired
    ItemService itemService;
    @Autowired
    OrderDao orderDao;
    @Autowired
    ItemsFeign itemsFeign;
    @Autowired
    UserFeign userFeign;
    private static Logger LOGGER = LoggerFactory.getLogger(OrderServiceimpl.class);
    @Autowired
    private CancelOrderSender cancelOrderSender;

    @Override
    public Order querybuid(Long oid) {
        Order order = orderDao.findByOid(oid);
        if (order==null){
            throw new GlobalException("805X06","订单不存在");
        }
        return order;
    }

    @Override
    public int test(Integer sid) {
//        ResponseData res = itemsFeign.skuquery(sid);
//        if (res.getRspCode().equals("501")){return -1;}
//        Object stock = res.getData();
//        try {
//            int a = Integer.parseInt((String) stock);
//            return a;
//
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//        }
        return 0;
    }

    @Override
    public int createorder(Order order) {
        return 0;
    }
    public int createorder( int skuid,String buycode){
        int res = itemService.stockquery(skuid);
        if (res<=0){return -1;}
        if (res==-3){return 2;}
        Date date = DateUtil.date();
        sendDelayMessageCancelOrder(DateUtil.year(date)*10000000+(DateUtil.month(date)+1)*100000+DateUtil.dayOfMonth(date)+RandomUtil.randomLong(6));
        return res;
    }

    @Override
    public Long corder(Long sku) {
        //todo 执行一系类下单操作，具体参考mall项目
        LOGGER.info("process generateOrder");
        //下单完成后开启一个延迟消息，用于当用户没有付款时取消订单（orderId应该在下单后生成）
        sendDelayMessageCancelOrder(sku);

        return 1L;
    }
    private void sendDelayMessageCancelOrder(Long orderId) {
        //获取订单超时时间，假设为60分钟
        long delayTimes = 30 * 1000;
        //发送延迟消息
        cancelOrderSender.sendMessage(orderId, delayTimes);
    }
    @Override
    public int cancelorder(Long oid) {
        //todo 执行一系类取消订单操作，具体参考mall项目
        LOGGER.info("订单逾期已被取消：orderId:{}",oid);
        updateorder(oid,0L,"逾期未支付",-3);
        return 1;
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

    @Override
    public int createorder(int skuid, int num) {
        int status = itemsFeign.skustockcut(skuid,num);
        if (status>=0){
            LOGGER.info("锁单成功正在创建订单...");
            //下单完成后开启一个延迟消息，用于当用户没有付款时取消订单（orderId应该在下单后生成）
            Order order = new Order();
            Date now= new Date();
            Long time = now.getTime();
            order.setCreateBy(userFeign.getusername());
            order.setCreateTime(time);
            order.setLastmodifiedBy(userFeign.getusername());
            order.setUpdateTime(time);
            Snowflake snowflake = IdUtil.getSnowflake(1, 1);
            long id = snowflake.nextId();
            sendDelayMessageCancelOrder(id);
            order.setOid(id);
            order.setSkuid(skuid);
            order.setBusstype(1);
            order.setType((byte)1);
            order.setStatus(0);
            orderDao.save(order);
            return 1;
        }else {
            LOGGER.info("库存不足，下单失败");
            return -1;
        }

    }

    @Override
    public int updateorder(Long orderid, Long payid, String content,int status) {
        Order order = querybuid(orderid);
        order.setStatus(status);
        order.setPayid(payid);
        order.setContent(content);
        Date now= new Date();
        Long time = now.getTime();
        order.setCreateBy(userFeign.getusername());
        order.setCreateTime(time);
        order.setLastmodifiedBy(userFeign.getusername());
        order.setUpdateTime(time);
        orderDao.save(order);
        return 1;
    }

    @Override
    public int getorderstatus(Long orderid) {
        try{
            Order order = querybuid(orderid);
            return order.getStatus();
        }catch (Exception e){
            return -4;
        }
    }

    @Override
    public boolean message(Long oid) {
        System.out.println(oid);
        return true;
    }
}
