package org.yixz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.yixz.entity.dto.SysMenuDto;
import org.yixz.entity.mysql.SysMenu;
import org.yixz.service.SysMenuService;

import javax.annotation.Resource;

/**
 * 描述
 *
 * @author yixiuzheng11
 * @date 2021年11月25日 19:05
 */
@Tag(name = "菜单管理")
@RestController
@RequestMapping("/sys/menu")
public class MenuController {
    @Resource
    private SysMenuService menuService;

    @Operation(summary = "菜单分页查询")
    @PostMapping("/getPage")
    public Page<SysMenu> getPage(@RequestBody SysMenuDto dto) {
        return menuService.getPage(dto);
    }

    @Operation(summary = "菜单新增")
    @PostMapping("/add")
    public Integer add(@RequestBody SysMenuDto dto) {
        return menuService.add(dto);
    }

    @Operation(summary = "菜单修改")
    @PostMapping("/update")
    public void update(@RequestBody SysMenuDto dto) {
        menuService.update(dto);
    }

    @Operation(summary = "菜单删除")
    @PostMapping("/delete")
    public void delete(@RequestBody SysMenuDto dto) {
        menuService.delete(dto.getId());
    }

    @Operation(summary = "导航菜单那")
    @GetMapping("/getNav")
    public void getNav() {
        menuService.getNav();
    }
}
