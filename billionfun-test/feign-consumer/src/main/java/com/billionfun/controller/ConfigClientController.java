package com.billionfun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuyi
 * @since 2021/4/27 10:05 上午
 */
@RestController
public class ConfigClientController {
   // @Value("${profile}")
    private String profile;

    @GetMapping("/profile")
    public String hello() {
        return this.profile;
    }
}
