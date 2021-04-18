package com.q7w.controller;

import com.q7w.Entity.Order;
import com.q7w.Service.OrderService;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaogu
 * @date 2021/4/18 19:21
 **/
@RestController
@Api(tags = "订单服务接口")
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("/list")
    @ApiOperation("订单列表")
    public ResponseData listorder(@RequestParam(value = "start",defaultValue = "0")Integer start,
                                 @RequestParam(value = "num",defaultValue = "10")Integer num){
        start = start<0?0:start;
        Sort sort = Sort.by(Sort.Direction.DESC, "bid");
        Pageable pageable = PageRequest.of(start, num, sort);
        Page<Order> page = orderService.listall(pageable);
        return new ResponseData(ExceptionMsg.SUCCESS,page);
    }
    @GetMapping("/query")
    @ApiOperation("订单查询(订单号查询)")
    public ResponseData queryorderbyid(Long oid){
        return new ResponseData(ExceptionMsg.SUCCESS,orderService.querybyid(oid));
    }
}
