package org.yixz.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.yixz.entity.dto.SysMenuDto;
import org.yixz.entity.vo.SysMenuVo;
import org.yixz.service.SysMenuService;
import javax.annotation.Resource;
import java.util.List;

/**
 * 描述
 *
 * @author yixiuzheng11
 * @date 2021年11月25日 19:05
 */
@Tag(name = "菜单管理")
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private SysMenuService menuService;

    @Operation(summary = "菜单查询")
    @PostMapping("/getMenuList")
    public List<SysMenuVo> getMenuList(@RequestBody SysMenuDto dto) {
        return menuService.getMenuList(dto);
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
}
