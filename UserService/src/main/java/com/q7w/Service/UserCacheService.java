package com.q7w.Service;

/**
 * @author xiaogu
 * @date 2021/4/8 20:02
 **/
public interface UserCacheService {

    /**
     * 设置验证码
     */
    void setAuthCode(String email, String authCode);

    /**
     * 获取验证码
     */
    String getAuthCode(String email);

    void delAuthCode(String email);
}
