package com.xsungroup.tms.matedata.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.matedata.entity.AuditTypeEntity;

/**
 * <p>
 * 审核方式表 服务类
 * </p>
 *
 * @author Alex
 * @since 2019-07-22
 */
public interface AuditTypeService extends IService<AuditTypeEntity> {

    Object list(IPage<AuditTypeEntity> page, AuditTypeEntity auditTypeEntity);

    String addOrEdit(AuditTypeEntity auditTypeEntity) throws BussException;

}
