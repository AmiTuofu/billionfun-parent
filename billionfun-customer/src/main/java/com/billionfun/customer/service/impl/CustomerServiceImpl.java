package com.billionfun.customer.service.impl;

import com.billionfun.common.entity.Customer;
import com.billionfun.customer.service.CustomerService;
import com.billionfun.dao.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/1 11:18
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<Customer> getAll() {
        List list = customerMapper.getAll();
        return list;
    }

    @Override
    public Customer getCustomerById(Long id) {
        Customer customer = customerMapper.getCustomerById(id);
        return customer;
    }
}