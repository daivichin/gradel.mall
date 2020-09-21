package com.gradel.api.user.domain.controller;

import com.gradel.service.order.domain.service.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 示例
 */
@RestController
public class DemoExamplesController {
    @Reference()
    private HelloService helloService;

    @PostMapping(value = "/user/login")
    public Map<String,String> login(HttpServletRequest request , HttpServletResponse response) {
        System.out.println("this is 8081");
        Map<String,String>  result = new HashMap<>();
        result.put("url","无权限");
        return result;
    }

    @PostMapping(value = "/getUser")
    public Map<String,String> getUser() {
        Map<String,String> result = new HashMap<>(2);
        return result;
    }


}
