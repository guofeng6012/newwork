package com.xsungroup.tms.matedata.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.matedata.entity.UnitPkgEntity;

/**
 * <p>
 * 包装单位表 服务类
 * </p>
 *
 * @author Alex
 * @since 2019-07-19
 */
public interface UnitPkgService extends IService<UnitPkgEntity> {

    Object list(IPage<UnitPkgEntity> page, UnitPkgEntity unitPkgEntity);

    String addOrEdit(UnitPkgEntity unitMeasEntity) throws BussException;

}
