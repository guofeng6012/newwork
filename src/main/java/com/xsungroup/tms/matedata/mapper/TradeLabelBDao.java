package com.xsungroup.tms.matedata.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xsungroup.tms.matedata.entity.TradeLabelBEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 行业标签子表 Mapper 接口
 * </p>
 *
 * @author Alex
 * @since 2019-07-22
 */
public interface TradeLabelBDao extends BaseMapper<TradeLabelBEntity> {

    void  addBatchData(@Param("list") List<TradeLabelBEntity> list);


    void  editBatchData(@Param("list") List<TradeLabelBEntity> list);


    List<Map<String,String>>  selectSubIds(@Param("list") List<String> list);


}
