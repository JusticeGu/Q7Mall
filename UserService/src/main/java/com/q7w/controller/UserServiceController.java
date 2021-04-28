package com.q7w.controller;

import com.q7w.Entity.User;
import com.q7w.Entity.UserRole;
import com.q7w.Entity.Userpro;
import com.q7w.Service.UserService;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import com.q7w.common.service.RedisService;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author xiaogu
 * @date 2021/4/8 14:13
 **/
@RestController
@Api(tags = "用户服务接口")
@RequestMapping("/api/admin/user")
public class UserServiceController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation("C端用户注册")
    public ResponseData register(@RequestBody  User user,String authcode){
        int status = userService.register(user.getUsername(),user.getPassword(),user.getEmail(),authcode);
        switch (status) {
            case 1:
                return new ResponseData(ExceptionMsg.SUCCESS,"注册成功");
            case 2:
                return new ResponseData(ExceptionMsg.FAILED,"用户名已存在");
            case 3:
                return new ResponseData(ExceptionMsg.FAILED,"验证码验证失败");
        }
        return new ResponseData(ExceptionMsg.ERROR,"后端错误");
    }
    @GetMapping("/getauthcode")
    @ApiOperation("获取验证码接口")
    public ResponseData getauthcode(String email){
        return new ResponseData(ExceptionMsg.SUCCESS,userService.sendregmail("123",email));
    }
    @GetMapping("/getcurrentusername")
    @ApiOperation("获取当前用户用户名")
    public String getcurrentusername(){
        return userService.getcurrertusername();
    }
    @GetMapping("/getuserrole")
    @ApiOperation("查看用户角色")
    public ResponseData getuserrole(Long uid){
        return new ResponseData(ExceptionMsg.SUCCESS,userService.listuserroles(uid));}
    @GetMapping("/list")
    @ApiOperation("用户列表")
    public ResponseData listitem(@RequestParam(value = "start",defaultValue = "0")Integer start,
                                 @RequestParam(value = "num",defaultValue = "10")Integer num){
        start = start<0?0:start;
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, num, sort);
        Page<User> page = userService.listall(pageable);
        return new ResponseData(ExceptionMsg.SUCCESS,page);
    }
    @PostMapping("/extendUserinfo")
    @ApiOperation("完善用户信息C端")
    public ResponseData extendUserinfo(@RequestBody Userpro userpro){
        int status = userService.extendUserinfo(userpro);
        switch (status) {
            case 1:
                return new ResponseData(ExceptionMsg.SUCCESS,"提交成功");
            case 2:
                return new ResponseData(ExceptionMsg.FAILED,"验证失败");
        }
        return new ResponseData(ExceptionMsg.ERROR,"提交失败");
    }

    @PostMapping("/allocUserrole")
    @ApiOperation("用户角色分配")
    public ResponseData allocUserrole(@RequestBody UserRole userRole){
        int status = userService.allocrole(userRole.getUid(),userRole.getRids());
        switch (status) {
            case 1:
                return new ResponseData(ExceptionMsg.SUCCESS,"分配成功");
            case 2:
                return new ResponseData(ExceptionMsg.FAILED,"分配失败");
        }
        return new ResponseData(ExceptionMsg.ERROR,"提交失败");
    }
}
