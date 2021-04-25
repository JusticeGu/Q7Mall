package com.q7w.Service;

import com.q7w.Entity.User;
import com.q7w.Entity.Userpro;
import com.q7w.common.domain.UserDto;
import com.q7w.common.result.ResponseData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    public User getCurrentUser();
    public ResponseData login (String Username,String password);
    public String getcurrertusername();
    Page<User> listall(Pageable pageable);
    public int extendUserinfo(Userpro userpro);
    public UserDto loadUserByUsername(String username);

}
