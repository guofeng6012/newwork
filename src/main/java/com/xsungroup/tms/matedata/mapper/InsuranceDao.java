package com.xsungroup.tms.matedata.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xsungroup.tms.matedata.entity.InsuranceEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 险种大类表 Mapper 接口
 * </p>
 *
 * @author GF
 * @since 2019-07-25
 */
@Repository
public interface InsuranceDao extends BaseMapper<InsuranceEntity> {

    void addBatchData(List<InsuranceEntity> list);

    int  editBatchData(List<InsuranceEntity> list);
}
