package com.xsungroup.tms.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.user.entity.CompanyEntity;
import com.xsungroup.tms.user.mapper.CompanyDao;
import com.xsungroup.tms.user.service.CompanyService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公司表 服务实现类
 * </p>
 *
 * @author Alex
 * @since 2019-07-23
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyDao, CompanyEntity> implements CompanyService {

    @Override
    public Object list(IPage<CompanyEntity> page, CompanyEntity companyEntity) {

        return null;
    }

    @Override
    public String addOrEdit(CompanyEntity companyEntity) throws BussException {

        return null;
    }

    @Override
    public CompanyEntity getById(CompanyEntity companyEntity) throws BussException {

        return null;
    }
}
