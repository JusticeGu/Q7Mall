package com.q7w.common.exception;

import com.q7w.common.result.ExceptionMsg;

public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = -6543484989095940852L;
    private final String errorCode;
    private final String errorMsg;

    public BusinessException(ExceptionMsg resultCode) {
        super(resultCode.getMsg());
        this.errorCode = resultCode.getCode();
        this.errorMsg = resultCode.getMsg();
    }

    public BusinessException(ExceptionMsg resultCode, Throwable throwable) {
        super(resultCode.getMsg(), throwable);
        this.errorCode = resultCode.getCode();
        this.errorMsg = resultCode.getMsg();
    }

    public BusinessException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BusinessException(String errorCode, String errorMsg, Throwable throwable) {
        super(errorMsg, throwable);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getResultCode() {
        return this.errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }
}
