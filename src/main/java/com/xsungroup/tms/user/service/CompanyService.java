package com.xsungroup.tms.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.user.entity.CompanyEntity;

/**
 * <p>
 * 公司表 服务类
 * </p>
 *
 * @author Alex
 * @since 2019-07-23
 */
public interface CompanyService extends IService<CompanyEntity> {


    Object list(IPage<CompanyEntity> page, CompanyEntity companyEntity);


    String addOrEdit(CompanyEntity companyEntity) throws BussException;


    CompanyEntity getById(CompanyEntity companyEntity) throws BussException;

}
