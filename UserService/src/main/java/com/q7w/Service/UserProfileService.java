package com.q7w.Service;

import com.q7w.Entity.User;
import com.q7w.Entity.Userpro;

import java.util.List;

/**
 * 用户扩展信息维护
 * @author xiaogu
 * @date 2021/4/29 13:20
 **/
public interface UserProfileService {
    public int initUserPro(Userpro userpro);
    public int modify(Userpro userpro);
    public Userpro getuserproinfo(Long uid);
    public List<Userpro> queryuser(String name);
}
