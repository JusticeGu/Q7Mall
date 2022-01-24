package com.q7w.controller;

import com.nimbusds.jose.JOSEException;
import com.q7w.DTO.Oauth2TokenDto;
import com.q7w.Entity.User;
import com.q7w.Entity.UserRole;
import com.q7w.Entity.Userpro;
import com.q7w.Service.ResourceService;
import com.q7w.Service.UserProfileService;
import com.q7w.Service.UserService;
import com.q7w.VO.UserVo;
import com.q7w.VO.Userinfo;
import com.q7w.common.domain.UserDto;
import com.q7w.common.result.CommonResult;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import com.q7w.common.service.RedisService;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;


/**
 * @author xiaogu
 * @date 2021/4/8 14:13
 **/
@RestController
@Api(tags = "用户服务接口")
@RequestMapping("/v1/user")
public class UserServiceController {
    @Autowired
    UserService userService;
    @Autowired
    UserProfileService userProfileService;


    @PostMapping("/login")
    @ApiOperation("B端登录")
    public CommonResult adminlogin(@ApiIgnore Principal principal, @RequestBody UserDto user) throws HttpRequestMethodNotSupportedException, ParseException {

        //Map map= userService.login(user.getUsername(),user.getPassword());
        return userService.login(user.getUsername(),user.getPassword());
    }
    @PostMapping("/renewaltoken")
    @ApiOperation("Token续签")
    public CommonResult tokenrrenewal(@RequestParam String token) throws HttpRequestMethodNotSupportedException {
       // Map map= userService.renewtoken(token);
        return userService.renewtoken(token);
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation("C端用户注册")
    public ResponseData register(@RequestBody UserVo user, String authcode){
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
    @GetMapping("/users")
    @ApiOperation("获取指定用户信息")
    public ResponseData getuserinfobyuid(Long id) {
        return new ResponseData(ExceptionMsg.SUCCESS,userService.getUserByuid(id));
    }
    @PutMapping("/users")
    @ApiOperation("修改指定用户信息")
    public ResponseData modifyuserinfo(Long id) {
        return new ResponseData(ExceptionMsg.SUCCESS,id);
    }
    @PutMapping("/resetpwd")
    @ApiOperation("重置用户密码")
    public ResponseData resetuserpwd(Long id) {

        return new ResponseData(ExceptionMsg.SUCCESS,id);
    }
    @DeleteMapping("/users")
    @ApiOperation("删除指定用户")
    public CommonResult deluser(Long id) {
        return CommonResult.success(userService.deluser(id));
    }
    @GetMapping("/authcode")
    @ApiOperation("获取验证码接口")
    public ResponseData getauthcode(@RequestParam String email){
        return new ResponseData(ExceptionMsg.SUCCESS,userService.sendregmail("123",email));
    }
    @GetMapping("/currentusername")
    @ApiOperation("获取当前用户用户名")
    public String getcurrentusername(){
        return userService.getcurrertusername();
    }
    @GetMapping("/currentuserid")
    @ApiOperation("获取当前用户ID")
    public Long getcurrentuid(){
//        return CommonResult.success(userService.getcurrertuserid());
        return userService.getcurrertuserid();
    }

    @GetMapping("/currentuser")
    @ApiOperation("获取当前用户信息")
    public ResponseData getcurrentuserinfo() {
        Userinfo userinfo =userService.getcurrentuserinfo();
        return new ResponseData(ExceptionMsg.SUCCESS,userinfo);
    }
    @GetMapping("/query")
    @ApiOperation("用户检索模糊查询")
    public ResponseData userquery(String name) {
        if (name.length()>=10||name.length()<=1){return new ResponseData(ExceptionMsg.FAILED,"请不要输入过长或过短的内容");}
        //逻辑
        return new ResponseData(ExceptionMsg.SUCCESS,userProfileService.queryuser("%"+name+"%"));
    }
    @GetMapping("/userrole")
    @ApiOperation("查看用户角色")
    public ResponseData getuserrole(Long uid){
        return new ResponseData(ExceptionMsg.SUCCESS,userService.listuserroles(uid));}
    @GetMapping("/admin/list")
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
    @GetMapping("/admin/userstatus")
    @ApiOperation("修改用户状态")
    public ResponseData banuser(@RequestParam Long uid,@RequestParam int type){
        int status = userService.userstatus(uid,1,"reson");
        switch (status) {
            case 1:
                return new ResponseData(ExceptionMsg.SUCCESS,"提交成功");
            case 2:
                return new ResponseData(ExceptionMsg.FAILED,"验证失败");
        }
        return new ResponseData(ExceptionMsg.ERROR,"提交失败");
    }

    @PostMapping("/admin/allocUserrole")
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
