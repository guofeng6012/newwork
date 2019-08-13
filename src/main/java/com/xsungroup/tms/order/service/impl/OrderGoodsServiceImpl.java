package com.xsungroup.tms.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsungroup.tms.order.entity.OrderGoodsEntity;
import com.xsungroup.tms.order.mapper.OrderGoodsMapper;
import com.xsungroup.tms.order.service.OrderGoodsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单商品信息 服务实现类
 * </p>
 *
 * @author admin
 * @since 2019-08-12
 */
@Service
public class OrderGoodsServiceImpl extends ServiceImpl<OrderGoodsMapper, OrderGoodsEntity> implements OrderGoodsService {

}
