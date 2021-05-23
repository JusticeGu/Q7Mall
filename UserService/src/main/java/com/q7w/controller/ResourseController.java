package com.q7w.controller;

import com.q7w.Entity.Resource;

import com.q7w.Service.ResourceService;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/27 16:37
 **/
@RestController
@Api(tags = "用户资源接口")
@RequestMapping("/v1/resource")
public class ResourseController {
    @Autowired
    ResourceService resourceService;
    @GetMapping("/list")
    @ApiOperation("资源查询")
    public ResponseData reslist(){
        return new ResponseData(ExceptionMsg.SUCCESS,resourceService.listAll());
    }
    @GetMapping("/listbytype")
    @ApiOperation("资源查询")
    public ResponseData reslist(@RequestParam Integer type){
        return new ResponseData(ExceptionMsg.SUCCESS,resourceService.listbytype(type));
    }
    @PostMapping("")
    @ApiOperation("资源新增")
    public ResponseData addResourse(@RequestBody Resource resource){
        int status = resourceService.create(resource);
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"新增资源成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"资源新增失败");
    }
    @DeleteMapping("")
    @ApiOperation("资源删除")
    public ResponseData delResourse(Long rid){
        int status = resourceService.delete(rid);
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"删除资源成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"资源删除失败");
    }
    @PutMapping("")
    @ApiOperation("资源更新")
    public ResponseData updateResourse(@RequestBody Resource resource){
        int status = resourceService.update(resource.getId(),resource);
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"更新资源成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"资源更新失败");
    }
    @PostMapping("/Resourse_add_menu")
    @ApiOperation("资源添加菜单")
    public ResponseData addResoursemenu(@RequestBody Resource resource){
        int status = resourceService.create(resource);
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"资源菜单更新成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"资源菜单更新失败");
    }
    @GetMapping("/getroleresmap")
    @ApiOperation("资源查询")
    public ResponseData getroleresmap(){
        return new ResponseData(ExceptionMsg.SUCCESS,resourceService.getrolrresmap());
    }

}
