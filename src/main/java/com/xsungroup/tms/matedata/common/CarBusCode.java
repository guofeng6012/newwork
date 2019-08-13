package com.xsungroup.tms.matedata.common;

import com.xsungroup.tms.core.supper.IBusCode;

public enum CarBusCode implements IBusCode {

    SELECT_DATA(5101, "请选择数据！"),

    SELECT_NO_DATA(5102, "没有找到相关数据"),

    SELECT_CAR_CATE(5103, "请先选择车辆类型"),

    NOT_FIND_USER(5104, "没有找到登录用户的信息"),


    ;



    private int code;
    private String msg;


    CarBusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 方法描述: 枚举转换
     *
     */
    public static CarBusCode parseOf(int code) {
        for (CarBusCode item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }

    public static CarBusCode parseOf(String key) {
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
