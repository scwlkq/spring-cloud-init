package com.example.userserver.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/test")
@Api("测试")
public class TestController {

    @Value("${msg}")
    private String msg;

    @ApiOperation("测试方法")
    @GetMapping("/hello")
    public String hello(){
        return msg;
    }
}
