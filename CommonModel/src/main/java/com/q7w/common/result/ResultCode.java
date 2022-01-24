package com.q7w.common.result;

/**
 * 枚举了一些常用API操作码
 * Created by macro on 2019/4/19.
 */
public enum ResultCode implements IErrorCode {
    SUCCESS("200", "操作成功"),
    FAILED("800", "Error:操作失败"),
    Error("500", "内部错误"),
    GError("501", "网关错误"),
    VALIDATE_FAILED("804", "参数检验失败"),
    UNAUTHORIZED("401", "登录状态错误"),
    FORBIDDEN("403", "权限不足"),
    NOMATCH("404", "资源错误");
    private String rspCode;
    private String rspMsg;

    private ResultCode(String rspCode, String rspMsg) {
        this.rspCode = rspCode;
        this.rspMsg = rspMsg;
    }

    public String getCode() {
        return rspCode;
    }

    public String getMessage() {
        return rspMsg;
    }
}
