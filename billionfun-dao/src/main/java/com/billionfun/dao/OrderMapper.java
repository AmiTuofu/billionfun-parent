package com.billionfun.dao;

import com.billionfun.common.entity.Customer;
import com.billionfun.common.entity.Order;
import com.billionfun.common.entity.OrderItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/1 10:01
 */
public interface OrderMapper {
    @Select("select * from `order` where id = #{id}")
    @Results({ @Result(property = "orderNo", column = "order_no"),
            @Result(property = "customerId", column = "customer_id"),
            @Result(property = "totalPrice", column = "total_price"),
            @Result(property = "discountPrice", column = "discount_price"),
            @Result(property = "realPrice", column = "real_price"),
            @Result(property = "orderStatus", column = "order_status"),
            @Result(property = "createDate", column = "create_date")
    })
    Order getOrderById(Long id);


    Integer saveOrder(Order order);

    Integer saveOrderItem(OrderItem orderItem);

    Integer updateOrder(Order order);
}