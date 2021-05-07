package com.billionfun.web.security.controller;

import com.billionfun.common.annotation.rest.AnonymousGetMapping;
import com.billionfun.common.utils.Result;
import com.billionfun.common.utils.ResultGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuyi
 * @since 2021/5/7 3:13 下午
 */
@RestController
public class DemoController {

    @GetMapping("/test1")
    @AnonymousGetMapping
    public Result test1(){
      //  int i = 1/0;
        return ResultGenerator.genSuccessResult();
    }
}
