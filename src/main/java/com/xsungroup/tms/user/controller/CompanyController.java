package com.xsungroup.tms.user.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.core.supper.BaseController;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.core.supper.SuperController;
import com.xsungroup.tms.user.entity.CompanyEntity;
import com.xsungroup.tms.user.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 公司表 前端控制器
 * </p>
 *
 * @author Alex
 * @since 2019-07-23
 */
@Api(tags = "公司表信息")
@RestController
@RequestMapping("/api/company")
public class CompanyController extends SuperController {

    @Autowired
    private CompanyService companyService;


    /**
     * 暂时用不到的  等后面再说
     * @param companyEntity
     */
    @ApiOperation(value="列表查询")
    @PostMapping("/list")
    public ResponseInfo list(@RequestBody CompanyEntity companyEntity) {
       Page page = this.getPage(false);
       return  ResponseUtil.success(companyService.list(page,companyEntity));
    }


    @ApiOperation(value="新增或修改")
    @PostMapping("/addOrEdit")
    public ResponseInfo addOrEdit(@RequestBody CompanyEntity companyEntity) throws BussException {
        return  ResponseUtil.success(companyService.addOrEdit(companyEntity));
    }


    @ApiOperation(value="详情接口")
    @PostMapping("/getDataById")
    public ResponseInfo getById(@RequestBody CompanyEntity companyEntity) throws BussException {
        return  ResponseUtil.success(companyService.getById(companyEntity));
    }



}