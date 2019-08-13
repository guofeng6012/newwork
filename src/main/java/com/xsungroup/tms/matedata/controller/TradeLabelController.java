package com.xsungroup.tms.matedata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.core.supper.SuperController;
import com.xsungroup.tms.matedata.entity.TradeLabelEntity;
import com.xsungroup.tms.matedata.service.TradeLabelService;
import com.xsungroup.tms.utils.TypeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 行业标签表 前端控制器
 * </p>
 *
 * @author Alex
 * @since 2019-07-22
 */
@Api(tags = "行业标签信息")
@RestController
@RequestMapping("/api/tradeLabel")
public class TradeLabelController extends SuperController {

    @Autowired
    private TradeLabelService tradeLabelService;


    @ApiOperation(value="列表查询")
    @PostMapping("/list")
    public ResponseInfo list(@RequestBody TradeLabelEntity tradeLabelEntity) {
        QueryWrapper<TradeLabelEntity> qw = new QueryWrapper<>();
        qw.eq("is_able",1);
        qw.likeRight("trade_label_name", TypeUtils.castToString(tradeLabelEntity.getTradeLabelName(),""));
        qw.likeRight("trade_label_code", TypeUtils.castToString(tradeLabelEntity.getTradeLabelCode(),""));
        IPage<TradeLabelEntity> iPage = this.getPage(true);
        iPage = tradeLabelService.page(iPage,qw);
        return  ResponseUtil.success(iPage);
    }


    @ApiOperation(value="新增或修改")
    @PostMapping("/addOrEdit")
    public ResponseInfo addOrEdit(@RequestBody TradeLabelEntity tradeLabelEntity) throws BussException {
        return  ResponseUtil.success(tradeLabelService.addOrEdit(tradeLabelEntity));
    }


    @ApiOperation(value="详情接口")
    @PostMapping("/getDataById")
    public ResponseInfo getById(@RequestBody TradeLabelEntity tradeLabelEntity) throws BussException {
        return  ResponseUtil.success(tradeLabelService.getById(tradeLabelEntity));
    }


    @ApiOperation(value="删除接口")
    @PostMapping("/deleteDataBatchIds")
    public ResponseInfo deleteBatchIds(@RequestBody List<String> list) throws BussException {
        return  ResponseUtil.success(tradeLabelService.deleteBatchIds(list));
    }







}
