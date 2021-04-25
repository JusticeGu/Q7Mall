package com.q7w.auth.Service;

import com.q7w.auth.Entity.User;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/6 19:48
 **/
public interface AdminService {
    public List<User> listall();
    public List<User> findbyusername(String username);
    public List<User> findbyemail(String email);
    public List<User> findbyid(Long uid);
    public int createuser(User user);
    public int updateuserinfo(User user);
    public int adminlogin(String username,String password);
    public int usertoadmintoken();
    public int satuser(Long uid);
    public int resetpwd(Long uid);

}
