package com.xsungroup.tms.user.common;

import com.xsungroup.tms.core.supper.IBusCode;

public enum DriverBusCode implements IBusCode {

    SELECT_DATA(4101, "请选择数据！"),

    SELECT_NO_DATA(4102, "没有找到相关数据"),

    ;



    private int code;
    private String msg;


    DriverBusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 方法描述: 枚举转换
     *
     */
    public static DriverBusCode parseOf(int code) {
        for (DriverBusCode item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }

    public static DriverBusCode parseOf(String key) {
        return parseOf(Integer.parseInt(key));
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
    }
}
