package com.q7w.Controller;

import com.q7w.DTO.Product;
import com.q7w.Entity.Brand;
import com.q7w.Entity.Goods;
import com.q7w.Service.GoodsService;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiaogu
 * @date 2021/3/30 15:44
 **/
@RestController
@Api(tags = "商品(SPU)服务接口")
@RequestMapping("/api/item")
@CrossOrigin
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @GetMapping("/itemsinfo")
    @ApiOperation("商品信息B端")
    public ResponseData itemsinfo(int gid){
        Product product = goodsService.iteminfo(gid);
        if (product.getGoodsinfo().getStatus()==1){
            return new ResponseData(ExceptionMsg.SUCCESS,product);
        }else {
            return new ResponseData(ExceptionMsg.ContextError,"商品正在被审核或已被下架！");
        }

    }
    @GetMapping("/list")
    @ApiOperation("商品列表B端")
    public ResponseData listitem_b(@RequestParam(value = "start",defaultValue = "0")Integer start,
                                   @RequestParam(value = "num",defaultValue = "10")Integer num){
        start = start<0?0:start;
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, num, sort);
        Page<Goods> page = goodsService.list(pageable);
        return new ResponseData(ExceptionMsg.SUCCESS,page);
    }
    @GetMapping("/listbycate")
    @ApiOperation("分类商品列表")
    public ResponseData listbycate(@RequestParam(value = "start",defaultValue = "0")Integer start,
                                   @RequestParam(value = "num",defaultValue = "10")Integer num){
        start = start<0?0:start;
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, num, sort);
        Page<Goods> page = goodsService.list(pageable);
        return new ResponseData(ExceptionMsg.SUCCESS,page);
    }
    @GetMapping("/admin/list")
    @ApiOperation("商品列表C端")
    public ResponseData listitem_c(@RequestParam(value = "start",defaultValue = "0")Integer start,
                                   @RequestParam(value = "num",defaultValue = "10")Integer num){
        start = start<0?0:start;
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, num, sort);
        Page<Goods> page = goodsService.listall(pageable);
        return new ResponseData(ExceptionMsg.SUCCESS,page);
    }
    @PostMapping("itemsadd")
    @ApiOperation("商品添加")
    public ResponseData itemadd(@RequestBody Goods product){
            byte status = goodsService.addGoods(product);
            switch (status) {
                    case 1:
                        return new ResponseData(ExceptionMsg.SUCCESS, "添加成功");
                    case 2:
                        return new ResponseData(ExceptionMsg.FAILED, "商品已存在，请勿重复提交");
                    case 3:
                        return new ResponseData(ExceptionMsg.FAILED, "所选品牌不存在，请重试！");
                }
                return new ResponseData(ExceptionMsg.SUCCESS,"添加成功");

    }
    @GetMapping("/getlist")
    @ApiOperation("B端商品列表")
    public ResponseData listb(){
        return new ResponseData(ExceptionMsg.SUCCESS,goodsService.listall_b());
    }


}
