package com.xsungroup.tms.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xsungroup.tms.user.dto.AuditRecordDto;
import com.xsungroup.tms.user.dto.DriverAcceptOrderDto;
import com.xsungroup.tms.user.dto.DriverDto;
import com.xsungroup.tms.user.entity.DriverEntity;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 司机表 服务类
 * </p>
 *
 * @author Alex
 * @since 2019-08-01
 */
public interface DriverService extends IService<DriverEntity> {

    boolean addDriver (String userId, DriverDto driverDto);

    boolean editDriver (String userId,DriverDto driverDto);

    Object list(IPage<DriverEntity> page, DriverDto driverDto);

    boolean deleBatchIds(List<String> list);

    Map<String,Object> checkIsExist(DriverDto driverDto);

    boolean unBound(DriverAcceptOrderDto acceptOrderDto);

    boolean bound(DriverAcceptOrderDto acceptOrderDto);

    boolean acceptOrder(String unbindReason);

    boolean auditRefuse(AuditRecordDto auditRecordDto);

    boolean auditSuccess(DriverDto driverDto);

    boolean auditCancel(AuditRecordDto auditRecordDto);
}
