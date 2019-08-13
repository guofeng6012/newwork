package com.xsungroup.tms.matedata.dto;

import com.xsungroup.tms.core.common.modelmapper.Convert;
import lombok.Data;

import java.io.Serializable;

@Data
public class CarOrgDto extends Convert  implements Serializable {

    private String carId;
    /**
     * 外协等级
     */
    private Integer level;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 更新人
     */
    private String updateUser;
}
