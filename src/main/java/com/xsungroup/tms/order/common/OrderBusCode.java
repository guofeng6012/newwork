package com.xsungroup.tms.order.common;

import com.xsungroup.tms.core.supper.IBusCode;

/**
 * @author 梁建军
 * 创建日期： 2019/8/7
 * 创建时间： 13:37
 * @version 1.0
 * @since 1.0
 */
public enum OrderBusCode implements IBusCode {
    /**
     * 无效的ID
     */
    INVALID_ID(6100, "无效的ID"),
    /**
     * 商品分类类型不存在
     */
    CLASSIFICATION_TYPE_DOES_NOT_EXIST(6101, "商品分类类型不存在"),
    /**
     * 父类未找到
     */
    PARENT_CLASS_NOT_FOUND(6102, "父类未找到"),

    /**
     * 存在子分类，不能删除
     */
    EXISTENCE_SUBCLASS_NOT_DELETE(6103, "存在子分类，不能删除"),
    /**
     * 地址类型不存在
     */
    ADDRESS_TYPE_DOES_NOT_EXIST(6104, "地址类型不存在"),
    /**
     * 重量和体积不能同时为空
     */
    WEIGHT_VOLUME_CANNOT_BE_EMPTY_SAME_TIME(6105, "重量和体积不能同时为空"),
    /**
     * 缺少重量单位
     */
    LACK_OF_WEIGHT_UNITS(6106, "缺少重量单位"),
    /**
     * 缺少体积单位
     */
    LACK_OF_VOLUME_UNITS(6107, "缺少体积单位"),
    /**
     * 长宽高必须同时存在
     */
    LENGTH_WIDTH_HEIGHT_MUST_COEXIST(6108, "长宽高必须同时存在"),
    /**
     * 缺少商品长度显示单位
     */
    LACK_OF_COMMODITY_LENGTH_DISPLAY_UNITS(6109, "缺少商品长度显示单位"),
    /**
     * 重量和体积必须有一个
     */
    WEIGHT_VOLUME_MUST_HAVE_ONE(6110,"重量和体积必须有一个"),;



    private final int code;
    private final String msg;


    OrderBusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 方法描述: 枚举转换
     *
     * @param code code
     * @return BusCode BusCode
     */
    public static OrderBusCode parseOf(int code) {
        for (OrderBusCode item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }

    public static OrderBusCode parseOf(String key) {
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
