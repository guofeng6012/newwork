package com.xsungroup.tms.matedata.dto;

import com.xsungroup.tms.core.common.modelmapper.Convert;
import com.xsungroup.tms.user.dto.CarBoundDriverDto;
import lombok.Data;

import java.util.List;

@Data
public class BoundDriverDto extends Convert {

    private String carId;

    private List<CarBoundDriverDto> driverDtos;

}
