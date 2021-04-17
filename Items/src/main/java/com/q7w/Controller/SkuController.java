package com.q7w.Controller;

import com.q7w.Entity.Goods_sku;
import com.q7w.Service.SkuService;
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
 * @date 2021/3/31 17:36
 **/
@RestController
@Api(tags = "商品(SKU)服务接口")
@RequestMapping("/api/sku")
public class SkuController {
    @Autowired
    SkuService skuService;
    @GetMapping("/list")
    @ApiOperation("SKU列表")
    public ResponseData listsku(){
        //逻辑
        return new ResponseData(ExceptionMsg.SUCCESS,skuService.listall());
    }
    @GetMapping("/query")
    @ApiOperation("SKU查询")
    public ResponseData skuquery(int gid){
        //逻辑
        return new ResponseData(ExceptionMsg.SUCCESS,skuService.listall());
    }
    @GetMapping("/skuadd")
    @ApiOperation("SKU新增")
    public ResponseData addsku(Goods_sku goods_sku){
        //逻辑
        return new ResponseData(ExceptionMsg.SUCCESS,skuService.listall());
    }
}
