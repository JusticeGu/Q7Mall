package com.q7mall.common.exception;

import com.q7mall.common.result.ResponseData;
import com.q7mall.common.result.ExceptionMsg;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Evan
 * @date 2019/11
 */
@ControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public ResponseData handleAuthorizationException(UnauthorizedException e) {
        String message = "权限认证失败,请联系管理员确认权限";
        return new ResponseData(ExceptionMsg.FAILED_403,message);
    }
}
