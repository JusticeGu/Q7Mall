package com.q7w.Service;

import com.nimbusds.jose.JOSEException;
import com.q7w.Entity.Role;
import com.q7w.Entity.User;
import com.q7w.Entity.Userpro;
import com.q7w.VO.Userinfo;
import com.q7w.common.domain.UserDto;
import com.q7w.common.result.CommonResult;
import com.q7w.common.result.ResponseData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author xiaogu
 * @date 2021/4/6 19:48
 **/
public interface UserService {
    public User getUserByUsername(String username);
    public User getUserByuid(Long uid);
    public int register(String username,String password,String email,String authcode);
    public int updateuser(User user);
    public int sendregmail(String username,String email);
    public int resetpwd(Long uid);
    public boolean checkcode(String mail,String code);
    public int updatepassword(String email,String password,String authcode);
    public User getCurrentUser();
    public Userinfo getcurrentuserinfo();
    public String getcurrertusername();
    public Long getcurrertuserid();
    public CommonResult login (String Username, String password) throws ParseException;
    public CommonResult renewtoken(String token);
    Page<User> listall(Pageable pageable);
    public int extendUserinfo(Userpro userpro);
    public UserDto loadUserByUsername(String username);
    public int allocrole(Long uid, List<Long> rids);
    public List<Role> listuserroles(Long uid);
    public int userstatus(Long uid,int status,String reson);
    public int deluser(Long id);


}
