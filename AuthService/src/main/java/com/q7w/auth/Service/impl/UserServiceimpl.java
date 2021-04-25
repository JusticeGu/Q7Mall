package com.q7w.auth.Service.impl;


import com.q7w.auth.DAO.UserDao;
import com.q7w.auth.Entity.User;
import com.q7w.auth.Entity.Userpro;
import com.q7w.auth.Service.AuthService;
import com.q7w.auth.Service.UserCacheService;
import com.q7w.auth.Service.UserService;
import com.q7w.common.constant.AuthConstant;
import com.q7w.common.domain.UserDto;
import com.q7w.common.exception.GlobalException;
import com.q7w.common.result.ResponseData;
import com.q7w.common.util.Pbkdf2Sha256;
import com.q7w.common.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
    @Override
    public User getUserByUsername(String username) {
        User user = userDao.findUserByUsername(username);
        return user;
    }

    @Override
    public Page<User> listall(Pageable pageable) {
        return userDao.findAll(pageable);
    }

    @Override
    public User getUserByuid(Long uid) {
        User user = userDao.findUserById(uid);
        if (user==null){
            throw new GlobalException("805X08","用户不存在");
        }
        return user;
    }

    @Override
    public UserDto loadUserByUsername(String username) {
        User user = getUserByUsername(username);
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setClientId("123");
        userDto.setPassword(user.getPassword());
        userDto.setStatus(1);
        return userDto;
    }

    @Override
    public int register(String username, String password, String email, String authcode) {
        if (!checkcode(email, authcode)){return 3;}
        if (getUserByUsername(username)!=null){return 2;}
        User user = new User();
        user.setUsername(username);
        String salt = RandomUtil.generateLowerString(16);
        String pwd = Pbkdf2Sha256.encode(password,salt);
        user.setPassword(pwd);
        user.setEmail(email);
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        userDao.save(user);
        return 1;
    }

    @Override
    public int extendUserinfo(Userpro userpro) {
        userpro.setUid(getUserByuid(userpro.getUid()).getId());
        return 1;
    }

    @Override
    public int sendregmail(String username, String email) {
        userCacheService.setAuthCode(email, RandomUtil.generateDigitalString(6));
        return 1;
    }

    @Override
    public boolean checkcode(String mail, String code) {
        try{
        String dbcode = userCacheService.getAuthCode(mail);
        if (dbcode.equals(code)){
            userCacheService.delAuthCode(mail);
            return true;}
        return false;}catch (Exception e){
            return  false;
        }
    }

    @Override
    public int updatepassword(String email, String password, String authcode) {
        return 0;
    }

    @Override
    public User getCurrentUser() {
        return null;
    }

    @Override
    public ResponseData login(String Username, String password) {
        if(Username.equals(null)){
            throw new GlobalException("810:","用户名或密码不能为空！");
        }
        Map<String, String> params = new HashMap<>();
        params.put("client_id", AuthConstant.PORTAL_CLIENT_ID);
        params.put("client_secret","123456");
        params.put("grant_type","password");
        params.put("username",Username);
        params.put("password",password);
        return authService.getAccessToken(params);
    }

    @Override
    public String getcurrertusername() {
        return "test-sys";
    }
}
