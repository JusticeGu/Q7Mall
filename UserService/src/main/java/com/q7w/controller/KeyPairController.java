package com.q7w.controller;

import cn.hutool.json.JSONUtil;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.q7w.DTO.Payload;
import com.q7w.common.domain.UserDto;
import com.q7w.common.exception.GlobalException;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import com.q7w.domain.Oauth2TokenDto;
import com.q7w.domain.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;

import java.util.Date;
import java.util.Map;

/**
 * 获取RSA公钥接口
 * Created by macro on 2020/6/19.
 */
@RestController
public class KeyPairController {

    @Autowired
    private KeyPair keyPair;

    @GetMapping("/rsa/publicKey")
    public Map<String, Object> getKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }
    @GetMapping("/verify")
    public ResponseData verify(String token) throws ParseException, JOSEException {
        JWSObject jwsObject = JWSObject.parse(token);
        RSAKey rsaKey =  getKey_internal();
        RSAKey publicRsaKey = rsaKey.toPublicJWK();
        //使用RSA公钥创建RSA验证器
        JWSVerifier jwsVerifier = new RSASSAVerifier(publicRsaKey);
        if (!jwsObject.verify(jwsVerifier)) {
            throw new GlobalException("800-1","token签名不合法！");
        }
        String payload = jwsObject.getPayload().toString();
        Payload payloadDto = JSONUtil.toBean(payload, Payload.class);
        Long time = new Date().getTime();
        if (payloadDto.getExp() < time/1000) {
            throw new GlobalException("800-2","token签名已过期！");
        }
        return new ResponseData(ExceptionMsg.SUCCESS,"token合法有效");
    }
    public RSAKey getKey_internal() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return key;
    }
}
