package com.billionfun.dao;

import com.billionfun.common.entity.Order;
import com.billionfun.common.entity.OrderItem;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/1 10:01
 */
public interface OrderItemMapper {
    @Select("select * from order_item where order_id = #{orderId}")
    @Results({ @Result(property = "orderId", column = "order_id"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "goodsId", column = "goods_id"),
            @Result(property = "productName", column = "product_name"),
            @Result(property = "totalPrice", column = "total_price"),
            @Result(property = "discountPrice", column = "discount_price"),
            @Result(property = "realPrice", column = "real_price"),
            @Result(property = "createDate", column = "create_date")
    })
    List<OrderItem> getOrderItemByOrderId(Long orderId);

    Integer saveOrderItem(OrderItem orderItem);
}