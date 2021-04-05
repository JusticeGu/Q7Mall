package com.q7mall.Controller;

import com.q7mall.Entity.Brand;
import com.q7mall.Service.BrandService;
import com.q7mall.common.result.ExceptionMsg;
import com.q7mall.common.result.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiaogu
 * @date 2021/3/31 15:05
 **/
@RestController
@Api(tags = "品牌服务接口")
@RequestMapping("/api/brand")
public class BrandController {
    @Autowired
    BrandService brandService;
    @GetMapping("/")
    public ResponseData brandindex(){
        return new ResponseData(ExceptionMsg.SUCCESS,"品牌服务接口");
    }
    @GetMapping("/list")
    @ApiOperation("品牌列表")
    public ResponseData listitem(){
        //逻辑
        return new ResponseData(ExceptionMsg.SUCCESS,brandService.list());
    }
    @GetMapping("/findbrandname")
    @ApiOperation("品牌查询")
    public ResponseData brandquery(@RequestParam String brandname){
        //逻辑
        return new ResponseData(ExceptionMsg.SUCCESS,brandService.list());
    }
    @PostMapping("brandop")
    @ApiOperation("品牌添加")
    public ResponseData itemadd(@RequestBody Brand brand){
        //逻辑
        return new ResponseData(ExceptionMsg.SUCCESS,"添加成功");
    }
    @DeleteMapping("branddel")
    @ApiOperation("品牌删除")
    public ResponseData itemdel(@RequestParam int bid){
        //逻辑
        return new ResponseData(ExceptionMsg.SUCCESS,"删除成功");
    }
    @PutMapping("brandop")
    @ApiOperation("品牌修改")
    public ResponseData itemmodify(@RequestBody Brand brand){
        //逻辑
        return new ResponseData(ExceptionMsg.SUCCESS,"修改成功");
    }

}
