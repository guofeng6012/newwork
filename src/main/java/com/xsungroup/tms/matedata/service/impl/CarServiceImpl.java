package com.xsungroup.tms.matedata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.matedata.common.CarBusCode;
import com.xsungroup.tms.matedata.dto.BoundDriverDto;
import com.xsungroup.tms.matedata.dto.CarDriverDto;
import com.xsungroup.tms.matedata.dto.CarDto;
import com.xsungroup.tms.matedata.dto.CarOrgDto;
import com.xsungroup.tms.matedata.entity.CarDriverEntity;
import com.xsungroup.tms.matedata.entity.CarEntity;
import com.xsungroup.tms.matedata.entity.CarOrgEntity;
import com.xsungroup.tms.matedata.entity.VehicleCateEntity;
import com.xsungroup.tms.matedata.mapper.CarDao;
import com.xsungroup.tms.matedata.mapper.CarDriverDao;
import com.xsungroup.tms.matedata.mapper.CarOrgDao;
import com.xsungroup.tms.matedata.mapper.VehicleCateDao;
import com.xsungroup.tms.matedata.service.CarService;
import com.xsungroup.tms.user.dto.AuditRecordDto;
import com.xsungroup.tms.user.dto.CarBoundDriverDto;
import com.xsungroup.tms.user.entity.AuditRecordEntity;
import com.xsungroup.tms.user.entity.UserEntity;
import com.xsungroup.tms.user.entity.UserOrgEntity;
import com.xsungroup.tms.user.mapper.AuditRecordDao;
import com.xsungroup.tms.user.mapper.DriverDao;
import com.xsungroup.tms.user.mapper.UserDao;
import com.xsungroup.tms.user.mapper.UserOrgDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 车辆表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2019-08-06
 */
@Service("carService")
public class CarServiceImpl extends ServiceImpl<CarDao, CarEntity> implements CarService {

    @Autowired
    private CarOrgDao carOrgDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private VehicleCateDao vehicleCateDao;

    @Autowired
    private CarDriverDao carDriverDao;

    @Autowired
    private UserOrgDao userOrgDao;

    @Autowired
    private DriverDao driverDao;

    @Autowired
    private AuditRecordDao auditRecordDao;

    @Override
    @Transactional
    public boolean addCar(CarDto carDto) {
        CarEntity carEntity = new CarEntity();
        BeanUtils.copyProperties(carDto, carEntity);
        //新增，配置那几个字段，直接insert即可
        carEntity.setGmtCreate(LocalDateTime.now());
        carEntity.setFirstSubmitTime(LocalDateTime.now());//首次提交时间，后面的审核会用的到
        carEntity.setCarOrigin(0);
        carEntity.setAuditStatus(0);
        carEntity.setCarStatus(0);
        baseMapper.insert(carEntity);
//        if (carDto.getBoundDriver() != null && carDto.getBoundDriver().size() > 0) {
//            List<CarDriverEntity> list = new ArrayList<>();
//            for (CarDriverDto carDriverDto : carDto.getBoundDriver()) {
//                CarDriverEntity carDriverEntity = new CarDriverEntity();
//                BeanUtils.copyProperties(carDriverDto, carDriverEntity);
//                carDriverEntity.setCarId(carEntity.getCarId());
//                carDriverEntity.setCreateUser(carEntity.getCreateUser());
//                list.add(carDriverEntity);
//            }
//            carDriverDao.addBatchData(list);
//        }
        return true;
    }


