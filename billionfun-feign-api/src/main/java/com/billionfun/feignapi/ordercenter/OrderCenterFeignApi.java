package com.billionfun.feignapi.ordercenter;

import com.billionfun.common.entity.Order;
import com.billionfun.config.OrderCenterFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/1 19:53
 */
@FeignClient(name = "order-center",configuration = OrderCenterFeignConfig.class)
public interface OrderCenterFeignApi {
    @RequestMapping("/order/{orderId}")
    Order getCustomerById(@PathVariable("orderId") Long customerId);
}