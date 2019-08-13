package com.xsungroup.tms.matedata.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.matedata.entity.TransportTypeEntity;

/**
 * <p>
 * 运输类型表 服务类
 * </p>
 *
 * @author Alex
 * @since 2019-07-15
 */
public interface TransportTypeService extends IService<TransportTypeEntity> {
    Object list(IPage<TransportTypeEntity> page,TransportTypeEntity transportTypeEntity);

    String addOrEdit(TransportTypeEntity transportTypeEntity) throws BussException;
}
