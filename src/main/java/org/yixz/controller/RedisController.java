package org.yixz.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yixz.common.util.RedisUtil;
import org.yixz.entity.mysql.SysUser;

@Slf4j
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

    @Operation(summary = "设置对象")
    @GetMapping("/setObj")
    public void setObj(@RequestParam String key) {
        SysUser sysUser = new SysUser();
        redisUtil.set(key, sysUser);
        SysUser rUser = (SysUser) redisUtil.get(key);
        log.info("ruser-----");
    }
}
