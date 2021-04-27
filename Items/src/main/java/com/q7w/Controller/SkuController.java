package com.q7w.Controller;

import com.q7w.Entity.Goods_sku;
import com.q7w.Service.SkuService;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaogu
 * @date 2021/3/31 17:36
 **/
@RestController
@Api(tags = "商品(SKU)服务接口")
@RequestMapping("/api/sku")
@CrossOrigin
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
    public ResponseData skuquery(Integer sid){
        //逻辑
        return new ResponseData(ExceptionMsg.SUCCESS,skuService.skuquery(sid));
    }
    @GetMapping("/stock/query")
    @ApiOperation("SKU库存查询")
    public ResponseData skustockquery(@RequestParam Integer sid){
        //逻辑
        return new ResponseData(ExceptionMsg.SUCCESS,skuService.queryskustock(sid));
    }
    @GetMapping("/stock/test")
    @ApiOperation("调用测试")
    public Map skutest(@RequestParam("sid") Integer sid){
        Map map = new HashMap();
        map.put("key","测试成功");
        //逻辑
        return map;
    }
    @GetMapping("/skucut")
    @ApiOperation("SKU库存锁定")
    public int skustockcut(@RequestParam("skuid") int skuid,@RequestParam("num") int num){
        int status = skuService.skucut(skuid,num);
        if (status>=0){return 1;}
        else {return -1;}
    }
    @GetMapping("/Fquery")
    @ApiOperation("SKU查询-Feign")
    public int Feignskuquery(Integer gid){
        //逻辑
        return skuService.queryskustock(gid);
    }

    @PostMapping("/skuadd")
    @ApiOperation("SKU新增")
    public ResponseData addsku(@RequestBody Goods_sku goods_sku){
        //逻辑
        return new ResponseData(ExceptionMsg.SUCCESS,skuService.newsku(goods_sku));
    }
    @PostMapping("/skuup")
    @ApiOperation("SKU库存更新")
    public ResponseData upskustock(@RequestBody Goods_sku goods_sku){
        //逻辑
        return new ResponseData(ExceptionMsg.SUCCESS,"库存更新成功");
    }
    @GetMapping("/getskubuyspu")
    @ApiOperation("获取SPU下所有SKU")
    public ResponseData getspuallsku(@RequestParam int gid){
        //逻辑
        return new ResponseData(ExceptionMsg.SUCCESS,skuService.skuquery(gid));
    }
}
