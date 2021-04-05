package com.q7mall.common.result;

public enum ExceptionMsg {
    SUCCESS("200", "操作成功"),
    SUCCESS_GET("200", "数据获取成功DB"),
    SUCCESS_RGET("200", "数据获取成功"),
    SUCCESS_LOGIN("200", "登录成功"),
    FAILED("801","操作失败"),
    ERROR("501","服务暂不可用"),
    SE_error("502","保护机制拦截"),
    ParamError("802", "参数错误！"),
    FileError("803","文件上传失败"),
    FAILED_403("403","禁止访问！您暂时没有此接口的访问权限，请联系管理员"),;
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