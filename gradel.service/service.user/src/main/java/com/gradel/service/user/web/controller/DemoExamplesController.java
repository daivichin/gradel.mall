package com.gradel.service.user.web.controller;

import com.gradel.service.order.domain.service.HelloService;
import com.gradel.service.user.domain.service.rocketmq.RocketMQProducerService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 示例
 */
@RestController
public class DemoExamplesController {
    @Reference()
    private HelloService helloService;

    @Autowired
    private RocketMQProducerService service;

    @GetMapping(value = "/examples")
    public void examplesMethod() {
       String result = helloService.hello("examples");
        System.out.println(result);
    }

    @GetMapping(value = "/sendmq")
    public void sendMQ() {
        service.sendMessageMQ();
    }

}
