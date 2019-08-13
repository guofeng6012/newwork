package com.xsungroup.tms.matedata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.shiro.ShiroUtil;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.core.supper.SuperController;
import com.xsungroup.tms.matedata.dto.BoundDriverDto;
import com.xsungroup.tms.matedata.dto.CarDriverDto;
import com.xsungroup.tms.matedata.dto.CarDto;
import com.xsungroup.tms.matedata.dto.CarOrgDto;
import com.xsungroup.tms.matedata.entity.CarEntity;
import com.xsungroup.tms.matedata.service.CarService;
import com.xsungroup.tms.user.dto.AuditRecordDto;
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
 * 车辆表 前端控制器
 * </p>
 *
 * @author Alex
 * @since 2019-08-06
 */

@Api(tags = "车辆信息")
@RestController
@RequestMapping("/api/car")
public class CarController extends SuperController {

    @Autowired
    private CarService carService;

    @Autowired
    private DriverService driverService;

    @PostMapping("/add")
    @ApiOperation(value="新增车辆信息")
    @ApiImplicitParam(name = "name", value = "添加车辆", paramType = "add")
    public ResponseInfo add(@RequestBody CarDto carDao){
        return  ResponseUtil.success(carService.addCar(carDao));
    }


    @PostMapping("/addCheck")
    @ApiOperation(value="新增车辆信息时的校验")
    @ApiImplicitParam(name = "name", value = "添加车辆校验", paramType = "add")
    public ResponseInfo addCheck(@RequestBody CarDto carDao){
        return  ResponseUtil.success(carService.addCheck(carDao));
    }


    @GetMapping("/boundDriverList")
    @ApiOperation(value="查找绑定的司机")
    @ApiImplicitParam(name = "name", value = "找司机")
    public ResponseInfo boundDriverList(String driverName,String mobile){
        String orgId = ShiroUtil.currentUser().getOrgId();
        QueryWrapper<DriverEntity> qw = new QueryWrapper<>();
        qw.eq("is_able",1);
        qw.eq("is_bound",0);
        if(driverName != null && driverName.length() > 0){
            qw.like("id_card_name",driverName);
        }
        if(mobile != null && mobile.length() > 0){
            qw.like("mobile",mobile);
        }
        qw.eq("org_id",orgId);
        IPage<DriverEntity> iPage = this.getPage(true);
        iPage = driverService.page(iPage,qw);
        return  ResponseUtil.success(iPage);
    }


    @PostMapping("/boundDriver")
    @ApiOperation(value="批量绑定司机，就是点击上面的绑定按钮")
    public ResponseInfo boundDriver(@RequestBody BoundDriverDto boundDriverDto){
        return  ResponseUtil.success(carService.boundDriver(boundDriverDto));
    }


    @PostMapping("/cancelBoundDriver")
    @ApiOperation(value="取消绑定的司机")
    //只需要传车辆和司机的id
    public ResponseInfo cancelBoundDriver(@RequestBody CarDriverDto carDriverDto){
        return  ResponseUtil.success(carService.cancelBoundDriver(carDriverDto));
    }


    @PostMapping("/isDefaultDriver")
    @ApiOperation(value="设置默认的司机，只能有一个默认")
    //跟上面的参数传一样的  只是里面的list就一条数据了
    public ResponseInfo isDefaultDriver(@RequestBody BoundDriverDto boundDriverDto){
        return  ResponseUtil.success(carService.isDefaultDriver(boundDriverDto));
    }


    @PostMapping("/deleBatchIds")
    @ApiOperation(value="批量删除（逻辑删除，把is_able的值变为0）")
    public ResponseInfo deleBatchIds(@RequestBody List<String> list){
        return  ResponseUtil.success(carService.deleBatchIds(list));
    }


    @PostMapping("/edit")
    @ApiOperation(value="修改车辆信息")
    public ResponseInfo edit(@RequestHeader(value = "token") String token,@RequestBody CarDto carDao){
        String userId = ShiroUtil.currentUserId();
        return  ResponseUtil.success(carService.editCar(userId,carDao));
    }


    @GetMapping("/list")
    @ApiOperation(value="车辆列表信息查询")
    public ResponseInfo list(String carNo,String carType,String carSort,String carOrigin,String auditStatus){
        QueryWrapper<CarEntity> qw = new QueryWrapper<>();
        qw.eq("is_able",1);
        qw.eq("car_origin",0);//自有车
        if(carNo != null && carNo.length() > 0){
            qw.like("car_no",carNo);
        }
        if(carType != null && carType.length() > 0){
            qw.eq("car_type",carType);
        }
        if(carSort != null && carSort.length() > 0){
            qw.eq("car_sort",carSort);
        }
        if(carOrigin != null && carOrigin.length() > 0){
            qw.eq("car_origin",carOrigin);
        }
        if(auditStatus != null && auditStatus.length() > 0){
            qw.eq("audit_status",auditStatus);
        }
//        qw.eq("car_type", TypeUtils.castToString(carType,""));
//        qw.eq("car_sort", TypeUtils.castToString(carSort,""));
//        qw.eq("car_origin", TypeUtils.castToString(carOrigin,""));
//        qw.eq("audit_status",TypeUtils.castToString(auditStatus,""));
        IPage<CarEntity> iPage = this.getPage(true);
        iPage = carService.page(iPage,qw);
        return  ResponseUtil.success(iPage);
   }


