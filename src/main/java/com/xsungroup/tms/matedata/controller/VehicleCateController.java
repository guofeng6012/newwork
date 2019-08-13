package com.xsungroup.tms.matedata.controller;


import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.core.supper.SuperController;
import com.xsungroup.tms.matedata.entity.VehicleCateEntity;
import com.xsungroup.tms.matedata.service.VehicleCateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 车辆分类表 前端控制器
 * </p>
 *
 * @author Alex
 * @since 2019-07-19
 */
@Api(tags = "车辆分类信息")
@RestController
@RequestMapping("/api/vehicleCate")
public class VehicleCateController extends SuperController {

    @Autowired
    private VehicleCateService vehicleCateService;


    @ApiOperation(value="列表查询")
    @PostMapping("/list")
    public ResponseInfo list(@RequestBody VehicleCateEntity vehicleCateEntity) {
        return  ResponseUtil.success(vehicleCateService.list(this.getPage(false),vehicleCateEntity));
    }


    @ApiOperation(value="新增或修改")
    @PostMapping("/addOrEdit")
    public ResponseInfo addOrEdit(@RequestBody VehicleCateEntity vehicleCateEntity) throws BussException {
        return  ResponseUtil.success(vehicleCateService.addOrEdit(vehicleCateEntity));
    }


}
