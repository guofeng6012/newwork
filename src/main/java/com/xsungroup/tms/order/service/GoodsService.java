package com.xsungroup.tms.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xsungroup.tms.order.dto.GoodsAndPackDto;
import com.xsungroup.tms.order.entity.GoodsEntity;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author admin
 * @since 2019-08-08
 */
public interface GoodsService extends IService<GoodsEntity> {

    void createAndPack(GoodsAndPackDto t);
}
