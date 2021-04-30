package com.billionfun.controller;

import com.billionfun.api.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/1/16 17:48
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private UserApi userApi;

    @RequestMapping("/getUserList")
    public List getUser(){
        List list = userApi.getList();
        return list;
    }
}