package com.xsungroup.tms.matedata.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsungroup.tms.matedata.entity.CarOrgEntity;
import com.xsungroup.tms.matedata.mapper.CarOrgDao;
import com.xsungroup.tms.matedata.service.CarOrgService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 车辆企业关系表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2019-08-07
 */
@Service("carOrgService")
public class CarOrgServiceImpl extends ServiceImpl<CarOrgDao, CarOrgEntity> implements CarOrgService {

}
