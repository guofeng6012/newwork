package com.xsungroup.tms.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xsungroup.tms.user.entity.DriverEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 司机表 Mapper 接口
 * </p>
 *
 * @author Alex
 * @since 2019-08-01
 */
public interface DriverDao extends BaseMapper<DriverEntity> {

    void  editBatchData(@Param("list") List<DriverEntity> list);

    void  inBoundByIds(@Param("list") List<DriverEntity> list);

    void  boundByIds(@Param("list") List<DriverEntity> list);

}
