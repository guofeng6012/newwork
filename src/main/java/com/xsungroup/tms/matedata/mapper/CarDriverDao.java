package com.xsungroup.tms.matedata.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xsungroup.tms.matedata.entity.CarDriverEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 车辆与司机关系表 Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2019-08-07
 */
public interface CarDriverDao extends BaseMapper<CarDriverEntity> {

    void  addBatchData(@Param("list") List<CarDriverEntity> list);

}
