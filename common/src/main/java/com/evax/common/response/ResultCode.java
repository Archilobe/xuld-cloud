package com.evax.common.response;

/**
 * 状态码
 *
 * @author Lionel
 */
public enum ResultCode {
    /**
     * 请求返回状态码和说明信息
     */
    SUCCESS(20000, "成功"),
    EXIST(20100, "已存在"),
    NOT_EXIST(20400, "无数据"),
    BAD_REQUEST(40000, "参数或者语法不对"),
    UNAUTHORIZED(40100, "认证失败"),
    TOKEN_EXPIRED(40101, "token无效，请重新登录"),
    UNBELIEVABLE(40102, "JWT签名与本地签名不匹配"),
    LOGIN_USERNAME_ERROR(40103, "用户不存在"),
    LOGIN_PASSWORD_ERROR(40104, "密码无效"),
    ACCOUNT_LOCKE(40104, "账号已锁定"),
    FORBIDDEN(40300, "无权限"),
    NOT_FOUND(40400, "请求的资源不存在"),
    OPERATE_ERROR(40500, "操作失败"),
    TIME_OUT(40800, "请求超时"),
    SERVER_ERROR(50000, "服务器内部错误"),
    GATEWAY_ERROR(60000, "网关错误"),
    OTHER(70000, "其他");


    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
