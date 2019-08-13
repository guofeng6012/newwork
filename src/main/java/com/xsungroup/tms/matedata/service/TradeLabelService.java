package com.xsungroup.tms.matedata.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.matedata.entity.TradeLabelEntity;

import java.util.List;

/**
 * <p>
 * 行业标签表 服务类
 * </p>
 *
 * @author Alex
 * @since 2019-07-22
 */
public interface TradeLabelService extends IService<TradeLabelEntity> {


    Object list(IPage<TradeLabelEntity> page, TradeLabelEntity tradeLabelEntity);


    String addOrEdit(TradeLabelEntity tradeLabelEntity) throws BussException;


    TradeLabelEntity getById(TradeLabelEntity tradeLabelEntity) throws BussException;


    String deleteBatchIds(List<String> list) throws BussException;

}
