package com.xsungroup.tms.matedata.dto;

import com.xsungroup.tms.core.common.modelmapper.Convert;
import lombok.Data;

@Data
public class CarDriverDto extends Convert {

    private String carId;

    private String driverId;
    /**
     * 司机名字
     */
    private String driverName;
    /**
     * 是否默认创建组织 0:否 1:是
     */
    private Integer isDefault;

}
