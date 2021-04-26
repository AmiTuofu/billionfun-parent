package com.billionfun.user.service.impl;

import com.billionfun.common.entity.Customer;
import com.billionfun.dao.CustomerMapper;
import com.billionfun.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/1 10:50
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private CustomerMapper customerDao;

    @Override
    public List<Customer> getAll() {
        List list = customerDao.getAll();
        return list;
    }
}