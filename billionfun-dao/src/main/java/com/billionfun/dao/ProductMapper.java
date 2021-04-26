package com.billionfun.dao;

import com.billionfun.common.entity.Order;
import com.billionfun.common.entity.Product;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/1 10:01
 */
public interface ProductMapper {
    @Select("select * from product where id = #{id}")
    @Results({ @Result(property = "brandId", column = "brand_id"),
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "createDate", column = "create_date"),
    })
    Product getProductById(Long id);
}