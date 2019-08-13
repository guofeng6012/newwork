package com.xsungroup.tms.order.service;

        import com.baomidou.mybatisplus.extension.service.IService;
        import com.xsungroup.tms.order.dto.OrderCreateDto;
        import com.xsungroup.tms.order.entity.OrderEntity;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author admin
 * @since 2019-08-12
 */
public interface OrderService extends IService<OrderEntity> {

    /**
     * @param t
     * @return 订单ID
     */
    String create(OrderCreateDto t);
}
