package com.billionfun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/1/16 17:14
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class FeignProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignProviderApplication.class,args);
    }

}