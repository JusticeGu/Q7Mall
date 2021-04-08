package com.q7w.controller;

import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import com.q7w.common.service.RedisService;
import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaogu
 * @date 2021/4/8 14:13
 **/
@RestController
@Api(tags = "品牌服务接口")
@RequestMapping("/api/user")
public class UserServiceController {
    @Autowired
    RedisService redisService;
    @GetMapping("/rset")
    public ResponseData rset(String key,String value){
        redisService.set(key,value);
        return new ResponseData(ExceptionMsg.SUCCESS,"success");
    }
    @GetMapping("/rget")
    public ResponseData rget(String key){
        return new ResponseData(ExceptionMsg.SUCCESS,redisService.get(key));
    }
}
