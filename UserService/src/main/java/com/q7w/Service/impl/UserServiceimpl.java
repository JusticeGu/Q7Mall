package com.q7w.Service.impl;

import com.q7w.DAO.UserDao;
import com.q7w.Entity.User;
import com.q7w.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

    @Override
    public User getUserByUsername(String username) {
        User user = userDao.findUserByUsername(username);
        return user;
    }

    @Override
    public User getUserByuid(Long uid) {
        User user = userDao.findUserById(uid);
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
        return 0;
    }

    @Override
    public boolean checkcode(String mail, String code) {
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
    public int login(String Usrname, String passwrd) {
        return 0;
    }
}
