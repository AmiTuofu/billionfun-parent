package com.billionfun.customer.service;

import com.billionfun.common.entity.Customer;

import java.util.List;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/1 11:18
 */
public interface CustomerService {
    List<Customer> getAll();

    Customer getCustomerById(Long id);
}