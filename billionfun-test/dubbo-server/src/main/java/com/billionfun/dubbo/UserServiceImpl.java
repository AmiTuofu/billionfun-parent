package com.billionfun.dubbo;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/2/23 17:33
 */
@Service
@Component
public class UserServiceImpl implements UserService {

    @Override
    public User getUser(String username) {
        User user = new User();
        user.setId(1l);
        user.setUsername("zhuyi");
        return user;
    }
}