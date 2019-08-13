package com.xsungroup.tms.matedata.dto;

import com.xsungroup.tms.core.common.modelmapper.Convert;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class InsuranceSelectDTO extends Convert {

    private String insuranceCode;

    private String insuranceName;

    private Integer pageSize;

    private Integer pageNum;
}
