package com.q7w.Service.impl;

import com.q7w.DAO.UserProDao;
import com.q7w.Entity.Userpro;
import com.q7w.Service.UserProfileService;
import com.q7w.Service.UserService;
import com.q7w.common.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/29 13:21
 **/
@Service
public class UserProfileServiceimpl implements UserProfileService {
    @Autowired
    UserProDao userProDao;
    @Autowired
    UserService userService;
    @Override
    public int initUserPro(Userpro userpro) {
        if(userProDao.findByUid(userpro.getUid())!=null){throw new GlobalException("011","用户信息已初始化，请尝试修改信息");
        }
        Date now= new Date();
        Long time = now.getTime();
        userpro.setCreateBy(userService.getcurrertusername());
        userpro.setCreateTime(time);
        userpro.setLastmodifiedBy(userService.getcurrertusername());
        userpro.setUpdateTime(time);
        userProDao.save(userpro);
        return 1;
    }

    @Override
    public int modify(Userpro userpro) {
        if(userProDao.findByUid(userpro.getUid())==null){throw new GlobalException("012","用户信息未初始化，请先进行初始化");
        }
        Date now= new Date();
        Long time = now.getTime();
        userpro.setCreateBy(userService.getcurrertusername());
        userpro.setCreateTime(time);
        userpro.setLastmodifiedBy(userService.getcurrertusername());
        userpro.setUpdateTime(time);
        userProDao.save(userpro);
        return 1;
    }

    @Override
    public Userpro getuserproinfo(Long uid) {
        if(userProDao.findByUid(uid)==null){
            throw new GlobalException("012","用户信息未初始化，请先进行初始化");
        }
        return userProDao.findByUid(uid);
    }

    @Override
    public List<Userpro> queryuser(String name) {
        List<Userpro> userpros =  userProDao.findAllByRealnameLike(name);
        return userpros;
    }
}
