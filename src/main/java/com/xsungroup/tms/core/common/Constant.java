package com.xsungroup.tms.core.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface Constant {


    @Getter
    @AllArgsConstructor
    enum AbleEnum{

        YES(1,"有效"),
        NO(0,"无效");

        private final Integer value;

        private final String comment;

    }

    @Getter
    @AllArgsConstructor
    enum Status{

        ENABLE(1,"启用"),
        DISABLED(0,"禁用");

        private final Integer value;

        private final String comment;

    }


}
