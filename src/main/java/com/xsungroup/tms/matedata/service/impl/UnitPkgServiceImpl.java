package com.xsungroup.tms.matedata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.matedata.entity.UnitPkgEntity;
import com.xsungroup.tms.matedata.mapper.UnitPkgDao;
import com.xsungroup.tms.matedata.service.UnitPkgService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 包装单位表 服务实现类
 * </p>
 *
 * @author Alex
 * @since 2019-07-19
 */
@Service
public class UnitPkgServiceImpl extends ServiceImpl<UnitPkgDao, UnitPkgEntity> implements UnitPkgService {

    @Override
    public Object list(IPage<UnitPkgEntity> page, UnitPkgEntity unitPkgEntity) {
        QueryWrapper<UnitPkgEntity> wrapper = new QueryWrapper<>();
//        wrapper.setEntity(EventNoticeTypeEntity);
        wrapper.eq("is_able", 1);//精确查询
        if (unitPkgEntity.getUnitPkgName() != null && unitPkgEntity.getUnitPkgName().length() > 0) {
            wrapper.like("unit_pkg_name", unitPkgEntity.getUnitPkgName());//模糊查询
        }
        if (unitPkgEntity.getUnitPkgCode() != null && unitPkgEntity.getUnitPkgCode().length() > 0) {
            wrapper.like("unit_pkg_code", unitPkgEntity.getUnitPkgCode());//模糊查询
        }
        wrapper.orderByDesc("unit_pkg_code");//排序
        IPage<UnitPkgEntity> list = baseMapper.selectPage(page, wrapper);
        return list;
    }

    @Override
    public String addOrEdit(UnitPkgEntity unitPkgEntity) throws BussException {
        check(unitPkgEntity);//先校验是否存在
        if (StringUtils.isBlank(unitPkgEntity.getUnitPkgId())) {
            //这时是新增
            baseMapper.insert(unitPkgEntity);
        } else {
            //修改
            baseMapper.updateById(unitPkgEntity);
        }
        return unitPkgEntity.getUnitPkgId();
    }


    public void check(UnitPkgEntity unitPkgEntity) throws BussException {
        int num = 0;
        if (StringUtils.isBlank(unitPkgEntity.getUnitPkgId())) {
            //新增判断
            //判断名称
            QueryWrapper<UnitPkgEntity> name = new QueryWrapper<>();
            name.eq("unit_pkg_name", unitPkgEntity.getUnitPkgName());//
            num = baseMapper.selectCount(name);
            if (num > 0) {
                throw new BussException("该名称已经存在");
            }
            //判断code
            QueryWrapper<UnitPkgEntity> code = new QueryWrapper<>();
            code.eq("unit_pkg_code", unitPkgEntity.getUnitPkgCode());//
            num = baseMapper.selectCount(code);
            if (num > 0) {
                throw new BussException("该代码已经存在");
            }
        } else {
            //修改判断（校验的时候要排除自身）
            //判断名称
            QueryWrapper<UnitPkgEntity> name = new QueryWrapper<>();
            name.eq("unit_pkg_name", unitPkgEntity.getUnitPkgName());
            name.ne("unit_pkg_id", unitPkgEntity.getUnitPkgId());
            num = baseMapper.selectCount(name);
            if (num > 0) {
                throw new BussException("该名称已经存在");
            }
            //判断code
            QueryWrapper<UnitPkgEntity> code = new QueryWrapper<>();
            code.eq("unit_pkg_code", unitPkgEntity.getUnitPkgCode());
            code.ne("unit_pkg_id", unitPkgEntity.getUnitPkgId());
            num = baseMapper.selectCount(code);
            if (num > 0) {
                throw new BussException("该代码已经存在");
            }
        }
    }

}
