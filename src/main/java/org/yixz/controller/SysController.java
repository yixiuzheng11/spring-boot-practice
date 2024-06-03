package org.yixz.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.yixz.entity.vo.LoginVo;
import org.yixz.entity.vo.RouteVo;
import org.yixz.service.LoginService;
import org.yixz.service.SysMenuService;
import javax.annotation.Resource;

/**
 * 描述
 *
 * @author yixiuzheng11
 * @date 2021年11月25日 19:05
 */
@Tag(name = "系统管理")
@RestController
@RequestMapping("/sys")
@Slf4j
public class SysController {
    @Resource
    private LoginService loginService;

    @Resource
    private SysMenuService menuService;

    @Operation(summary = "获取用户登录信息")
    @GetMapping("/getLoginInfo")
    public LoginVo getLoginInfo() {
        return loginService.getLoginInfo();
    }

    @Operation(summary = "导航菜单")
    @GetMapping("/getMenuRoute")
    public RouteVo getMenuRoute() {
        return menuService.getMenuRoute();
    }
}
