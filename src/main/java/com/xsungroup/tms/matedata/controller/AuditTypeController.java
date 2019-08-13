package com.xsungroup.tms.matedata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.core.supper.SuperController;
import com.xsungroup.tms.matedata.entity.AuditTypeEntity;
import com.xsungroup.tms.matedata.service.AuditTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 审核方式表 前端控制器
 * </p>
 *
 * @author Alex
 * @since 2019-07-22
 */
@Api(tags = "审核方式信息")
@RestController
@RequestMapping("/api/auditType")
public class AuditTypeController extends SuperController {
    @Autowired
    private AuditTypeService auditTypeService;

    @ApiOperation(value="列表查询")
    @PostMapping("/list")
    public ResponseInfo list(@RequestBody AuditTypeEntity auditTypeEntity) {
        QueryWrapper<AuditTypeEntity> qw = new QueryWrapper<>();
        qw.eq("is_able",1);
        IPage<AuditTypeEntity> iPage = this.getPage(true);
        iPage = auditTypeService.page(iPage,qw);
        return  ResponseUtil.success(iPage);
    }


    @ApiOperation(value="新增或修改")
    @PostMapping("/addOrEdit")
    public ResponseInfo addOrEdit(@RequestBody AuditTypeEntity auditTypeEntity) throws BussException {
        return  ResponseUtil.success(auditTypeService.addOrEdit(auditTypeEntity));
    }

}
