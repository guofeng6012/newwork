package com.xsungroup.tms.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.shiro.ShiroUtil;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.core.supper.SuperController;
import com.xsungroup.tms.user.dto.AuditRecordDto;
import com.xsungroup.tms.user.dto.DriverAcceptOrderDto;
import com.xsungroup.tms.user.dto.DriverDto;
import com.xsungroup.tms.user.entity.DriverEntity;
import com.xsungroup.tms.user.service.DriverService;
import com.xsungroup.tms.utils.TypeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 司机表 前端控制器
 * </p>
 *
 * @author Alex
 * @since 2019-08-01
 */
@Api(tags = "司机信息")
@RestController
@RequestMapping("/api/driver")
public class DriverController extends SuperController {

    @Autowired
    private DriverService driverService;

    @PostMapping("/add")
    @ApiOperation(value="新增司机信息")
    @ApiImplicitParam(name = "name", value = "添加司机", paramType = "add")
    public ResponseInfo add(@RequestHeader(value = "token") String token, @RequestBody DriverDto driverDto){
        String userId = ShiroUtil.currentUserId();
        return  ResponseUtil.success(driverService.addDriver(userId,driverDto));
    }


    @PostMapping("/edit")
    @ApiOperation(value="修改司机信息")
    public ResponseInfo edit(@RequestHeader(value = "token") String token,@RequestBody DriverDto driverDto){
        String userId = ShiroUtil.currentUserId();
        return  ResponseUtil.success(driverService.editDriver(userId,driverDto));
    }


    @PostMapping("/checkIsExist")
    @ApiOperation(value="新增司机时，判断这个司机是否已经在系统中了")
    public ResponseInfo checkIsExist(@RequestBody DriverDto driverDto){
        //返回0不在，1表示在系统中
        return  ResponseUtil.success(driverService.checkIsExist(driverDto));
    }




//    @PostMapping("/list")
//    @ApiOperation(value="修改司机信息")
//    public ResponseInfo listee(@RequestBody DriverDto driverDto){
//        Page<DriverEntity> page = new Page<>(driverDto.getPageNum(), driverDto.getPageSize()); // 当前页码，每页条数
//        return  ResponseUtil.success(driverService.list(page,driverDto));
//    }
    @GetMapping("/list")
    @ApiOperation(value="司机列表信息查询")
    public ResponseInfo list(String idCardName,String mobile,String auditStatus){
        String orgId = ShiroUtil.currentUser().getOrgId();
        QueryWrapper<DriverEntity> qw = new QueryWrapper<>();
        qw.eq("is_able",1);
        qw.eq("org_id",orgId);
        if(idCardName != null && idCardName.length() > 0){
            qw.like("id_card_name",idCardName);
        }
        if(mobile != null && mobile.length() > 0){
            qw.like("mobile",mobile);
        }
        qw.eq("is_bound",1);
        if(auditStatus != null && auditStatus.length() > 0){
            qw.eq("audit_status",auditStatus);
        }
        IPage<DriverEntity> iPage = this.getPage(true);
        iPage = driverService.page(iPage,qw);
        return  ResponseUtil.success(iPage);
    }


    @GetMapping("/invalidList")
    @ApiOperation(value="失效司机列表信息查询")
    public ResponseInfo invalidList(String idCardName,String mobile){
        QueryWrapper<DriverEntity> qw = new QueryWrapper<>();
        qw.eq("is_able",1);
        if(idCardName != null && idCardName.length() > 0){
            qw.like("id_card_name",idCardName);
        }
        if(mobile != null && mobile.length() > 0){
            qw.like("mobile",mobile);
        }
//        qw.eq("audit_status",TypeUtils.castToString(auditStatus,""));
        qw.eq("is_bound",0);
        IPage<DriverEntity> iPage = this.getPage(true);
        iPage = driverService.page(iPage,qw);
        return  ResponseUtil.success(iPage);
    }


    @PostMapping("/deleBatchIds")
    @ApiOperation(value="批量删除（逻辑删除，把is_able的值变为0）")
    public ResponseInfo deleBatchIds(@RequestBody List<String> list){
        return  ResponseUtil.success(driverService.deleBatchIds(list));
    }



    @PostMapping("/unBound")
    @ApiOperation(value="列表的解绑动作")
    public ResponseInfo unBound(@RequestBody DriverAcceptOrderDto acceptOrderDto){
        return  ResponseUtil.success(driverService.unBound(acceptOrderDto));
    }


    @PostMapping("/bound")
    @ApiOperation(value="列表的重新绑定动作")
    public ResponseInfo bound(@RequestBody DriverAcceptOrderDto acceptOrderDto){
        return  ResponseUtil.success(driverService.bound(acceptOrderDto));
    }


    @PostMapping("/acceptOrder")
    @ApiOperation(value="是否允许司机接单")
    public ResponseInfo acceptOrder(@RequestBody DriverAcceptOrderDto acceptOrderDto){
        return  ResponseUtil.success(driverService.acceptOrder(acceptOrderDto.getDriverId()));
    }


    /**
     *  以下是关于司机审核的借口
     */

    @GetMapping("/auditList")
    @ApiOperation(value="司机审核的列表")
    public ResponseInfo auditList(String mobile,String driverName,String auditStatus){
        QueryWrapper<DriverEntity> qw = new QueryWrapper<>();
        qw.eq("is_able",1);
        if(driverName != null && driverName.length() > 0){
            qw.like("id_card_name",driverName);
        }
        if(mobile != null && mobile.length() > 0){
            qw.like("mobile",mobile);
        }
        if(auditStatus != null && auditStatus.length() > 0){
            qw.eq("audit_status",auditStatus);
        }
        IPage<DriverEntity> iPage = this.getPage(true);
        return  ResponseUtil.success(driverService.page(iPage,qw));
    }


    @GetMapping("/auditDetail")
    @ApiOperation(value="审核的详情，点击审核展示的信息")
    //讲道理，这个接口可以不调，完全可以从列表带过去
    public ResponseInfo auditDetail(String driverId){
        driverService.getBaseMapper().selectById(driverId);
        return  ResponseUtil.success(driverService.getBaseMapper().selectById(driverId));
    }


    @PostMapping("/auditRefuse")
    @ApiOperation(value="审核拒绝")
    public ResponseInfo auditRefuse(AuditRecordDto auditRecordDto){
        return  ResponseUtil.success(driverService.auditRefuse(auditRecordDto));
    }


    @PostMapping("/auditSuccess")
    @ApiOperation(value="审核通过")
    public ResponseInfo auditSuccess(DriverDto driverDto){
        return  ResponseUtil.success(driverService.auditSuccess(driverDto));
    }


    @PostMapping("/auditCancel")
    @ApiOperation(value="取消审核")
    public ResponseInfo auditCancel(AuditRecordDto auditRecordDto){
        return  ResponseUtil.success(driverService.auditCancel(auditRecordDto));
    }
}
