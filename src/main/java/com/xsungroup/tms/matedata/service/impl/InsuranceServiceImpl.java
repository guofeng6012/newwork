package com.xsungroup.tms.matedata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsungroup.tms.matedata.dto.InsuranceAddOrEditDTO;
import com.xsungroup.tms.matedata.dto.InsuranceDTO;
import com.xsungroup.tms.matedata.dto.InsuranceSelectDTO;
import com.xsungroup.tms.matedata.entity.InsuranceEntity;
import com.xsungroup.tms.matedata.mapper.InsuranceDao;
import com.xsungroup.tms.matedata.service.InsuranceService;
import com.xsungroup.tms.matedata.vo.InsuranceQueryVO;
import com.xsungroup.tms.matedata.vo.InsuranceVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 险种大类表 服务实现类
 * </p>
 *
 * @author GF
 * @since 2019-07-25
 */
@Service
public class InsuranceServiceImpl extends ServiceImpl<InsuranceDao, InsuranceEntity> implements InsuranceService {

    @Autowired
    private InsuranceDao insuranceDao;

    /**
     * 新增修改险种
     * @param insuranceAddOrEditDTO
     * @return
     */
    @Transactional
    @Override
    public boolean addOrEdit(InsuranceAddOrEditDTO insuranceAddOrEditDTO) {
        InsuranceEntity insuranceEntity = new InsuranceEntity();
        BeanUtils.copyProperties(insuranceAddOrEditDTO,insuranceEntity);
        insuranceEntity.setType(0);
        //情况一：新增的时候
        // 这个时候比较简单了，直接insert即可
        if(StringUtils.isEmpty(insuranceAddOrEditDTO.getInsuranceId())){
            //先保存主表信息，后拿主表的ID 放到子表中去，再保存子表
            insuranceDao.insert(insuranceEntity);
            String id = insuranceEntity.getInsuranceId();
            if(insuranceAddOrEditDTO.getInsList().isEmpty()){
                return true;
            }
            List<InsuranceEntity> list = new ArrayList<>();
            for(InsuranceDTO insuranceDTO : insuranceAddOrEditDTO.getInsList()){
                InsuranceEntity insuranceEntityParent = new InsuranceEntity();
                BeanUtils.copyProperties(insuranceDTO,insuranceEntityParent);
                insuranceEntityParent.setType(1);
                String uuid = UUID.randomUUID().toString().replace("-","");
                insuranceEntityParent.setInsuranceId(uuid);
                insuranceEntityParent.setParentId(id);
                list.add(insuranceEntityParent);
            }
            insuranceDao.addBatchData(list);
        }else{
            //情况二：修改的时候
            //先保存修改后的主表信息
            insuranceDao.updateById(insuranceEntity);
            List<InsuranceEntity> addList = new ArrayList<>();//需要新增的子表数据
            List<InsuranceEntity> editList = new ArrayList<>();//修改的子表信息
            List<String> deleteList = new ArrayList<>();//删除的子表信息
            for(InsuranceDTO insuranceDTO : insuranceAddOrEditDTO.getInsList()){
                InsuranceEntity ins = new InsuranceEntity();
                BeanUtils.copyProperties(insuranceDTO,ins);
                ins.setType(1);
                if(StringUtils.isEmpty(insuranceDTO.getInsuranceId())){
                    //ID为空，是新增的
                    ins.setParentId(insuranceAddOrEditDTO.getInsuranceId());
                    addList.add(ins);
                }else{
                    if(1 == insuranceDTO.getIsAble()){
                        //这个是要修改的
                        editList.add(ins);
                    }else if(0 == insuranceDTO.getIsAble()){
                        //这个是要删除的
                        deleteList.add(insuranceDTO.getInsuranceId());
                    }
                }
            }
            if(!addList.isEmpty()){
                insuranceDao.addBatchData(addList);//批量新增
            }
            if(!editList.isEmpty()){
                insuranceDao.editBatchData(editList);//批量修改
            }
            if(!deleteList.isEmpty()){
                insuranceDao.deleteBatchIds(deleteList);//批量删除
            }
        }
        return true;
    }

    /**
     * 删除险种
     * @param insuranceId
     * @return
     */
    @Transactional
    @Override
    public int  delInsurance(List<String> insuranceId) {
        QueryWrapper<InsuranceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("parent_id",insuranceId);
        List<InsuranceEntity> list = insuranceDao.selectList(queryWrapper);
        if(!list.isEmpty()){
            for (InsuranceEntity insuranceEntity : list) {
                insuranceId.add(insuranceEntity.getInsuranceId());
            }
        }
        return insuranceDao.deleteBatchIds(insuranceId);
    }

    /**
     * 查询险种信息 分页
     * @param insuranceSelectDTO
     * @return
     */
    @Override
    public Object findByPage(InsuranceSelectDTO insuranceSelectDTO) {
        IPage<InsuranceEntity> page = new Page<>();
        page.setSize(insuranceSelectDTO.getPageSize());
        page.setCurrent(insuranceSelectDTO.getPageNum());
        QueryWrapper<InsuranceEntity> wrapper = new QueryWrapper<>();
//        wrapper.setEntity(EventNoticeTypeEntity);
        wrapper.eq("is_able", 1);//精确查询
        wrapper.eq("type","0");
        if (!insuranceSelectDTO.getInsuranceName().isEmpty()) {
            wrapper.like("insurance_name", insuranceSelectDTO.getInsuranceName());//模糊查询
        }
        if (!insuranceSelectDTO.getInsuranceCode().isEmpty()) {
            wrapper.like("insurance_code", insuranceSelectDTO.getInsuranceCode());//模糊查询
        }
        wrapper.orderByDesc("insurance_code");//排序
        IPage<InsuranceEntity> list = baseMapper.selectPage(page, wrapper);
        return list;
    }

    @Override
    public InsuranceQueryVO getById(String id) {
        InsuranceEntity insuranceEntity = insuranceDao.selectById(id);
        if(null == insuranceEntity){
            return null;
        }
        QueryWrapper<InsuranceEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        List<InsuranceEntity> list = insuranceDao.selectList(wrapper);
        InsuranceQueryVO insuranceQueryVO = new InsuranceQueryVO();
        BeanUtils.copyProperties(insuranceEntity,insuranceQueryVO);
        List<InsuranceVO> insuranceVOList = new ArrayList<>();
        for(InsuranceEntity ins : list){
            InsuranceVO insuranceVO = new InsuranceVO();
            BeanUtils.copyProperties(ins,insuranceVO);
            insuranceVOList.add(insuranceVO);
        }
        insuranceQueryVO.setInsuranceVOList(insuranceVOList);
        return insuranceQueryVO;
    }
}
