package org.yixz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.yixz.dto.MenuDto;
import org.yixz.entity.Menu;
import org.yixz.service.MenuServiceImpl;
import org.yixz.vo.MenuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

/**
 * 描述
 *
 * @author YIXIUZHENG741
 * @date 2021年11月25日 19:05
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/sys/menu")
public class MenuController {
    @Resource
    private MenuServiceImpl menuService;

    @ApiOperation("菜单分页查询")
    @PostMapping("/getPage")
    public Page<Menu> getPage(@RequestBody MenuDto dto) {
        return menuService.getPage(dto);
    }

    @ApiOperation("菜单新增")
    @PostMapping("/add")
    public Integer add(@RequestBody MenuDto dto) {
        return menuService.add(dto);
    }

    @ApiOperation("菜单修改")
    @PostMapping("/update")
    public void update(@RequestBody MenuDto dto) {
        menuService.update(dto);
    }

    @ApiOperation("菜单删除")
    @PostMapping("/delete")
    public void delete(@RequestBody MenuDto dto) {
        menuService.delete(dto.getId());
    }


}
