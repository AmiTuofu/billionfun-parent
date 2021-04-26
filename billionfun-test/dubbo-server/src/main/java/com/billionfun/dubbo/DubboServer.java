package com.billionfun.dubbo;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/2/23 17:37
 */
public class DubboServer {
    public static void main(String[] args) throws IOException {
        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-server-test");
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(-1);//20880
        RegistryConfig registryConfig = new RegistryConfig("N/A");
        ServiceConfig serviceConfig = new ServiceConfig();
        serviceConfig.setInterface(UserService.class);
        serviceConfig.setRef(new UserServiceImpl());
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.export();
        System.out.println("服务已经暴露");
        System.in.read();
    }
}