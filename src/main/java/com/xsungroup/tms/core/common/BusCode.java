package com.xsungroup.tms.core.common;

import com.xsungroup.tms.core.supper.IBusCode;

/**
 * 请求响应码枚举
 * Created by lether on 2016/7/28.
 */
public enum BusCode implements IBusCode {

    /**
     * "1000","成功"
     */
    SUCCESS(1000,"成功"),
    /**
     * "1001","业务失败"
     */
    FAIL(1001,"业务失败"),

    /**
     * "1003","无效token"
     */
    TOKEN_INVALID(1002,"无效token"),

    /**
     * "1005","token丢失"
     */
    TOKEN_LOSE(1003,"token丢失"),

    /**
     * "1004","token过期"
     */
    TOKEN_EXPIRATION(1004,"token过期"),

    /**
     * "1002","权限不足"
     */
    PERMISSION_DENIED(1005,"权限不足"),


    /**
     * "1006","签名无效"
     */
    INVALID_SIGNATURE(1006,"签名无效"),

    /**
     * "1007","参数绑定失败"
     */
    PARAMETER_BIND_ERROR(1007,"参数绑定失败"),
    /**
     * "1008","参数格式错误"
     */
    PARAMETER_VALID_ERROR(1008,"参数验证失败"),


    /**
     * "1009","非法请求"
     */
    ILLEGAL(1009,"非法请求"),

    /**
     * "1100","重复提交"
     */
    REPEAT_SUBMIT(1100,"重复提交"),


    /**
     * "2001","无效的资源ID"
     */
    ILLEGALARGUMENT(2001,"无效的资源ID"),

    /**
     * "2002","资源被关联"
     */
    ASSOCIATIVE(2002,"资源被关联"),

    /**
     *  无效请求地址
     */
    NOT_FOUND(2003,"无效请求地址");


    private int code;
    private String msg;


    BusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 方法描述: 枚举转换
     *
     * @param code code
     * @return BusCode BusCode
     */
    public static BusCode parseOf(int code) {
        for (BusCode item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }

    public static BusCode parseOf(String key) {
        return BusCode.parseOf(Integer.parseInt(key));
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public String toString() {
        return "BusCode{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }}
