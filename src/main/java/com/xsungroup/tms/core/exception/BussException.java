package com.xsungroup.tms.core.exception;

import com.xsungroup.tms.core.common.BusCode;
import com.xsungroup.tms.core.supper.IBusCode;

/**
 * 基础业务异常
 * 主要自定义 状态码 的处理
 * Created by lether on 2016/12/8.
 */
public class BussException extends RuntimeException {

    private IBusCode code;
    private String msg;

    public BussException(IBusCode code) {
        super(code.getMsg());
        this.setCode(code);
        this.setMsg(code.getMsg());
    }

    public BussException(String e) {
        super(e);
        this.setCode(BusCode.FAIL);
        this.setMsg(e);
    }

    public BussException(String msg, Throwable e) {
        super(e);
        this.setCode(BusCode.FAIL);
        this.setMsg(msg);
    }


    public BussException(IBusCode code, String msg) {
        super(msg);
        this.setCode(code);
        this.setMsg(msg);
    }

    public IBusCode getCode() {
        return code;
    }

    public void setCode(IBusCode code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
