package com.billionfun.dao;

import com.billionfun.common.entity.Goods;
import com.billionfun.common.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/1 10:01
 */
public interface GoodsMapper {
    @Select("select * from goods where id = #{id}")
    @Results({ @Result(property = "productId", column = "product_id"),
            @Result(property = "marketPrice", column = "market_price"),
            @Result(property = "realPrice", column = "real_price"),
            @Result(property = "shelfStatus", column = "shelf_status"),
            @Result(property = "shelfDate", column = "shelf_date"),
            @Result(property = "createDate", column = "create_date")
    })
    Goods getGoodsById(Long id);

    Integer subtractStock( @Param("id")Long id,@Param("count")Integer count);
}