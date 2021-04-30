package com.billionfun.order.service;

import com.billionfun.common.entity.Order;
import com.billionfun.common.vo.OrderItemVo;
import com.billionfun.common.vo.OrderVo;

import java.util.List;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/1 11:18
 */
public interface OrderService {
    OrderVo getOrderById(Long id);

    boolean createOrder(OrderVo orderVo);

    List<OrderItemVo> getOrderItemByOrderId(Long id);
}