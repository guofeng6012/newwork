package com.xsungroup.tms.core.exception;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Spring Validation 返回信息接收对象
 */


@Getter
@Setter
@ToString
public class ArgumentInvalidResult {

    /**
     * 字段名
     */
    private String field;
    /**
     * 值
     */
    private Object value;

    /**
     * 默认错误信息
     */
    private String message;

}
