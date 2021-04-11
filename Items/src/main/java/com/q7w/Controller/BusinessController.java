package com.q7w.Controller;

import com.q7w.Service.SkuService;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiaogu
 * @date 2021/3/31 17:01
 **/
@RestController
@Api(tags = "交易核心服务接口")
@RequestMapping("/api/buy")
public class BusinessController {
    @Autowired
    SkuService skuService;
    @GetMapping("/sku")
    @ApiOperation("商品SKU获取")
    public ResponseData getskuinfo(){
        //逻辑
        return new ResponseData(ExceptionMsg.SUCCESS,"SKU获取成功");
    }
    @PostMapping("/{skuid}/business/")
    @ApiOperation("商品购买（正常端口）")
    public ResponseData business(@PathVariable("skuid") int kid){
        //逻辑
        return new ResponseData(ExceptionMsg.SUCCESS,kid+":交易成功，您的订单正在被审核请稍后查看具体订单状态");
    }
    @PostMapping("/{skuid}/seckill-business/")
    @ApiOperation("商品购买（秒杀哦端口）")
    public ResponseData business_seckill(@PathVariable("skuid") int skuid,@RequestParam String buycode){
        int status= skuService.skucut(skuid,1);
        if (buycode == null){
            return new ResponseData(ExceptionMsg.FAILED,"请不要非法请求服务端口，您的相关信息将被记录");
        }
        switch (status) {
            case -1:
                return new ResponseData(ExceptionMsg.FAILED,"秒杀失败库存不足");
            case -2:
                return new ResponseData(ExceptionMsg.FAILED,"秒杀失败:商品信息非法，请勿通过第三方通道进行操作");
            default:
                return new ResponseData(ExceptionMsg.SUCCESS,"交易成功，您的订单正在被审核请稍后查看具体订单状态");
        }
    }
}
