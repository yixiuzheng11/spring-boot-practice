package org.yixz.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yixz.common.util.RedisUtil;

@Tag(name = "redis操作")
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisUtil redisUtil;

    @Operation(summary = "设置字符串")
    @GetMapping("/setString")
    public void setString(@RequestParam String key, @RequestParam String value) {
        redisUtil.set(key, value);
    }

    @Operation(summary = "根据key查询")
    @GetMapping("/get")
    public String get(@RequestParam String key) {
        Object obj = redisUtil.get(key);
        return obj==null?null:obj.toString();
    }
}
