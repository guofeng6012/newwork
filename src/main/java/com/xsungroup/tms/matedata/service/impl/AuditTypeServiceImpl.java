package com.xsungroup.tms.matedata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.matedata.entity.AuditTypeEntity;
import com.xsungroup.tms.matedata.mapper.AuditTypeDao;
import com.xsungroup.tms.matedata.service.AuditTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 审核方式表 服务实现类
 * </p>
 *
 * @author Alex
 * @since 2019-07-22
 */
@Service
public class AuditTypeServiceImpl extends ServiceImpl<AuditTypeDao, AuditTypeEntity> implements AuditTypeService {


    @Autowired
    private AuditTypeDao auditTypeDao;

    @Override
    public Object list(IPage<AuditTypeEntity> page, AuditTypeEntity auditTypeEntity) {
        QueryWrapper<AuditTypeEntity> wrapper = new QueryWrapper<>();
//        wrapper.setEntity(EventNoticeTypeEntity);
        wrapper.eq("is_able", 1);//精确查询
        if (auditTypeEntity.getAuditTypeName() != null && auditTypeEntity.getAuditTypeName().length() > 0) {
            wrapper.like("audit_type_name", auditTypeEntity.getAuditTypeName());//模糊查询
        }
        if (auditTypeEntity.getAuditTypeId() != null && auditTypeEntity.getAuditTypeId().length() > 0) {
            wrapper.like("audit_type_id", auditTypeEntity.getAuditTypeId());//模糊查询
        }
        wrapper.orderByAsc("audit_type_id");//排序
        IPage<AuditTypeEntity> list = baseMapper.selectPage(page, wrapper);
        return list;
    }


    @Override
    public String addOrEdit(AuditTypeEntity auditTypeEntity) throws BussException {
//        check(auditTypeEntity);//先校验是否存在
        if (StringUtils.isBlank(auditTypeEntity.getAuditTypeId())) {
            //这时是新增
            baseMapper.insert(auditTypeEntity);
        } else {
            //修改
            auditTypeDao.isAutoHandle(auditTypeEntity.getIsAuto(),auditTypeEntity.getAuditTypeId());
        }
        return auditTypeEntity.getAuditTypeId();
    }


    public void check(AuditTypeEntity auditTypeEntity) throws BussException {
        int num = 0;
        if (StringUtils.isBlank(auditTypeEntity.getAuditTypeId())) {
            //新增判断
            //判断名称
            QueryWrapper<AuditTypeEntity> name = new QueryWrapper<>();
            name.eq("audit_type_name", auditTypeEntity.getAuditTypeName());//
            num = baseMapper.selectCount(name);
            if (num > 0) {
                throw new BussException("该名称已经存在");
            }
//            //判断code
//            QueryWrapper<AuditTypeEntity> code = new QueryWrapper<>();
//            code.eq("audit_type_code", auditTypeEntity.get());//
//            num = baseMapper.selectCount(code);
//            if (num > 0) {
//                throw new BussException("该代码已经存在");
//            }
        } else {
            //修改判断（校验的时候要排除自身）
            //判断名称
            QueryWrapper<AuditTypeEntity> name = new QueryWrapper<>();
            name.eq("audit_type_name", auditTypeEntity.getAuditTypeName());
            name.ne("audit_type_id", auditTypeEntity.getAuditTypeId());
            num = baseMapper.selectCount(name);
            if (num > 0) {
                throw new BussException("该名称已经存在");
            }
//            //判断code
//            QueryWrapper<AuditTypeEntity> code = new QueryWrapper<>();
//            code.eq("event_notice_type_code", auditTypeEntity.getEventNoticeTypeCode());
//            code.ne("event_notice_type_id", auditTypeEntity.getEventNoticeTypeId());
//            num = baseMapper.selectCount(code);
//            if (num > 0) {
//                throw new BussException("该代码已经存在");
//            }
        }
    }


}
