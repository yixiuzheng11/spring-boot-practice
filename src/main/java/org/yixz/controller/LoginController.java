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
import org.yixz.entity.vo.LoginVo;
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
@RequestMapping("/auth")
@Slf4j
public class LoginController {
    @Resource
    private LoginService loginService;

    @Autowired
    private CaptchaService captchaService;

    @Operation(summary = "登录")
    @PostMapping("/login")
    public LoginVo login(@Validated @RequestBody LoginDto dto) {
        //log.info("登录信息----", JSON.toJSONString(dto));
        return loginService.doLogin(dto);
    }

    @Operation(summary = "获取图形验证码")
    @GetMapping("/getCaptcha")
    public CaptchaVo getCaptcha() {
        return captchaService.generateCaptcha();
    }
}
