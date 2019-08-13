package com.xsungroup.tms.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsungroup.tms.order.entity.OrderStatusLogEntity;
import com.xsungroup.tms.order.mapper.OrderStatusLogMapper;
import com.xsungroup.tms.order.service.OrderStatusLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单状态变化日志 服务实现类
 * </p>
 *
 * @author admin
 * @since 2019-08-12
 */
@Service
public class OrderStatusLogServiceImpl extends ServiceImpl<OrderStatusLogMapper, OrderStatusLogEntity> implements OrderStatusLogService {

}