    @Override
    public boolean addCheck(CarDto carDto) {
        QueryWrapper<CarEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("is_able",1);
        wrapper.eq("audit_status",2);
        if(carDto.getCarNo() != null && carDto.getCarNo().toString().length() > 0){
            //这时候验证车牌号
            wrapper.eq("car_no",carDto.getCarNo());
        }
        if(carDto.getVinNo() != null && carDto.getVinNo().toString().length() > 0){
            //这时候验证车架号
            wrapper.eq("vin_no",carDto.getVinNo());
        }
        if(StringUtils.isNotBlank(carDto.getRoadTransCertificateProvince()) && StringUtils.isNotBlank(carDto.getRoadTransCertificateWord()) && StringUtils.isNotBlank(carDto.getRoadTransCertificateCode())){
            //这时候验证道路运输证
            wrapper.eq("road_trans_certificate_province",carDto.getRoadTransCertificateProvince());
            wrapper.eq("road_trans_certificate_word",carDto.getRoadTransCertificateWord());
            wrapper.eq("road_trans_certificate_code",carDto.getRoadTransCertificateCode());
        }
        if(baseMapper.selectCount(wrapper) > 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean boundDriver(BoundDriverDto boundDriverDto) {
        List<CarDriverEntity> carDriverEntityList = new ArrayList<>();
        List<CarBoundDriverDto> list = boundDriverDto.getDriverDtos();
        for(CarBoundDriverDto carBoundDriverDto : list){
            CarDriverEntity carDriverEntity = new CarDriverEntity();
            carDriverEntity.setCarId(boundDriverDto.getCarId());
            carDriverEntity.setDriverId(carBoundDriverDto.getDriverId());
            carDriverEntity.setIsDefault(carBoundDriverDto.getIsDefault());
            carDriverEntity.setDriverName(carBoundDriverDto.getDriverName());
            carDriverEntityList.add(carDriverEntity);
        }
        carDriverDao.addBatchData(carDriverEntityList);
        return true;
    }

    @Override
    public boolean cancelBoundDriver(CarDriverDto carDriverDto) {
        QueryWrapper<CarDriverEntity>  wrapper = new QueryWrapper<>();
        wrapper.eq("car_id",carDriverDto.getCarId());
        wrapper.eq("driver_id",carDriverDto.getDriverId());
        CarDriverEntity carDriverEntity = carDriverDao.selectOne(wrapper);
        if(carDriverEntity == null){
            throw new BussException(CarBusCode.SELECT_NO_DATA);
        }
        carDriverEntity.setIsAble(0);
        carDriverDao.updateById(carDriverEntity);
        return true;
    }

    @Override
    @Transactional
    public boolean isDefaultDriver(BoundDriverDto boundDriverDto) {
        QueryWrapper<CarDriverEntity>  wrapper = new QueryWrapper<>();
        wrapper.eq("car_id",boundDriverDto.getCarId());
        wrapper.eq("is_able",1);
        wrapper.eq("is_default",1);
        List<CarDriverEntity>  list = carDriverDao.selectList(wrapper);
        if(list != null && list.size() > 0){
            //因为只能有一个默认的  所以这次设置了一个默认的  就要把别的数据取消掉
            for(CarDriverEntity carDriverEntity : list){
                carDriverEntity.setIsDefault(0);//不是默认的
                carDriverDao.updateById(carDriverEntity);
            }
        }
        CarDriverEntity carDriverEntity = new CarDriverEntity();
        carDriverEntity.setIsDefault(1);
        carDriverEntity.setCarId(boundDriverDto.getCarId());
        carDriverEntity.setDriverId(boundDriverDto.getDriverDtos().get(0).getDriverId());
        carDriverEntity.setDriverName(boundDriverDto.getDriverDtos().get(0).getDriverName());
        carDriverDao.insert(carDriverEntity);
        return true;
    }


    //取消关联
    @Override
    public boolean cancleAssociation(String orgId, List<String> list) {
        if(list == null || list.size() == 0){
            throw new BussException(CarBusCode.SELECT_DATA);
        }
        QueryWrapper<CarOrgEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("is_able",1);
        wrapper.eq("org_id",orgId);
        wrapper.in("car_id",list);
        List<CarOrgEntity>  carOrgEntityList = carOrgDao.selectList(wrapper);
        if(carOrgEntityList == null || carOrgEntityList.size() == 0){
            throw new BussException(CarBusCode.SELECT_NO_DATA);
        }
        for(CarOrgEntity carOrgEntity : carOrgEntityList){
            UpdateWrapper<CarOrgEntity>  update = new UpdateWrapper<>();
            update.set("is_able",0);
            carOrgDao.update(carOrgEntity,update);
        }
        return true;
    }


    @Override
    public boolean deleBatchIds(List<String> carIds) {
        if (carIds == null || carIds.size() == 0) {
            throw new BussException(CarBusCode.SELECT_DATA);
        }
        QueryWrapper<CarEntity> wrapper = new QueryWrapper<>();
        wrapper.in("car_id", carIds);
        wrapper.eq("is_able", 1);
        List<CarEntity> carEntityList = baseMapper.selectList(wrapper);
        if (carEntityList == null || carEntityList.size() == 0) {
            throw new BussException(CarBusCode.SELECT_NO_DATA);
        }
        List<String> ids = new ArrayList<>();
        for (CarEntity carEntity : carEntityList) {
            ids.add(carEntity.getCarId());
        }
        removeByIds(ids);
        return true;
    }

    @Override
    public boolean editCar(String userId, CarDto carDto) {
        CarEntity carEntity = new CarEntity();
        BeanUtils.copyProperties(carDto, carEntity);
        //修改，直接update即可
        //更新时间采用的是数据库时间  不用管
        UserEntity userEntity = userDao.selectById(userId);
        if (userEntity != null) {
            carEntity.setUpdateUser(userEntity.getUserId());
            carEntity.setUpdateUser(userEntity.getUserId());
        }
        carEntity.setAuditStatus(0);
        baseMapper.updateById(carEntity);
        return true;
    }

    @Override
    public List<VehicleCateEntity> vehicleCate(String vehicleCate) {
        if (StringUtils.isBlank(vehicleCate)) {
            throw new BussException(CarBusCode.SELECT_CAR_CATE);
        }
        QueryWrapper<VehicleCateEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("vehicle_type", Integer.parseInt(vehicleCate));
        wrapper.eq("is_able", 1);
        List<VehicleCateEntity> list = vehicleCateDao.selectList(wrapper);
        return list;
    }

    @Override
    public boolean outSourceCar(String userId, CarOrgDto carOrgDto) {
        UserEntity user = userDao.selectById(userId);
        if (user == null) {
            throw new BussException(CarBusCode.NOT_FIND_USER);
        }
        CarOrgEntity carOrgEntity = new CarOrgEntity();
        BeanUtils.copyProperties(carOrgDto, carOrgEntity);
        carOrgEntity.setCreateUser(user.getCreateUser());
        carOrgEntity.setOrgId(user.getOrgId());
        carOrgDao.insert(carOrgEntity);
        return true;
    }

    @Override
    public QueryWrapper<CarEntity> outSourceList(String userId, String carNo, String carType, String carSort) {
        UserEntity user = userDao.selectById(userId);
        if (user == null) {
            throw new BussException(CarBusCode.NOT_FIND_USER);
        }

        QueryWrapper<CarEntity> qw = new QueryWrapper<>();
        qw.eq("is_able", 1);
        qw.ne("car_origin", 0);//不是自有车
        if(carNo != null && carNo.length() > 0){
            qw.like("car_no", carNo);
        }
        if(carType != null && carType.length() > 0){
            qw.eq("car_type", carType);
        }
        if(carSort != null && carSort.length() > 0){
            qw.eq("car_sort", carSort);
        }

        QueryWrapper<UserOrgEntity> userOrgWrapper = new QueryWrapper<>();
        userOrgWrapper.eq("is_able", 1);
        userOrgWrapper.eq("user_id", user.getUserId());
        List<UserOrgEntity> userOrgEntityList = userOrgDao.selectList(userOrgWrapper);
        if (userOrgEntityList != null && userOrgEntityList.size() > 0) {
            List<String> orgIds = new ArrayList<>();//能查看的组织的id
            for (UserOrgEntity userOrgEntity : userOrgEntityList) {
                orgIds.add(userOrgEntity.getOrgId());
            }
            QueryWrapper<CarOrgEntity> carOrgWrapper = new QueryWrapper<>();
            carOrgWrapper.in("org_id",orgIds);
            List<CarOrgEntity> carOrgEntityList = carOrgDao.selectList(carOrgWrapper);
            if(carOrgEntityList != null && carOrgEntityList.size() > 0){
                List<String> carIds = new ArrayList<>();//能查看的车的id
                for(CarOrgEntity carOrgEntity : carOrgEntityList){
                    carIds.add(carOrgEntity.getCarId());
                }
                qw.in("car_id",carIds);
            }
        }
        return qw;
    }


    @Override
    @Transactional
    public boolean auditRefuse(AuditRecordDto auditRecordDto) {
        AuditRecordEntity auditRecordEntity = new AuditRecordEntity();
        BeanUtils.copyProperties(auditRecordDto,auditRecordEntity);
        auditRecordEntity.setAuditMemo("被拒绝");
        auditRecordDao.insert(auditRecordEntity);
        CarEntity car = baseMapper.selectById(auditRecordDto.getAuditObjId());
        car.setAuditStatus(1);//审核拒绝
        baseMapper.updateById(car);
        return true;
    }


    @Override
    @Transactional
    public boolean auditSuccess(CarDto carDto) {
        CarEntity car = baseMapper.selectById(carDto.getCarId());
        BeanUtils.copyProperties(carDto,car);
        car.setAuditStatus(2);//审核通过
        AuditRecordEntity auditRecordEntity = new AuditRecordEntity();
        auditRecordEntity.setAuditMemo("审核通过");
//        auditRecordEntity.setRefuseReason();
        auditRecordDao.insert(auditRecordEntity);
        return true;
    }


    @Override
    @Transactional
    public boolean auditCancel(AuditRecordDto auditRecordDto) {
        CarEntity car = baseMapper.selectById(auditRecordDto.getAuditObjId());
        car.setAuditStatus(0);//取消审核
        baseMapper.insert(car);
        //取消审核时也需要在审核记录表查数据
        AuditRecordEntity auditRecordEntity = new AuditRecordEntity();
        auditRecordEntity.setAuditMemo("取消审核");
        auditRecordEntity.setRefuseReason(auditRecordDto.getRefuseReason());
        auditRecordDao.insert(auditRecordEntity);
        return true;
    }



}
