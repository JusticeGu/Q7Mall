package com.q7w.Service;

import com.nimbusds.jose.JOSEException;
import com.q7w.Entity.Role;
import com.q7w.Entity.User;
import com.q7w.Entity.Userpro;
import com.q7w.common.domain.UserDto;
import com.q7w.common.result.ResponseData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/6 19:48
 **/
public interface UserService {
    public User getUserByUsername(String username);
    public User getUserByuid(Long uid);
    public int register(String username,String password,String email,String authcode);
    public int sendregmail(String username,String email);
    public boolean checkcode(String mail,String code);
    public int updatepassword(String email,String password,String authcode);
    public User getCurrentUser() throws ParseException, JOSEException;
    public String getcurrertusername();
    public ResponseData login (String Username,String password);
    Page<User> listall(Pageable pageable);
    public int extendUserinfo(Userpro userpro);
    public UserDto loadUserByUsername(String username);
    public int allocrole(Long uid, List<Long> rids);
    public List<Role> listuserroles(Long uid);
    public int banuser(Long uid,int reson);

}
