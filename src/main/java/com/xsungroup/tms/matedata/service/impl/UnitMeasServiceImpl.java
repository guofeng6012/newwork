package com.xsungroup.tms.matedata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.matedata.entity.UnitMeasEntity;
import com.xsungroup.tms.matedata.mapper.UnitMeasDao;
import com.xsungroup.tms.matedata.service.UnitMeasService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 计量单位表 服务实现类
 * </p>
 *
 * @author Alex
 * @since 2019-07-19
 */
@Service
public class UnitMeasServiceImpl extends ServiceImpl<UnitMeasDao, UnitMeasEntity> implements UnitMeasService {


    @Override
    public Object list(IPage<UnitMeasEntity> page, UnitMeasEntity unitMeasEntity) {
        QueryWrapper<UnitMeasEntity> wrapper = new QueryWrapper<>();
//        wrapper.setEntity(EventNoticeTypeEntity);
        wrapper.eq("is_able", 1);//精确查询
        if (unitMeasEntity.getUnitMeasName() != null && unitMeasEntity.getUnitMeasName().length() > 0) {
            wrapper.like("unit_meas_name", unitMeasEntity.getUnitMeasName());//模糊查询
        }
        if (unitMeasEntity.getUnitMeasCode() != null && unitMeasEntity.getUnitMeasCode().length() > 0) {
            wrapper.like("unit_meas_code", unitMeasEntity.getUnitMeasCode());//模糊查询
        }
        wrapper.orderByDesc("unit_meas_code");//排序
        IPage<UnitMeasEntity> list = baseMapper.selectPage(page, wrapper);
        return list;
    }



    @Override
    public String addOrEdit(UnitMeasEntity unitMeasEntity) throws BussException {
        check(unitMeasEntity);//先校验是否存在
        if (StringUtils.isBlank(unitMeasEntity.getUnitMeasId())) {
            //这时是新增
            baseMapper.insert(unitMeasEntity);
        } else {
            //修改
            baseMapper.updateById(unitMeasEntity);
        }
        return unitMeasEntity.getUnitMeasId();
    }


    public void check(UnitMeasEntity unitMeasEntity) throws BussException {
        int num = 0;
        if (StringUtils.isBlank(unitMeasEntity.getUnitMeasId())) {
            //新增判断
            //判断名称
            QueryWrapper<UnitMeasEntity> name = new QueryWrapper<>();
            name.eq("unit_meas_name", unitMeasEntity.getUnitMeasName());//
            num = baseMapper.selectCount(name);
            if (num > 0) {
                throw new BussException("该名称已经存在");
            }
            //判断code
            QueryWrapper<UnitMeasEntity> code = new QueryWrapper<>();
            code.eq("unit_meas_code", unitMeasEntity.getUnitMeasCode());//
            num = baseMapper.selectCount(code);
            if (num > 0) {
                throw new BussException("该代码已经存在");
            }
        } else {
            //修改判断（校验的时候要排除自身）
            //判断名称
            QueryWrapper<UnitMeasEntity> name = new QueryWrapper<>();
            name.eq("unit_meas_name", unitMeasEntity.getUnitMeasName());
            name.ne("unit_meas_id", unitMeasEntity.getUnitMeasId());
            num = baseMapper.selectCount(name);
            if (num > 0) {
                throw new BussException("该名称已经存在");
            }
            //判断code
            QueryWrapper<UnitMeasEntity> code = new QueryWrapper<>();
            code.eq("unit_meas_code", unitMeasEntity.getUnitMeasCode());
            code.ne("unit_meas_id", unitMeasEntity.getUnitMeasId());
            num = baseMapper.selectCount(code);
            if (num > 0) {
                throw new BussException("该代码已经存在");
            }
        }
    }

}
