package com.billionfun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/1 10:53
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableConfigServer
public class BilliionfunConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BilliionfunConfigServerApplication.class,args);
    }
}