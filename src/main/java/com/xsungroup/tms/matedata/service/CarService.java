package com.xsungroup.tms.matedata.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xsungroup.tms.matedata.dto.BoundDriverDto;
import com.xsungroup.tms.matedata.dto.CarDriverDto;
import com.xsungroup.tms.matedata.dto.CarDto;
import com.xsungroup.tms.matedata.dto.CarOrgDto;
import com.xsungroup.tms.matedata.entity.CarEntity;
import com.xsungroup.tms.matedata.entity.VehicleCateEntity;
import com.xsungroup.tms.user.dto.AuditRecordDto;

import java.util.List;

/**
 * <p>
 * 车辆表 服务类
 * </p>
 *
 * @author admin
 * @since 2019-08-06
 */
public interface CarService extends IService<CarEntity> {

    boolean addCar (CarDto carDto);

    boolean deleBatchIds (List<String> carIds);

    boolean editCar (String userId,CarDto carDto);

    List<VehicleCateEntity> vehicleCate (String vehicleCate);

    boolean outSourceCar (String userId,CarOrgDto carOrgDto);

    QueryWrapper<CarEntity> outSourceList (String userId, String carNo, String carType, String carSort);

    boolean addCheck (CarDto carDto);

    boolean boundDriver (BoundDriverDto boundDriverDto);

    boolean cancelBoundDriver (CarDriverDto carDriverDto);

    boolean isDefaultDriver (BoundDriverDto boundDriverDto);

    boolean cancleAssociation (String orgId,List<String> list);

    /**
     * 以下为审核接口
     */
    boolean auditRefuse(AuditRecordDto auditRecordDto);

    boolean auditSuccess(CarDto c);

    boolean auditCancel(AuditRecordDto auditRecordDto);
}
