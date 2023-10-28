package org.yixz.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.yixz.captcha.CaptchaService;
import org.yixz.captcha.domain.CaptchaVo;
import org.yixz.entity.dto.LoginDto;
import org.yixz.service.LoginService;
import javax.annotation.Resource;

/**
 * 描述
 *
 * @author yixiuzheng11
 * @date 2021年11月25日 19:05
 */
@Tag(name = "登录管理")
@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Resource
    private LoginService loginService;

    @Autowired
    private CaptchaService captchaService;

    @Operation(summary = "登录")
    @PostMapping("/doLogin")
    public String doLogin(@Validated @RequestBody LoginDto dto) {
        //log.info("密码：{}", dto.getPwd());
        return loginService.doLogin(dto);
    }

    @Operation(summary = "获取图形验证码")
    @GetMapping("/getCaptcha")
    public CaptchaVo getCaptcha() {
        return captchaService.generateCaptcha();
    }
}
