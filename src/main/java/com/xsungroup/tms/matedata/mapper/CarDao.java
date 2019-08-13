package com.xsungroup.tms.matedata.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xsungroup.tms.matedata.entity.CarEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 车辆表 Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2019-08-06
 */
public interface CarDao extends BaseMapper<CarEntity> {

    void  editBatchData(@Param("list") List<CarEntity> list);

}
