package com.billionfun.order.controller;

import com.alibaba.fastjson.JSON;
import com.billionfun.order.service.OrderService;
import com.billionfun.common.entity.Customer;
import com.billionfun.common.entity.Order;
import com.billionfun.common.vo.CustomerVo;
import com.billionfun.common.vo.OrderVo;
import com.billionfun.feignapi.customercenter.CustomerCenterFeignApi;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/1 11:18
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private CustomerCenterFeignApi customerCenterFeignApi;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/order/{orderId}")
    public OrderVo getOrder(@PathVariable("orderId") Long orderId){
        OrderVo orderVo = orderService.getOrderById(orderId);

        stringRedisTemplate.opsForValue().set("order"+orderId, JSON.toJSONString(orderVo));
        return orderVo;
    }

    @RequestMapping("/order/create")
    public OrderVo create(OrderVo order){

        orderService.createOrder(order);

        OrderVo orderVo = orderService.getOrderById(order.getId());

        stringRedisTemplate.opsForValue().set("order"+order.getId(), JSON.toJSONString(order.getOrderNo()));
        return orderVo;
    }

    @GetMapping("/getServiceList")
    public List<ServiceInstance> getServiceList() {
        List<ServiceInstance> serviceInstanceList =  discoveryClient.getInstances("order-center");
        return serviceInstanceList;
    }
}