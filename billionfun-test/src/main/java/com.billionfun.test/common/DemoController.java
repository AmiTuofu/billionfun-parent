package com.billionfun.test.common;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2020/1/14 11:18
 */
@RestController
public class DemoController {

    @RequestMapping("/hello")
    public String test(){
        return "hello,world!";
    }
}