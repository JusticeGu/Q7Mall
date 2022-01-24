package com.q7w.common.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class ResponseData extends Response implements Serializable {
    private static final long serialVersionUID = 3033545151355633270L;
    private Object data;

    public  ResponseData(Object data) {
        this.data = data;
    }

    public  ResponseData(ExceptionMsg msg) {
        super(msg);
    }

    public  ResponseData(String rspCode, String rspMsg) {
        super(rspCode, rspMsg);
    }

    public ResponseData(String rspCode, String rspMsg, Object data) {
        super(rspCode, rspMsg);
        this.data = data;
    }

    public ResponseData(ExceptionMsg msg, Object data) {
        super(msg);
        this.data = data;
    }

    public  Object getData() {
        return data;
    }

    public  void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "data=" + data +
                "} " + super.toString();
    }
}

/*return new ResponseData(ExceptionMsg.SUCCESS,"你好");*/
