package com.q7w.Controller;

import com.q7w.Entity.Brand;
import com.q7w.Service.BrandService;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import com.q7w.rabbit.SenderA;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
    @Autowired
    SenderA senderA;
    @GetMapping("/")
    public ResponseData brandindex(){
        return new ResponseData(ExceptionMsg.SUCCESS,"品牌服务接口");
    }
    @GetMapping("/mqtest")
    @ApiOperation("品牌查询")
    public ResponseData mqtest(@RequestParam String brandname){
        HashMap map = new HashMap();
        senderA.sendmsg(1,1,brandname,map);
        //逻辑
        return new ResponseData(ExceptionMsg.SUCCESS,"队列已上传");
    }
    @GetMapping("/list")
    @ApiOperation("品牌列表")
    public ResponseData listitem(@RequestParam(value = "start",defaultValue = "0")Integer start,
                                 @RequestParam(value = "num",defaultValue = "10")Integer num){
        start = start<0?0:start;
        Sort sort = Sort.by(Sort.Direction.DESC, "bid");
        Pageable pageable = PageRequest.of(start, num, sort);
            Page<Brand> page = brandService.list(pageable);
        return new ResponseData(ExceptionMsg.SUCCESS,page);
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
        brandService.modefybrand(brand);
        return new ResponseData(ExceptionMsg.SUCCESS,"修改成功");
    }

}
