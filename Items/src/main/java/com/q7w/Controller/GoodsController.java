package com.q7w.Controller;

import com.q7w.DTO.Product;
import com.q7w.Entity.Goods;
import com.q7w.Service.GoodsService;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiaogu
 * @date 2021/3/30 15:44
 **/
@RestController
@Api(tags = "商品(SPU)服务接口")
@RequestMapping("/api/item")
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @GetMapping("/list")
    @ApiOperation("商品列表")
    public ResponseData listitem(){
        //逻辑
        return new ResponseData(ExceptionMsg.SUCCESS,goodsService.list());
    }
    @PostMapping("itemsadd")
    @ApiOperation("商品添加")
    public ResponseData itemadd(@RequestBody Product product){
        //逻辑
        return new ResponseData(ExceptionMsg.SUCCESS,goodsService.list());
    }


}
