package com.xsungroup.tms.matedata.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsungroup.tms.matedata.entity.CarDriverEntity;
import com.xsungroup.tms.matedata.mapper.CarDriverDao;
import com.xsungroup.tms.matedata.service.CarDriverService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 车辆与司机关系表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2019-08-07
 */
@Service("carDriverService")
public class CarDriverServiceImpl extends ServiceImpl<CarDriverDao, CarDriverEntity> implements CarDriverService {

}
