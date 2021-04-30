package com.billionfun.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.billionfun.common.entity.OrderItem;
import com.billionfun.common.utils.OrderUtil;
import com.billionfun.common.vo.*;
import com.billionfun.dao.OrderItemMapper;
import com.billionfun.feignapi.customercenter.CustomerCenterFeignApi;
import com.billionfun.feignapi.productcenter.ProductCenterFeignApi;
import com.billionfun.order.service.OrderService;
import com.billionfun.common.entity.Order;

import com.billionfun.dao.OrderMapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/1 11:18
 */
@Service
public class OrderServiceImpl implements OrderService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private CustomerCenterFeignApi customerCenterFeignApi;

    @Autowired
    private ProductCenterFeignApi productCenterFeignApi;


    @Override
    public OrderVo getOrderById(Long id) {
        OrderVo orderVo = new OrderVo();
        Order order = orderMapper.getOrderById(id);
        BeanUtils.copyProperties(order,orderVo);
        CustomerVo customerVo = customerCenterFeignApi.getCustomerById(order.getCustomerId());
        orderVo.setCustomerVo(customerVo);
        orderVo.setOrderItemVoList(getOrderItemByOrderId(order.getId()));
        return orderVo;
    }

    @Override
    @GlobalTransactional(name = "prex-create-order",rollbackFor = Exception.class)
    public boolean createOrder(OrderVo orderVo) {
        Random random=new Random();
        Order order = new Order();
        order.setOrderNo(OrderUtil.getOrderIdByTime());
        order.setTotalPrice(Double.parseDouble(random.nextInt(1000)+""));
        order.setDiscountPrice(Double.parseDouble(random.nextInt(100)+""));
        order.setRealPrice(order.getTotalPrice()-order.getDiscountPrice());
        order.setOrderStatus(1);
        order.setCreateDate(new Date());
        order.setStatus(1);
        order.setCustomerId(1l);

        orderMapper.saveOrder(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(order.getId());
        orderItem.setCreateDate(new Date());
        orderItem.setDiscountPrice(order.getDiscountPrice());
        orderItem.setGoodsId(10000001l);
        orderItem.setPrice(order.getTotalPrice());

        ProductVo productVo = productCenterFeignApi.getProductById(1000000l);
        logger.info(JSON.toJSONString(productVo));

        orderItem.setProductId(1000000l);
        orderItem.setProductName(productVo.getName());
        orderItem.setQty(1);
        orderItem.setRealPrice(order.getRealPrice());
        orderItem.setStatus(1);
        orderItem.setTotalPrice(order.getTotalPrice());
        orderItemMapper.saveOrderItem(orderItem);
        logger.info("创建订单成功,orderId:{}",order.getId());
        orderVo.setId(order.getId());





        productCenterFeignApi.subtractStock(10000001l);
        logger.info("扣减库存成功");

 //       System.out.println(1/0);      测试分布式事务

        order.setOrderStatus(2);
        order.setLastModifyDate(new Date());
        orderMapper.updateOrder(order);


        return true;
    }

    @Override
    public List<OrderItemVo> getOrderItemByOrderId(Long orderId) {
        List<OrderItemVo> returnList = new ArrayList<>();
        List<OrderItem> orderItemList = orderItemMapper.getOrderItemByOrderId(orderId);
        for (int i =0;i<orderItemList.size();i++){
            OrderItem orderItem = orderItemList.get(i);
            OrderItemVo orderItemVo = new OrderItemVo();
            BeanUtils.copyProperties(orderItem,orderItemVo);

            ProductVo productVo = productCenterFeignApi.getProductById(orderItem.getProductId());
            orderItemVo.setProductVo(productVo);

            GoodsVo goodsVo = productCenterFeignApi.getGoodsById(orderItem.getGoodsId());
            orderItemVo.setGoodsVo(goodsVo);

            returnList.add(orderItemVo);

        }

        return returnList;
    }
}