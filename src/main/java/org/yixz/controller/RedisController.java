package org.yixz.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yixz.service.RedisOptService;

@Api(tags = "redis操作")
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisOptService redisOptService;

    @ApiOperation("设置字符串")
    @GetMapping("/setString")
    public void setString(@RequestParam String key, @RequestParam String value) {
        redisOptService.set(key, value);
    }

    @ApiOperation("根据key查询")
    @GetMapping("/get")
    public String get(@RequestParam String key) {
        Object obj = redisOptService.get(key);
        return obj==null?null:obj.toString();
    }
}
