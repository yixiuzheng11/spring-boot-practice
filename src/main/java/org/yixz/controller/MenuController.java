package org.yixz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.yixz.dto.SysMenuDto;
import org.yixz.entity.SysMenu;
import org.yixz.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * 描述
 *
 * @author yixiuzheng11
 * @date 2021年11月25日 19:05
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/sys/menu")
public class MenuController {
    @Resource
    private SysMenuService menuService;

    @ApiOperation("菜单分页查询")
    @PostMapping("/getPage")
    public Page<SysMenu> getPage(@RequestBody SysMenuDto dto) {
        return menuService.getPage(dto);
    }

    @ApiOperation("菜单新增")
    @PostMapping("/add")
    public Integer add(@RequestBody SysMenuDto dto) {
        return menuService.add(dto);
    }

    @ApiOperation("菜单修改")
    @PostMapping("/update")
    public void update(@RequestBody SysMenuDto dto) {
        menuService.update(dto);
    }

    @ApiOperation("菜单删除")
    @PostMapping("/delete")
    public void delete(@RequestBody SysMenuDto dto) {
        menuService.delete(dto.getId());
    }


}
