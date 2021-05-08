package com.billionfun.web.security.controller;

import com.billionfun.common.annotation.rest.AnonymousGetMapping;
import com.billionfun.common.utils.Result;
import com.billionfun.common.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuyi
 * @since 2021/5/7 3:13 下午
 */
@RestController
public class DemoController {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("/test1")
    @AnonymousGetMapping
    public Result test1(){
      //  int i = 1/0;
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/kafka/normal/{message}")
    @AnonymousGetMapping
    public void sendMessage1(@PathVariable("message") String normalMessage) {
        kafkaTemplate.send("topic1", normalMessage);
    }

}
