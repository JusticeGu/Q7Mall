package com.q7w.controller;

import com.q7w.Entity.Role;
import com.q7w.Service.RoleMenuService;
import com.q7w.Service.RoleResourceService;
import com.q7w.Service.RoleService;
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
@RequestMapping("/api/admin/role")
public class RoleController {
    @Autowired
    RoleService roleService;
    @Autowired
    RoleResourceService roleResourceService;
    @Autowired
    RoleMenuService roleMenuService;
    @GetMapping("/list")
    @ApiOperation("角色列表")
    public ResponseData listall(){
        return new ResponseData(ExceptionMsg.SUCCESS,roleService.list());
    }
    @GetMapping("/rolemenulist")
    @ApiOperation("角色可见菜单查询")
    public ResponseData rolemenulist(@RequestParam Long rid){
        return new ResponseData(ExceptionMsg.SUCCESS,roleMenuService.findAllByRid(rid));
    }
    @PostMapping("/add")
    @ApiOperation("角色新增")
    public ResponseData addrole(@RequestBody Role role){
        int status = roleService.create(role);
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"新增角色成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"角色新增失败");
    }
    @DeleteMapping("/del")
    @ApiOperation("角色删除")
    public ResponseData delrole(Long rid){
        List<Long> rids = new ArrayList();
        rids.add(rid);
        int status = roleService.delete(rids);
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"删除角色成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"角色删除失败");
    }
    @PutMapping("/update")
    @ApiOperation("角色更新")
    public ResponseData updaterole(@RequestBody Role role){
        int status = roleService.update(role.getId(),role);
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"更新角色成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"角色更新失败");
    }
    @PostMapping("/role_menu")
    @ApiOperation("角色添加菜单")
    public ResponseData addrolemenu(@RequestBody Long rid, LinkedHashMap mids){
        boolean status = roleMenuService.updateRoleMenu(rid,mids);
        if (status){return new ResponseData(ExceptionMsg.SUCCESS,"角色菜单更新成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"角色菜单更新失败");
    }
    @PostMapping("/role_res")
    @ApiOperation("角色添加权限")
    public ResponseData addroleres(@RequestBody Role role){
        int status = roleService.create(role);
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"角色权限更新成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"角色权限更新失败");
    }
    @PutMapping("/role_menu")
    @ApiOperation("角色菜单更新")
    public ResponseData updaterolemenu(@RequestBody Long rid, LinkedHashMap mids){
        boolean status = roleMenuService.updateRoleMenu(rid,mids);
        if (status){return new ResponseData(ExceptionMsg.SUCCESS,"角色菜单更新成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"角色菜单更新失败");
    }
    @PutMapping("/role_res")
    @ApiOperation("角色权限更新")
    public ResponseData updateroleres(@RequestBody Role role){
        int status = roleService.create(role);
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"角色权限更新成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"角色权限更新失败");
    }
}
