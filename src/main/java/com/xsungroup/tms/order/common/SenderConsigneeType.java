package com.xsungroup.tms.order.common;

/**
 * @author 梁建军
 * 创建日期： 2019/8/6
 * 创建时间： 15:50
 * @version 1.0
 * @since 1.0
 */
public enum SenderConsigneeType {
    /**
     * 收货人
     */
    RECEIVING_GOODS(1, "收货人"),
    /**
     * 发件人
     */
    DELIVER_GOODS(2, "发件人"),
    /**
     * 发件人收货人
     */
    SENDER_CONSIGNEE(3, "收/发件人");
    private final int type;
    private final String name;

    SenderConsigneeType(int type, String name) {

        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static SenderConsigneeType get(int type) {
        for (SenderConsigneeType value : values()) {
            if (value.type == type) {
                return value;
            }
        }
        return null;
    }
}
