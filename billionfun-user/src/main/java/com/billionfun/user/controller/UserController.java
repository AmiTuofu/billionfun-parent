package com.billionfun.user.controller;

import com.billionfun.common.entity.Customer;
import com.billionfun.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/1 10:52
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getList")
    public List<Customer> getList(){
        List list = userService.getAll();
        return list;
    }
}