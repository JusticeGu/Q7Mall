package com.q7w.Controller;

import com.q7w.Entity.Categories;
import com.q7w.Service.CategoriesService;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiaogu
 * @date 2021/4/10 12:56
 **/
@RestController
@Api(tags = "商品分类接口")
@RequestMapping("/api/categories")
@CrossOrigin
public class CateController {
    @Autowired
    CategoriesService categoriesService;

    @GetMapping("/list")
    @ApiOperation("分类列表")
    public ResponseData listCate(){
        //逻辑
        return new ResponseData(ExceptionMsg.SUCCESS,categoriesService.list());
    }
    @GetMapping("/findcatename")
    @ApiOperation("分类查询")
    public ResponseData catequery(@RequestParam String brandname){
        //逻辑
        return new ResponseData(ExceptionMsg.SUCCESS,categoriesService.querybyname(brandname));
    }

    @PostMapping("cateop")
    @ApiOperation("分类添加")
    public ResponseData cateadd(@RequestBody Categories categories){
        byte status = categoriesService.add(categories);
        switch (status) {
            case 1:
                return new ResponseData(ExceptionMsg.SUCCESS,"添加成功");
            case 2:
                return new ResponseData(ExceptionMsg.FAILED,"分类已存在，请勿重复提交");
        }
        return new ResponseData(ExceptionMsg.SUCCESS,"添加成功");
    }
    @DeleteMapping("catedel")
    @ApiOperation("分类删除")
    public ResponseData catedel(@RequestParam int id){
        byte status = categoriesService.del(id);
        switch (status) {
            case 1:
                return new ResponseData(ExceptionMsg.SUCCESS,"删除成功");
            case 2:
                return new ResponseData(ExceptionMsg.FAILED,"分类不存在/重复提交请确认后再试！");
        }
        return new ResponseData(ExceptionMsg.SUCCESS,"success");
    }
    @PutMapping("brandop")
    @ApiOperation("分类修改")
    public ResponseData catemodify(@RequestBody Categories categories){
        byte status = categoriesService.modify(categories);
        switch (status) {
            case 1:
                return new ResponseData(ExceptionMsg.SUCCESS,"修改成功");
            case 2:
                return new ResponseData(ExceptionMsg.FAILED,"分类不存在/重复提交请确认后再试！");
        }
        return new ResponseData(ExceptionMsg.ERROR,"sorry");
    }
}
