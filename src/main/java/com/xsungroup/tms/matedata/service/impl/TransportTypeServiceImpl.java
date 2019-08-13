package com.xsungroup.tms.matedata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.matedata.entity.TransportTypeEntity;
import com.xsungroup.tms.matedata.mapper.TransportTypeDao;
import com.xsungroup.tms.matedata.service.TransportTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 运输类型表 服务实现类
 * </p>
 *
 * @author Alex
 * @since 2019-07-15
 */
@Service
public class TransportTypeServiceImpl extends ServiceImpl<TransportTypeDao, TransportTypeEntity> implements TransportTypeService {
    @Override
    public Object list(IPage<TransportTypeEntity> page, TransportTypeEntity transportTypeEntity) {
        QueryWrapper<TransportTypeEntity> wrapper = new QueryWrapper<>();
//        wrapper.setEntity(transportTypeEntity);
        wrapper.eq("is_able", 1);//精确查询
        if (transportTypeEntity.getTransTypeName() != null && transportTypeEntity.getTransTypeName().length() > 0) {
            wrapper.like("trans_type_name", transportTypeEntity.getTransTypeName());//模糊查询
        }
        if (transportTypeEntity.getTransTypeCode() != null && transportTypeEntity.getTransTypeCode().length() > 0) {
            wrapper.like("trans_type_code", transportTypeEntity.getTransTypeCode());//模糊查询
        }
        wrapper.orderByDesc("trans_type_code");//排序
        IPage<TransportTypeEntity> list = baseMapper.selectPage(page, wrapper);
        return list;
    }

    @Override
    public String addOrEdit(TransportTypeEntity transportTypeEntity) throws BussException {
        check(transportTypeEntity);//先校验是否存在
        if (StringUtils.isBlank(transportTypeEntity.getTransTypeId())) {
            //这时是新增
            baseMapper.insert(transportTypeEntity);
        } else {
            //修改
            baseMapper.updateById(transportTypeEntity);
        }
        return transportTypeEntity.getTransTypeId();
    }


    public void check(TransportTypeEntity transportTypeEntity) throws BussException {
        int num = 0;
        if (StringUtils.isBlank(transportTypeEntity.getTransTypeId())) {
            //新增判断
            //判断名称
            QueryWrapper<TransportTypeEntity> name = new QueryWrapper<>();
            name.eq("trans_type_name", transportTypeEntity.getTransTypeName());//
            num = baseMapper.selectCount(name);
            if (num > 0) {
                    throw new BussException("该名称已经存在");
            }
            //判断code
            QueryWrapper<TransportTypeEntity> code = new QueryWrapper<>();
            code.eq("trans_type_code", transportTypeEntity.getTransTypeCode());//
            num = baseMapper.selectCount(code);
            if (num > 0) {
                    throw new BussException("该代码已经存在");
            }
        } else {
            //修改判断（校验的时候要排除自身）
            //判断名称
            QueryWrapper<TransportTypeEntity> name = new QueryWrapper<>();
            name.eq("trans_type_name", transportTypeEntity.getTransTypeName());
            name.ne("trans_type_id", transportTypeEntity.getTransTypeId());
            num = baseMapper.selectCount(name);
            if (num > 0) {
                    throw new BussException("该名称已经存在");
            }
            //判断code
            QueryWrapper<TransportTypeEntity> code = new QueryWrapper<>();
            code.eq("trans_type_code", transportTypeEntity.getTransTypeCode());
            code.ne("trans_type_id", transportTypeEntity.getTransTypeId());
            num = baseMapper.selectCount(code);
            if (num > 0) {
                    throw new BussException("该代码已经存在");
            }
        }
    }

}
