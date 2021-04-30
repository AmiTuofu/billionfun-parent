package com.billionfun.dao;

import com.billionfun.common.entity.Customer;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/1 10:01
 */
public interface CustomerMapper {
    @Select("select * from customer")
    List<Customer> getAll();

    @Select("select * from customer where id = #{id}")
    Customer getCustomerById(Long id);
}