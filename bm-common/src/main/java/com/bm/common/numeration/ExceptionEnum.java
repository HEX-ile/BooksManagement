package com.bm.common.numeration;

import lombok.Getter;

@Getter
public enum ExceptionEnum {

    /**
     * 找不到请求路径
     */
    ERROR_NO_FOUND(404, "找不到请求路径"),
    /**
     * 请求无效，参数错误
     */
    ERROR_MISS_PARAM(400, "请求无效，参数错误"),

    /**
     * 服务器错误
     */
    ERROR_INTERNAL_SERVER(500, "服务器错误"),

    /**
     * 未登录
     */
    ERROR_NOLOGIN(10000, "未登录"),
    /**
     * 数据库操作异常
     */
    ERROR_SQL(10001, "数据库操作异常"),
    /**
     * 参数为空
     */
    ERROR_NO_FOUND_DATA(10002, "无该记录"),
    /**
     * 参数为空
     */
    ERROR_EMPTY_PARAM(10003, "参数为空"),
    /**
     * 操作不允许
     */
    ERROR_NO_PERMIT(10004, "操作不允许"),
    /**
     * 无该权限
     */
    ERROR_NO_AUTH(10005, "无该权限");
    private Integer code;

    private String message;
    ExceptionEnum(int code, String message){
        this.code=code;
        this.message=message;
    }
}