    @GetMapping("/outSourceList")
    @ApiOperation(value="外协车辆列表")
    public ResponseInfo outSourceList(@RequestHeader(value = "token") String token,String carNo,String carType,String carSort){
        String userId = ShiroUtil.currentUserId();
        QueryWrapper<CarEntity> qw = carService.outSourceList(userId,carNo,carType,carSort);
        IPage<CarEntity> iPage = getPage(true);
        iPage = carService.page(iPage,qw);
        return  ResponseUtil.success(iPage);
   }


    @PostMapping("/cancleAssociation")
    @ApiOperation(value="外协车辆列表界面中取消关联的接口")
    public ResponseInfo cancleAssociation(List<String> list){
        String orgId = ShiroUtil.currentUser().getOrgId();//当前登录用户的组织id
        carService.cancleAssociation(orgId,list);
        return  ResponseUtil.success();
    }


    @GetMapping("/outSourceCar")
    @ApiOperation(value="查找外协车辆")
    public ResponseInfo outSourceCar(String carNo,String vinNo){
        QueryWrapper<CarEntity> qw = new QueryWrapper<>();
        qw.eq("is_able",1);
        if(carNo != null && carNo.length() > 0){
            qw.eq("car_no",carNo);
        }
        if(vinNo != null && vinNo.length() > 0){
            qw.eq("vin_no", vinNo);
        }
        IPage<CarEntity> iPage = this.getPage(true);
        iPage = carService.page(iPage,qw);
        return  ResponseUtil.success(iPage);
   }


    @GetMapping("/vehicleCate")
    @ApiOperation(value="通过车辆类型，找到车辆分类")
    public ResponseInfo vehicleCate(String vehicleType){
        return  ResponseUtil.success(carService.vehicleCate(vehicleType));
    }


    //外协车辆、租赁车辆
    @PostMapping("/outSource")
    @ApiOperation(value="别的页面租的'外协'车辆的方法")
    public ResponseInfo outSourceCar(@RequestHeader(value = "token") String token,@RequestBody CarOrgDto carOrgDto){
        String userId = ShiroUtil.currentUserId();
        return  ResponseUtil.success(carService.outSourceCar(userId,carOrgDto));
    }


    /**
     *  以下是关于司机审核的借口
     */

    @GetMapping("/auditList")
    @ApiOperation(value="车辆审核的列表")
    public ResponseInfo auditList(String carNo,String source,String auditStatus){
        QueryWrapper<CarEntity> qw = new QueryWrapper<>();
        qw.eq("is_able",1);
        if(carNo != null && carNo.length() > 0){
            qw.like("car_no",carNo);
        }
        if(source != null && source.length() > 0){
            qw.eq("source",source);
        }
        if(auditStatus != null && auditStatus.length() > 0){
            qw.eq("audit_status",auditStatus);
        }
        IPage<CarEntity> iPage = this.getPage(true);
        return  ResponseUtil.success(carService.page(iPage,qw));
    }


    @GetMapping("/auditDetail")
    @ApiOperation(value="审核的详情，点击审核展示的信息")
    //讲道理，这个接口可以不调，完全可以从列表带过去
    public ResponseInfo auditDetail(String driverId){
        carService.getBaseMapper().selectById(driverId);
        return  ResponseUtil.success(carService.getBaseMapper().selectById(driverId));
    }


    @PostMapping("/auditRefuse")
    @ApiOperation(value="审核拒绝")
    public ResponseInfo auditRefuse(AuditRecordDto auditRecordDto){
        return  ResponseUtil.success(carService.auditRefuse(auditRecordDto));
    }


    @PostMapping("/auditSuccess")
    @ApiOperation(value="审核通过")
    public ResponseInfo auditSuccess(CarDto carDto){
        return  ResponseUtil.success(carService.auditSuccess(carDto));
    }


    @PostMapping("/auditCancel")
    @ApiOperation(value="取消审核")
    public ResponseInfo auditCancel(AuditRecordDto auditRecordDto){
        return  ResponseUtil.success(carService.auditCancel(auditRecordDto));
    }
}

