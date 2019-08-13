package com.xsungroup.tms.matedata.controller;


import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.core.supper.SuperController;
import com.xsungroup.tms.matedata.entity.VehicleReceiptTypeEntity;
import com.xsungroup.tms.matedata.service.VehicleReceiptTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 车辆接单类型表 前端控制器
 * </p>
 *
 * @author Alex
 * @since 2019-07-22
 */
@Api(tags = "车辆接单类型信息")
@RestController
@RequestMapping("/api/vehicleReceiptType")
public class VehicleReceiptTypeController extends SuperController {

    @Autowired
    private VehicleReceiptTypeService vehicleReceiptTypeService;

    @ApiOperation(value="列表查询")
    @PostMapping("/list")
    public ResponseInfo list(@RequestBody VehicleReceiptTypeEntity vehicleReceiptTypeEntity) {

        return  ResponseUtil.success(vehicleReceiptTypeService.list(this.getPage(false),vehicleReceiptTypeEntity));
    }


    @ApiOperation(value="新增或修改")
    @PostMapping("/addOrEdit")
    public ResponseInfo addOrEdit(@RequestBody VehicleReceiptTypeEntity vehicleReceiptTypeEntity) throws BussException {
        return  ResponseUtil.success(vehicleReceiptTypeService.addOrEdit(vehicleReceiptTypeEntity));
    }

}
