package com.xsungroup.tms.matedata.controller;


import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.supper.BaseController;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.matedata.dto.InsuranceAddOrEditDTO;
import com.xsungroup.tms.matedata.dto.InsuranceSelectDTO;
import com.xsungroup.tms.matedata.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 险种大类表 前端控制器
 * </p>
 *
 * @author GF
 * @since 2019-07-25
 */
@RestController
@RequestMapping("/api/insurance")
public class InsuranceController extends BaseController {


    @Autowired
    private InsuranceService insuranceService;

    /**
     * 新增编辑险种
     * @param insuranceAddOrEditDTO
     * @return
     */
    @PostMapping("/addOrEdit")
    public ResponseInfo addOrEdit(@RequestBody InsuranceAddOrEditDTO insuranceAddOrEditDTO){
        return ResponseUtil.success(insuranceService.addOrEdit(insuranceAddOrEditDTO));
    }

    /**
     * 删除险种
     * @param insuranceIds
     * @return
     */
    @PostMapping("/deleteDataBatchIds")
    public ResponseInfo delInsurance(@RequestBody List<String> insuranceIds){
        return ResponseUtil.success(insuranceService.delInsurance(insuranceIds));
    }

    /**
     * 分页查询险种
     * @param insuranceSelectDTO
     * @return
     */
    @PostMapping("/list")
    public ResponseInfo findByPage(@RequestBody InsuranceSelectDTO insuranceSelectDTO){
        return  ResponseUtil.success(insuranceService.findByPage(insuranceSelectDTO));
    }

    /**
     * 查询险种
     * @param id
     * @return
     */
    @PostMapping("/getByInsuranceId")
    public ResponseInfo getById(@RequestParam("insuranceId") String id){
        return ResponseUtil.success(insuranceService.getById(id));
    }
}
