package com.xiaoshan.common;

/**
 * Created by gukc007 on 2017-05-08.
 */
public class ResponseBase {

    private String code;

    private Object data;

    public ResponseBase(String code, Object data) {
        this.code = code;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
