package com.xsungroup.tms.order.common;

import com.xsungroup.tms.core.exception.BussException;

/**
 * @author 梁建军
 * 创建日期： 2019/8/9
 * 创建时间： 10:16
 * @version 1.0
 * @since 1.0
 */
public interface CheckParameters {

    void checkRequest() throws BussException;
}

