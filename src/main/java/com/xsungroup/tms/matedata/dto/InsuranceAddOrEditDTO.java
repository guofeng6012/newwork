package com.xsungroup.tms.matedata.dto;

import com.xsungroup.tms.core.common.modelmapper.Convert;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class InsuranceAddOrEditDTO extends Convert {

    /**
     * 险种主键
     **/
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
     * 新增子险种
     **/
    private List<InsuranceDTO> insList;

}
