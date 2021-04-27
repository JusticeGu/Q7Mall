package com.q7w.controller;

import com.q7w.Entity.Menu;

import com.q7w.Service.MenuService;
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
@Api(tags = "用户菜单接口")
@RequestMapping("/api/admin/menu")
public class MenuController {
    @Autowired
    MenuService menuService;
    @GetMapping("/list")
    @ApiOperation("菜单列表")
    public ResponseData liatall(){
        return new ResponseData(ExceptionMsg.SUCCESS,menuService.list());
    }
    @PostMapping("/add")
    @ApiOperation("菜单新增")
    public ResponseData addrole(@RequestBody Menu menu){
        int status = menuService.create(menu);
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"新增菜单成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"菜单新增失败");
    }
    @DeleteMapping("/del")
    @ApiOperation("菜单删除")
    public ResponseData delrole(Long mid){
        List<Long> mids = new ArrayList();
        mids.add(mid);
        int status = menuService.delete(mids);
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"删除菜单成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"菜单删除失败");
    }
    @PutMapping("/update")
    @ApiOperation("菜单更新")
    public ResponseData updaterole(@RequestBody Menu menu){
        int status = menuService.update(menu.getId(),menu);
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"更新菜单成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"菜单更新失败");
    }
}
