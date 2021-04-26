package com.billionfun.order.controller;

import org.springframework.web.client.RestTemplate;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/3/2 18:15
 */
public class OrderControllerTest {
    public static void main(String[] args) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0;i<1000;i++){
            restTemplate.postForObject("http://localhost:8081/order/1",null,String.class);
            Thread.sleep(200);
        }
    }
}