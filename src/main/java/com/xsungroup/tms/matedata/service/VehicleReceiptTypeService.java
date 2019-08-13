package com.xsungroup.tms.matedata.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.matedata.entity.VehicleReceiptTypeEntity;

/**
 * <p>
 * 车辆接单类型表 服务类
 * </p>
 *
 * @author Alex
 * @since 2019-07-22
 */
public interface VehicleReceiptTypeService extends IService<VehicleReceiptTypeEntity> {


    Object list(IPage<VehicleReceiptTypeEntity> page, VehicleReceiptTypeEntity vehicleReceiptTypeEntity);

    String addOrEdit(VehicleReceiptTypeEntity vehicleReceiptTypeEntity) throws BussException;


}
