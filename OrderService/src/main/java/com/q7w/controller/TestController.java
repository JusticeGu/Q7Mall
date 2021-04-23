package com.q7w.controller;

import com.q7w.Service.ItemsFeign;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaogu
 * @date 2021/4/19 19:18
 **/
@RestController
@Api(tags = "测试接口")
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    ItemsFeign itemsFeign;
    @GetMapping("/get")
    @ApiOperation("Feign获取")
    public ResponseData getskuinfo(int sid){
        int status = itemsFeign.skustockcut(sid,1);
        if (status == 1){return new ResponseData(ExceptionMsg.SUCCESS,"库存已锁定请在指定时间内支付");}
        else if(status == -1){return new ResponseData(ExceptionMsg.FAILED,status+":"+"库存不足");}
        else {return new ResponseData(ExceptionMsg.FAILED,status+":"+"异常");}

    }
}
