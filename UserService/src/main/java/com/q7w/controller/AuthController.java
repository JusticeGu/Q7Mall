package com.q7w.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSObject;
import com.q7w.DTO.Payload;
import com.q7w.Entity.OpenIdJson;
import com.q7w.Entity.User;
import com.q7w.common.constant.AuthConstant;
import com.q7w.common.result.CommonResult;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import com.q7w.common.service.RedisService;
import com.q7w.domain.Oauth2TokenDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.security.Principal;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * 自定义Oauth2获取令牌接口
 * Created by macro on 2020/7/17.
 */
@RestController
@Api(tags = "AuthController", description = "认证中心登录认证")
@RequestMapping("/oauth")
public class AuthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;
    @Autowired
    RedisService redisService;

    @ApiOperation("Oauth2获取token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grant_type", value = "授权模式", required = true),
            @ApiImplicitParam(name = "client_id", value = "Oauth2客户端ID", required = true),
            @ApiImplicitParam(name = "client_secret", value = "Oauth2客户端秘钥", required = true),
            @ApiImplicitParam(name = "refresh_token", value = "刷新token"),
            @ApiImplicitParam(name = "username", value = "登录用户名"),
            @ApiImplicitParam(name = "password", value = "登录密码"),
            @ApiImplicitParam(name = "id", value = "UID"),
    })
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public CommonResult<Oauth2TokenDto> postAccessToken(@ApiIgnore Principal principal, @ApiIgnore @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException, ParseException {
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead(AuthConstant.JWT_TOKEN_PREFIX).build();
        JWSObject jwsObject = JWSObject.parse(oauth2TokenDto.getToken());
        String userStr = jwsObject.getPayload().toString();
        Payload payload = JSONUtil.toBean(userStr, Payload.class);
        redisService.set(AuthConstant.User_Auth_KEY+":"+payload.getId(),payload.getJti(),3600*24);
        return CommonResult.success(oauth2TokenDto);
    }

    @GetMapping("/wx/getopenid")
    @ApiOperation("微信小程序获取OPENID接口")
    public String userLogin(@RequestParam("code") String code) throws IOException {
        String WXAPPID = "wxc4d82adbcb40d914";
        String WXSECRET = "a789fb8efbbc3aea7729d9c759de2568";
        String result = "";
        try{//请求微信服务器，用code换取openid。HttpUtil是工具类，后面会给出实现，Configure类是小程序配置信息，后面会给出代码
            result = HttpUtil.get("https://api.weixin.qq.com/sns/jscode2session?appid="
                    + WXAPPID + "&secret=" + WXSECRET + "&js_code="
                    + code + "&grant_type=authorization_code");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        OpenIdJson openIdJson = mapper.readValue(result,OpenIdJson.class);
        System.out.println(result.toString());
        System.out.println(openIdJson.getOpenid());
        return result;
    }
//    @PostMapping("api/wxbind")
//    @CrossOrigin
//    @ApiOperation("微信小程序用户绑定")
//    public ResponseData wxbind(@RequestBody User requestUser) {
//        String username = requestUser.getUsername();
//        String password = requestUser.getUser_password();
//        String openid = requestUser.getWxuid();
//        if(userService.wxisExist(openid)){
//            return new ResponseData(ExceptionMsg.Login_FAILED_1,"您已进行过绑定操作请勿重复操作");}
//        else{
//            Subject subject = SecurityUtils.getSubject();
//            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
//            try {
//                subject.login(usernamePasswordToken);
//                User userInDB = userDAO.findByUsername(username);
//                if(userInDB.getWxuid() == null){userInDB.setWxuid(openid);
//                    userDAO.save(userInDB);
//                    return new ResponseData(ExceptionMsg.SUCCESS_WXBIND,username);}
//                else {
//                    return new ResponseData(ExceptionMsg.Login_FAILED_1,"该用户已绑定微信，重置请联系管理员");}
//            }
//            catch (IncorrectCredentialsException e) {
//                return new ResponseData(ExceptionMsg.Login_FAILED_1,"密码错误");
//            } catch (UnknownAccountException e) {
//                return new ResponseData(ExceptionMsg.Login_FAILED_2,"用户不存在");
//            }
//
//        }}
//    @GetMapping("api/wxlogin")
//    @CrossOrigin
//    @ApiOperation("微信小程序登录接口")
//    public ResponseData wxlogin(String openid) {
//        try {
//            if(redisService.hasKey(openid)){ return new ResponseData(ExceptionMsg.SUCCESS_GETR,"登录状态正常");
//            }else if (userService.wxisExist(openid)&&userService.wxisable(openid)){
//                redisService.set(openid, 1, 43200);
//                return new ResponseData(ExceptionMsg.SUCCESS_GET,"登录状态正常");
//            } else{
//                return new ResponseData(ExceptionMsg.FAILED,"登录验证失败，请先绑定用户,或检查账户状态");
//            }
//        } catch (Exception e){
//            return new ResponseData(ExceptionMsg.FAILED,"后端错误");
//        }
//
//    }



}
