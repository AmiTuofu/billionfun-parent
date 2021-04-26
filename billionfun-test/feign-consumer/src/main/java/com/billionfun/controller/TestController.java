package com.billionfun.controller;

import com.billionfun.api.UserApi;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author zhuyi
 * @since 2021/4/19 3:37 下午
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserApi userApi;

    @GetMapping("/getUserList")
    public List getUserList() {
        List list = this.restTemplate.getForObject("http://localhost:8001/getList", List.class);
        return list;
    }

    @GetMapping("/getUserList2")
    public List getUserList2() {
        List list = this.restTemplate.getForObject("http://feign-provider/getList", List.class);
        return list;
    }

    @HystrixCommand(fallbackMethod = "findByIdFallback")
    @GetMapping("/user/{id}")
    public Object getUserList2(@PathVariable Integer id) throws Throwable {
        Object object = this.userApi.findById(id);
    //    Thread.sleep(20 * 1000);
        return object;
    }

    public Object findByIdFallback(Integer id) {
        return "错误";
    }
}
