package com.q7w.auth.exception;


import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局处理Oauth2抛出的异常
 * Created by macro on 2020/7/17.
 */
@ControllerAdvice
public class Oauth2ExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = OAuth2Exception.class)
    public ResponseData handleOauth2(OAuth2Exception e) {
        return new ResponseData(ExceptionMsg.EXCEPT,e.getMessage());
    }
}
