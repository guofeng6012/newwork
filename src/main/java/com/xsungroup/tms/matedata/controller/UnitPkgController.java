package com.xsungroup.tms.matedata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.core.supper.SuperController;
import com.xsungroup.tms.matedata.entity.UnitPkgEntity;
import com.xsungroup.tms.matedata.service.UnitPkgService;
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
 * 包装单位表 前端控制器
 * </p>
 *
 * @author Alex
 * @since 2019-07-19
 */
@Api(tags = "包装单位信息")
@RestController
@RequestMapping("/api/unitPkg")
public class UnitPkgController extends SuperController {

    @Autowired
    private UnitPkgService unitPkgService;

    @ApiOperation(value="列表查询")
    @PostMapping("/list")
    public ResponseInfo list(@RequestBody UnitPkgEntity unitMeasEntity) {
        QueryWrapper<UnitPkgEntity> qw = new QueryWrapper<>();
        qw.eq("is_able",1);
        qw.likeRight("unit_pkg_name", TypeUtils.castToString(unitMeasEntity.getUnitPkgName(),""));
        qw.likeRight("unit_pkg_code", TypeUtils.castToString(unitMeasEntity.getUnitPkgCode(),""));
        IPage<UnitPkgEntity> iPage = this.getPage(true);
        iPage = unitPkgService.page(iPage,qw);
        return  ResponseUtil.success(iPage);
    }


    @ApiOperation(value="新增或修改")
    @PostMapping("/addOrEdit")
    public ResponseInfo addOrEdit(@RequestBody UnitPkgEntity unitMeasEntity) throws BussException {
        return  ResponseUtil.success(unitPkgService.addOrEdit(unitMeasEntity));
    }


}
