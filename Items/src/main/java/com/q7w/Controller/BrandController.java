package com.q7w.Controller;

import com.q7w.Entity.Brand;
import com.q7w.Service.BrandService;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
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
        return new ResponseData(ExceptionMsg.SUCCESS,brandService.querybrand(brandname));
    }
    @PostMapping("brandop")
    @ApiOperation("品牌添加")
    public ResponseData itemadd(@RequestBody Brand brand){
        byte status = brandService.addbrand(brand);
        switch (status) {
            case 1:
                return new ResponseData(ExceptionMsg.SUCCESS,"添加成功");
            case 2:
                return new ResponseData(ExceptionMsg.FAILED,"请勿重复提交");
        }
        return new ResponseData(ExceptionMsg.SUCCESS,"添加成功");
    }
    @DeleteMapping("branddel")
    @ApiOperation("品牌删除")
    public ResponseData itemdel(@RequestParam int bid){
        byte status = brandService.delbrand(bid);
        switch (status) {
            case 1:
                return new ResponseData(ExceptionMsg.SUCCESS,"删除成功");
            case 2:
                return new ResponseData(ExceptionMsg.FAILED,"品牌不存在/重复提交请确认后再试！");
        }
        return new ResponseData(ExceptionMsg.SUCCESS,"success");
    }
    @PutMapping("brandop")
    @ApiOperation("品牌修改")
    public ResponseData itemmodify(@RequestBody Brand brand){
        //逻辑
        return new ResponseData(ExceptionMsg.SUCCESS,"修改成功");
    }

}
