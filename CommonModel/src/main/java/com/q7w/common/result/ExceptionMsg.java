package com.q7w.common.result;

public enum ExceptionMsg {
    SUCCESS("200", "操作成功"),
    SUCCESS_LOGIN("200", "登录成功"),
    FAILED_403("403","资源禁止访问"),
    Unauthorized("401","验证失败"),
    FAILED("801","操作失败"),
    ParamError("802", "参数错误！"),
    BADERROR("803", "请求异常！"),
    FileError("804","文件上传失败"),
    EXCEPT("805","操作异常，请确认后重试"),
    ERROR("500","系统异常!"),
    ServiceERROR("501","服务暂不可用"),
    SE_error("502","保护机制拦截"),
    ContextError("503","内容审核机制"),;
    private ExceptionMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }


}