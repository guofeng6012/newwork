package com.xsungroup.tms.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsungroup.tms.user.entity.AuditRecordEntity;
import com.xsungroup.tms.user.mapper.AuditRecordDao;
import com.xsungroup.tms.user.service.AuditRecordService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 审核记录表 服务实现类
 * </p>
 *
 * @author Alex
 * @since 2019-08-08
 */
@Service("auditRecordService")
public class AuditRecordServiceImpl extends ServiceImpl<AuditRecordDao, AuditRecordEntity> implements AuditRecordService {

}
