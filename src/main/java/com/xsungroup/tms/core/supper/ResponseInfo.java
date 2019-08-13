package com.xsungroup.tms.core.supper;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 信息返回实体封装
 * @author: kingJing 
 * @Date: 2019/7/8 14:54
 */
@Data
public class ResponseInfo<T> implements Serializable {

    private int code;

    private String message;

    private T data;
}
