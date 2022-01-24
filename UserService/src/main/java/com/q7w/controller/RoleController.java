package com.q7w.controller;

import com.q7w.DTO.RoleResourceDTO;
import com.q7w.Entity.Role;
import com.q7w.Entity.RoleMenu;
import com.q7w.Entity.RoleResource;
import com.q7w.Service.*;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/27 16:37
 **/
@RestController
@Api(tags = "用户角色接口")
@RequestMapping("/v1/role")
public class RoleController {
    @Autowired
    RoleService roleService;


    @GetMapping("/list")
    @ApiOperation("角色列表")
    public ResponseData listall(){
        return new ResponseData(ExceptionMsg.SUCCESS,roleService.list());
    }
    @GetMapping("/{rid}/menus")
    @ApiOperation("角色可见菜单查询")
    public ResponseData rolemenulist(@PathVariable Long rid){
        return new ResponseData(ExceptionMsg.SUCCESS,roleService.listroleMenu(rid));
    }
    @GetMapping("/{rid}/resource")
    @ApiOperation("角色权限列表")
    public ResponseData rolereslist(@PathVariable Long rid,@RequestParam Integer type){
        return new ResponseData(ExceptionMsg.SUCCESS,roleService.listroletypeRes(rid,type));
    }
    @GetMapping("/{rid}")
    @ApiOperation("角色查询")
    public ResponseData rolequery(@PathVariable Long rid){
        return new ResponseData(ExceptionMsg.SUCCESS,rid);
    }
    @PostMapping("")
    @ApiOperation("角色新增")
    public ResponseData addrole(@RequestBody Role role){
        int status = roleService.create(role);
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"新增角色成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"角色新增失败");
    }
    @DeleteMapping("")
    @ApiOperation("角色删除")
    public ResponseData delrole(Long rid){
        List<Long> rids = new ArrayList();
        rids.add(rid);
        int status = roleService.delete(rids);
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"删除角色成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"角色删除失败");
    }
    @PutMapping("")
    @ApiOperation("角色更新")
    public ResponseData updaterole(@RequestBody Role role){
        int status = roleService.update(role.getId(),role);
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"更新角色成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"角色更新失败");
    }
    @PostMapping("/{rid}/menus")
    @ApiOperation("角色添加菜单")
    public ResponseData addrolemenu(@PathVariable Long rid,@RequestBody RoleMenu roleMenu){
        int status = roleService.allocMenu(rid,roleMenu.getMids());
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"角色菜单更新成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"角色菜单更新失败");
    }
    @PostMapping("/{rid}/resource")
    @ApiOperation("角色添加权限")
    public ResponseData addroleres(@PathVariable Long rid,@RequestBody RoleResourceDTO roleResource){
        int status = roleService.allocResource(rid,roleResource.getRids(),roleResource.getType(),roleResource.getModelid());
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"角色权限更新成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"角色权限更新失败");
    }
    @PutMapping("/{rid}/menus")
    @ApiOperation("角色菜单更新")
    public ResponseData updaterolemenu(@PathVariable Long rid,@RequestBody RoleMenu roleMenu){
        int status = roleService.allocMenu(rid,roleMenu.getMids());
        if (status>=1){return new ResponseData(ExceptionMsg.SUCCESS,status+"条菜单记录已更新"); }
        return new ResponseData(ExceptionMsg.FAILED,"角色菜单更新失败");
    }
    @PutMapping("/{rid}/resource")
    @ApiOperation("角色权限更新")
    public ResponseData updateroleres(@PathVariable Long rid,@RequestBody RoleResourceDTO roleResource){
        int status = roleService.allocResource(rid,roleResource.getRids(),roleResource.getType(),roleResource.getModelid());
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"角色权限更新成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"角色权限更新失败");
    }
}
