package com.q7w.Service.impl;

import cn.hutool.core.util.StrUtil;
import com.q7w.DAO.UserDao;
import com.q7w.Entity.User;
import com.q7w.Service.AuthService;
import com.q7w.Service.UserCacheService;
import com.q7w.Service.UserService;
import com.q7w.common.constant.AuthConstant;
import com.q7w.common.exception.GlobalException;
import com.q7w.common.result.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import cn.hutool.core.util.RandomUtil;

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
    public User getUserByuid(Long uid) {
        User user = userDao.findUserById(uid);
        if (user==null){
            throw new GlobalException("805X08","用户不存在");
        }
        return user;
    }

    @Override
    public int register(String username, String password, String email, String authcode) {
        if (getUserByUsername(username)!=null){return 2;}
        if (!checkcode(email, authcode)){return 3;}
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        return 1;
    }

    @Override
    public int sendregmail(String username, String email) {
        userCacheService.setAuthCode(email,RandomUtil.randomNumbers(6));
        return 1;
    }

    @Override
    public boolean checkcode(String mail, String code) {
        String dbcode = userCacheService.getAuthCode(mail);
        if (dbcode.equals(code)){
            userCacheService.delAuthCode(mail);
            return true;}
        return false;
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
        if(StrUtil.isEmpty(Username)||StrUtil.isEmpty(password)){
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
}
