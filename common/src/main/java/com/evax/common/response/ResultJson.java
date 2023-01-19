package com.evax.common.response;


import lombok.Data;

import java.io.Serializable;

/**
 * REST API 返回类型
 *
 * @author Lionel
 */
@Data
public class ResultJson<T> implements Serializable {

    private static final long serialVersionUID = 783015033603078674L;
    private int code;
    private String msg;
    private T data;

    public ResultJson() {
    }

    public static <T> ResultJson<T> ok() {
        return ok(null);
    }

    public static <T> ResultJson<T> ok(T o) {
        return ok(ResultCode.SUCCESS, o);
    }

    public static <T> ResultJson<T> ok(ResultCode resultCode, T o) {
        return ok(resultCode, o, resultCode.getMsg());
    }

    public static <T> ResultJson<T> ok(ResultCode resultCode, T o, String msg) {
        return new ResultJson(resultCode, o, msg);
    }


    public static <T> ResultJson<T> failure(ResultCode code) {
        return failure(code, code.getMsg());
    }

    public static <T> ResultJson<T> failure(ResultCode code, String msg) {
        return new ResultJson(code, null, msg);
    }

    private ResultJson(ResultCode resultCode, T data, String msg) {
        this.data = data;
        this.code = resultCode.getCode();
        this.msg = msg;
    }
}

