package com.q7w.Service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.q7w.DAO.UserDao;
import com.q7w.Entity.Role;
import com.q7w.Entity.User;
import com.q7w.Entity.Userpro;
import com.q7w.Service.*;
import com.q7w.VO.Userinfo;
import com.q7w.common.constant.AuthConstant;
import com.q7w.common.domain.UserDto;

import com.q7w.common.exception.GlobalException;
import com.q7w.common.result.CommonResult;

import com.q7w.common.result.ResultCode;
import com.q7w.common.service.RedisService;
import com.q7w.common.util.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import cn.hutool.core.util.RandomUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xiaogu
 * @date 2021/4/6 22:07
 **/
@Service
public class UserServiceimpl implements UserService {
    @Value("${redis.key.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;
    @Autowired
    UserDao userDao;
    @Autowired
    UserCacheService userCacheService;
    @Autowired
    AuthService authService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    RoleService roleService;
    @Autowired
    RedisService redisService;
    @Autowired
    ResourceService resourceService;
    @Autowired
    UserProfileService upService;
    @Override
    public User getUserByUsername(String username) {
        User user = userDao.findUserByUsername(username);
        return user;
    }
    @Override
    public User getUserByuid(Long uid) {
        User user = userDao.findUserById(uid);
        if (user==null){
            throw new GlobalException("805X08","用户不存在");
        }
        //user.setUserpros(upService.getuserproinfo(uid));
        return user;
    }
    @Override
    public UserDto loadUserByUsername(String username) {
        User user = getUserByUsername(username);
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setStatus(1);
        userDto.setAccountNonExpired(user.isAccountNonExpired());
        userDto.setAccountNonLocked(user.isAccountNonLocked());
        userDto.setCredentialsNonExpired(user.isCredentialsNonExpired());
        List<Role> roleList = listuserroles(user.getId());
        userDto.setEnabled(user.isEnabled());
        if(CollUtil.isNotEmpty(roleList)){
            List<String> roleStrList = roleList.stream().map(item -> item.getId() + "_" + item.getName()).collect(Collectors.toList());
            userDto.setRoles(roleStrList);
        }
        return userDto;
    }

    @Override
    public Page<User> listall(Pageable pageable) {
        return userDao.findAll(pageable);
    }



    @Override
    public Userinfo getcurrentuserinfo() {
        Long uid = getcurrertuserid();
        Userinfo userinfodb = (Userinfo) redisService.get("ums:cu:"+uid);
        if (userinfodb!=null){
            return userinfodb;
        }else
        {
        Userinfo userinfo = new Userinfo();
        userinfo.setId(uid);
        userinfo.setNickname(getcurrertusername());
        userinfo.setRoles(RequestUtils.getRoleIds());
        userinfo.setPerms(resourceService.listPermissionURLsByUser(uid));
        userinfo.setAvatar(upService.getuserproinfo(uid).getPhoto());
        redisService.set("ums:cu:"+uid,userinfo,600);
        return userinfo;
        }
    }

    @Override
    public int register(@NotNull String username,@NotNull String password,@NotNull String email, String authcode) {
        checkcode(email, authcode);
        if (getUserByUsername(username)!=null){return 2;}
        User user = new User();
        user.setUsername(username);
        String salt = RandomUtil.randomString(16);
        //String pwd = Pbkdf2Sha256.encode(password,salt);
        String pwd = passwordEncoder.encode(password);
        user.setPassword(pwd);
        user.setEmail(email);
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        Date now= new Date();
        Long time = now.getTime();
        user.setCreateBy("1");
        user.setCreateTime(time);
        user.setLastmodifiedBy("1");
        user.setUpdateTime(time);
        userDao.save(user);
        return 1;
    }
    @Override
    public int updateuser(User user) {
        userDao.save(user);
        upService.modify(user.getUserpros());
        return 1;
    }
    @Override
    public int extendUserinfo(Userpro userpro) {
        int status = upService.initUserPro(userpro);
        return status;
    }

    @Override
    public int sendregmail(String username, @NotNull String email) {
        String dbcode = userCacheService.getAuthCode(email);
        if (dbcode!=null){
            return 1;
        }
        userCacheService.setAuthCode(email,RandomUtil.randomNumbers(6));
        return 1;
    }

    @Override
    public boolean checkcode(@NotNull String mail, @NotNull String code) {
        try{
        String dbcode = userCacheService.getAuthCode(mail);
        if (dbcode.equals(code)){
            userCacheService.delAuthCode(mail);
            return true;}
        }catch (Exception e){
            throw new GlobalException("autherror","验证码核验失败，请重试");
        }
        return false;
    }

    @Override
    public int updatepassword(String email, String password, String authcode) {
        checkcode(email,authcode);

        return 0;
    }

    @Override
    public int resetpwd(Long uid) {
        User user = getUserByuid(uid);
        String pwd = passwordEncoder.encode("123456");
        user.setPassword(pwd);
        Date now= new Date();
        Long time = now.getTime();
        user.setCreateBy(getcurrertusername());
        user.setCreateTime(time);
        user.setLastmodifiedBy(getcurrertusername());
        user.setUpdateTime(time);
        userDao.save(user);
        return 1;
    }

    public boolean checkpwd(Long uid, String password){
        User user = getUserByuid(uid);
        if (user==null){throw new GlobalException("usererror","用户不存在");}
        if(!user.getPassword().equals(passwordEncoder.encode(password))){throw new GlobalException("usererror","密码错误！");}
        return true;
    }
    @Override
    public User getCurrentUser()  {
        return getUserByuid(getcurrertuserid());
    }
    @Override
    public String getcurrertusername() { return RequestUtils.getUsername(); }

    @Override
    public Long getcurrertuserid() {
//            String userStr = request.getHeader(AuthConstant.USER_TOKEN_HEADER);
//            Payload userDto = JSONUtil.toBean(userStr, Payload.class);
//            return userDto.getId();
            return RequestUtils.getUserId();

    }

    @Override
    public int allocrole(Long uid, List<Long> rids) {
        roleService.allocuserrole(uid,rids);
        return 1;
    }

    @Override
    public List<Role> listuserroles(Long uid) {
        return roleService.listRolesByUser(uid);
    }

    @Override
    public CommonResult login(String Username, String password) throws ParseException {
        if(StrUtil.isEmpty(Username)||StrUtil.isEmpty(password)){
            throw new GlobalException("810:","用户名或密码不能为空！");
        }
        User user = getUserByUsername(Username);
        if (user==null){
            throw new GlobalException("","用户名或密码错误");
        }
        Map<String, String> params = new HashMap<>();
        params.put("client_id", AuthConstant.ADMIN_CLIENT_ID);
        params.put("client_secret","123456");
        params.put("grant_type","password");
        params.put("username",Username);
        params.put("password",password);
        CommonResult restResult =  authService.getAccessToken(params);
        //if (result.get("code").equals("200")){
        if (ResultCode.SUCCESS.getCode()==restResult.getRspCode()&&restResult.getData()!=null){
            return restResult;
        }
        return restResult;
    }

    @Override
    public CommonResult renewtoken(String token) {
        Map<String, String> params = new HashMap<>();
        params.put("client_id", AuthConstant.ADMIN_CLIENT_ID);
        params.put("client_secret","123456");
        params.put("grant_type","refresh_token");
        params.put("refresh_token",token);
        return authService.getAccessToken(params);
    }

    @Override
    public int userstatus(Long uid,int status,String reson) {
        User user = getUserByuid(uid);
        String jti = (String) redisService.get(AuthConstant.User_Auth_KEY+uid);
        redisService.set(AuthConstant.User_Black_Table_KEY+":"+jti,"1",3600);
        switch (status) {
            case 1:
                user.setAccountNonLocked(false);
            case 2:
                user.setCredentialsNonExpired(false);
            case 3:
                user.setAccountNonExpired(false);
            case 4:
                user.setEnabled(false);
            case 5:
                user.setAccountNonLocked(true);
                user.setCredentialsNonExpired(true);
                user.setAccountNonExpired(true);
                user.setEnabled(true);
                redisService.del(AuthConstant.User_Black_Table_KEY+":"+jti);
        }
        userDao.save(user);
        return 1;
    }

    @Override
    public int deluser(Long id) {
        userDao.deleteById(id);
        return 1;
    }
}

