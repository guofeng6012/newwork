package com.xsungroup.tms.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xsungroup.tms.user.entity.AuditRecordEntity;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 审核记录表 Mapper 接口
 * </p>
 *
 * @author Alex
 * @since 2019-08-08
 */
@Repository
public interface AuditRecordDao extends BaseMapper<AuditRecordEntity> {

}
