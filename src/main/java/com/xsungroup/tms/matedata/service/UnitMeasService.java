package com.xsungroup.tms.matedata.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.matedata.entity.UnitMeasEntity;

/**
 * <p>
 * 计量单位表 服务类
 * </p>
 *
 * @author Alex
 * @since 2019-07-19
 */
public interface UnitMeasService extends IService<UnitMeasEntity> {

    Object list(IPage<UnitMeasEntity> page, UnitMeasEntity unitMeasEntity);

    String addOrEdit(UnitMeasEntity unitMeasEntity) throws BussException;


}
