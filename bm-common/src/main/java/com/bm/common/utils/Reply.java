package com.bm.common.utils;

import java.io.Serializable;

public class Reply<T> implements Serializable {
    private Boolean success;
    private Integer code;
    private String msg;
    private T data;
    public Reply(){}
    public static Reply create(){
        return new Reply();
    }
    public static Reply ok(){
        return new Reply().setSuccess(true).setMsg("成功");
    }
    public static Reply fail(){
        return new Reply().setSuccess(false).setMsg("失败");
    }

    public Boolean getSuccess() {
        return success;
    }

    public Reply setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public Reply setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Reply setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Reply<T> setData(T data) {
        this.data = data;
        return this;
    }
}
