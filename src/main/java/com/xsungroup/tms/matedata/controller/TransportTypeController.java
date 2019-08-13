package com.xsungroup.tms.matedata.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.core.supper.BaseController;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.matedata.entity.TransportTypeEntity;
import com.xsungroup.tms.matedata.service.TransportTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 运输类型表 前端控制器
 * </p>
 *
 * @author Alex
 * @since 2019-07-15
 */
@Api(tags = "运输类型")
@RestController
@RequestMapping("/api/transtype")
public class TransportTypeController extends BaseController<TransportTypeEntity> {


    @Autowired
    TransportTypeService transportTypeService;

    @ApiOperation(value="列表查询")
    @PostMapping("/list")
    public ResponseInfo list(@RequestBody Map<String,Object> map) {
        Page<TransportTypeEntity> page = new Page<>(Integer.parseInt(map.remove("pageNum").toString()), Integer.parseInt(map.remove("pageSize").toString())); // 当前页码，每页条数
        TransportTypeEntity  transportTypeEntity = new TransportTypeEntity();
        if(map.get("transTypeCode") != null && map.get("transTypeCode").toString().length() > 0){
            transportTypeEntity.setTransTypeCode(map.get("transTypeCode").toString());
        }
        if(map.get("transTypeName") != null && map.get("transTypeName").toString().length() > 0){
            transportTypeEntity.setTransTypeName(map.get("transTypeName").toString());
        }
        return  ResponseUtil.success(transportTypeService.list(page,transportTypeEntity));
    }


    @ApiOperation(value="新增或修改")
    @PostMapping("/addOrEdit")
    public ResponseInfo addOrEdit(@RequestBody TransportTypeEntity  transportTypeEntity) throws BussException {
        return  ResponseUtil.success(transportTypeService.addOrEdit(transportTypeEntity));
    }



}
