package com.xsungroup.tms.user.dto;

import com.xsungroup.tms.core.common.modelmapper.Convert;
import lombok.Data;

@Data
public class CarBoundDriverDto extends Convert {

    private String driverId;
    private String driverName;

    private String mobile;

    private Integer isDefault;

}
