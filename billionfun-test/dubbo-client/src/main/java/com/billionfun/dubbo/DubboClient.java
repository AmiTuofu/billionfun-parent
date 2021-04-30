package com.billionfun.dubbo;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/2/23 17:47
 */
public class DubboClient {
    public static void main(String[] args) {
        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-client-test");
        ReferenceConfig referenceConfig = new ReferenceConfig();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setInterface(UserService.class);
        referenceConfig.setUrl("dubbo://127.0.0.1:20880/com.billionfun.dubbo.UserService");
        UserService userService = (UserService) referenceConfig.get();
        System.out.println(userService.getUser("1"));
    }
}