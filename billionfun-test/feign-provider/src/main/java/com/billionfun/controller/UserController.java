package com.billionfun.controller;

import com.billionfun.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/1/16 17:16
 */
@RestController
public class UserController {

    @RequestMapping ("/getList")
    public List<User> getList(){
        List list = new ArrayList();

        User user1 = new User();
        user1.setId(1);
        user1.setUsername("username1");
        user1.setPassword("password1");
        list.add(user1);
        User user2 = new User();
        user2.setId(2);
        user2.setUsername("username2");
        user2.setPassword("password2");
        list.add(user2);

        return list;
    }

    @RequestMapping ("/user/{id}")
    public User findById(@PathVariable Integer id){

        User user1 = new User();
        user1.setId(id);
        user1.setUsername("username1");
        user1.setPassword("password1");

        return user1;
    }


}