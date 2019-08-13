package com.xsungroup.tms.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsungroup.tms.order.dto.OrderCreateDto;
import com.xsungroup.tms.order.entity.OrderEntity;
import com.xsungroup.tms.order.mapper.OrderMapper;
import com.xsungroup.tms.order.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2019-08-12
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService {

    @Override
    @Transactional
    public String create(OrderCreateDto t) {




        return null;
    }
}
