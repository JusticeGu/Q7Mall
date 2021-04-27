package com.q7w.common.jwt;



public class JWTToken  {

    private static final long serialVersionUID = 1L;

    // 加密后的 JWT token串
    private String token;

    private String userName;

    public JWTToken(String token) {
        this.token = token;
        this.userName = JwtUtils.getUsername(token);
    }

}