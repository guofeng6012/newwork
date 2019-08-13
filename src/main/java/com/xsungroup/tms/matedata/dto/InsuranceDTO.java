package com.xsungroup.tms.matedata.dto;

import com.xsungroup.tms.core.common.modelmapper.Convert;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class InsuranceDTO extends Convert {

    private String insuranceId;

    /**
     * 险种分类编码
     **/
    private String insuranceCode;

    /**
     * 险种分类名称
     **/
    private String insuranceName;

    /**
     * 失效日期
     **/
    private String expiryDate;

    /**
     * 删除标记 0:已删除 1:未删除
     */
    private Integer isAble;
}

