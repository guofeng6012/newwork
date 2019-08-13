package com.xsungroup.tms.order.common;

/**
 * @author 梁建军
 * 创建日期： 2019/8/5
 * 创建时间： 16:09
 * @version 1.0
 * @since 1.0
 */
public enum GoodsCategoryType {
    /**
     * 普通货物
     */
    COMMON(1, "普通货物"),
    /**
     * 冷链货物
     */
    COLD_CHAIN(2, "冷链货物"),
    /**
     * 普通/冷链
     */
    COMMON_COLD_CHAIN(3, "普通/冷链"),
    /**
     * 危险品
     */
    DANGEROUS_GOODS(4, "危险品");

    private final int type;
    private final String name;

    GoodsCategoryType(int type, String name) {

        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static GoodsCategoryType get(int type) {
        for (GoodsCategoryType value : values()) {
            if (value.type == type) {
                return value;
            }
        }
        return null;
    }

}
