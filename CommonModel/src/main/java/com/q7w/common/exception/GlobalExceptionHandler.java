package com.q7w.common.exception;

import com.alibaba.fastjson.JSONObject;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 * @author xiaogu
 * @date 2021/4/23 15:05
 **/
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private final static Logger Logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理请求方式(get,post,delete,put等)错误的异常
     */
    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseData handlerExp(HttpRequestMethodNotSupportedException e){
        return new ResponseData(ExceptionMsg.BADERROR,"暂不支持您当前的请求方式！");
    }
    /**
     * 处理请求方式(get,post,delete,put等)错误的异常
     */
    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseData handlerNull(NullPointerException e){
        return new ResponseData(ExceptionMsg.ParamError,"空指针异常！");
    }
    /**
     * 处理请求URL缺少必要参数的异常
     */
    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public ResponseData handlerExp(MissingServletRequestParameterException e){
        return new ResponseData(ExceptionMsg.ParamError,"请求参数异常");
    }

    /**
     * 处理对象传参非空校验异常
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseData handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        List errors = exception.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());
        return new ResponseData(ExceptionMsg.ParamError, JSONObject.toJSONString(errors));
    }

    /**
     * 处理自定义异常
     * @param ex
     * @return
     */
    @ExceptionHandler(GlobalException.class)
    public ResponseData bizException(GlobalException ex) {
        Logger.error("### 业务异常: {}", ex.getErrorMsg()+":"+ex.getMessage());
        ex.printStackTrace();
        return new ResponseData(ExceptionMsg.EXCEPT,ex.getResultCode()+":"+ex.getErrorMsg());
    }

    /**
     * 处理Exception
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseData unknownException(Exception ex) {
        ex.printStackTrace();
        Throwable cause = ex.getCause();
        String msg;
        if (cause != null) {
            msg = cause.getMessage();
        } else {
            msg = ex.getMessage();
        }
        Logger.error("### 异常捕获: {}", msg);
        return new ResponseData(ExceptionMsg.ERROR,msg);
    }

}
