package com.xsungroup.tms.matedata.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.matedata.entity.VehicleCateEntity;

/**
 * <p>
 * 车辆分类表 服务类
 * </p>
 *
 * @author Alex
 * @since 2019-07-19
 */
public interface VehicleCateService extends IService<VehicleCateEntity> {

    Object list(IPage<VehicleCateEntity> page, VehicleCateEntity vehicleCateEntity);

    String addOrEdit(VehicleCateEntity vehicleCateEntity) throws BussException;


}
