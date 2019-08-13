package com.xsungroup.tms.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xsungroup.tms.matedata.entity.TradeLabelBEntity;
import com.xsungroup.tms.user.entity.UserOrgEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户与组织关系 Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2019-08-03
 */
@Repository
public interface UserOrgDao extends BaseMapper<UserOrgEntity> {

    void  addBatchData(@Param("list") List<UserOrgEntity> list);

}
