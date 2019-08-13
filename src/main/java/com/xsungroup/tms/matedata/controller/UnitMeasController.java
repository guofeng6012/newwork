package com.xsungroup.tms.matedata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.core.supper.SuperController;
import com.xsungroup.tms.matedata.entity.UnitMeasEntity;
import com.xsungroup.tms.matedata.service.UnitMeasService;
import com.xsungroup.tms.utils.TypeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 计量单位表 前端控制器
 * </p>
 *
 * @author Alex
 * @since 2019-07-19
 */
@Api(tags = "计量单位信息")
@RestController
@RequestMapping("/api/unitMeas")
public class UnitMeasController extends SuperController {

    @Autowired
    private UnitMeasService unitMeasServicel;


    @ApiOperation(value="列表查询")
    @PostMapping("/list")
    public ResponseInfo list(@RequestBody UnitMeasEntity unitMeasEntity) {
        QueryWrapper<UnitMeasEntity> qw = new QueryWrapper<>();
        qw.eq("is_able",1);
        qw.likeRight("unit_meas_name", TypeUtils.castToString(unitMeasEntity.getUnitMeasName(),""));
        qw.likeRight("unit_meas_code", TypeUtils.castToString(unitMeasEntity.getUnitMeasCode(),""));
        IPage<UnitMeasEntity> iPage = this.getPage(true);
        iPage = unitMeasServicel.page(iPage,qw);
        return  ResponseUtil.success(iPage);
    }


    @ApiOperation(value="新增或修改")
    @PostMapping("/addOrEdit")
    public ResponseInfo addOrEdit(@RequestBody UnitMeasEntity unitMeasEntity) throws BussException {
        return  ResponseUtil.success(unitMeasServicel.addOrEdit(unitMeasEntity));
    }


}
