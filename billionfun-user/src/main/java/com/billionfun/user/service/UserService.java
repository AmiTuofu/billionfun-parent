package com.billionfun.user.service;

import com.billionfun.common.entity.Customer;

import java.util.List;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/1 10:45
 */
public interface UserService {
    List<Customer> getAll();
}