package com.xsungroup.tms.matedata.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xsungroup.tms.matedata.dto.InsuranceAddOrEditDTO;
import com.xsungroup.tms.matedata.dto.InsuranceSelectDTO;
import com.xsungroup.tms.matedata.entity.InsuranceEntity;
import com.xsungroup.tms.matedata.vo.InsuranceQueryVO;

import java.util.List;

/**
 * <p>
 * 险种大类表 服务类
 * </p>
 *
 * @author GF
 * @since 2019-07-25
 */
public interface InsuranceService extends IService<InsuranceEntity> {

    /**
     * 新增修改 险种信息
     * @param insuranceAddOrEditDTO
     * @return
     */
    boolean addOrEdit(InsuranceAddOrEditDTO insuranceAddOrEditDTO);

    /**
     * 删除险种
     * @param insuranceId
     * @return
     */
    int delInsurance(List<String> insuranceId);

    /**
     * 查询险种
     * @param insuranceSelectDTO
     * @return
     */
    Object findByPage(InsuranceSelectDTO insuranceSelectDTO);


    /**
     * 查询险种
     * @param id
     * @return
     */
    InsuranceQueryVO getById(String id);
}
