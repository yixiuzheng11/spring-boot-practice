package org.yixz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.yixz.entity.dto.SysUserDto;
import org.yixz.entity.mysql.SysUser;
import org.yixz.service.SysUserService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * 描述
 *
 * @author yixiuzheng11
 * @date 2021年11月25日 19:05
 */
@Tag(name = "用户管理")
@RestController
@RequestMapping("/sys/user")
public class UserController {
    @Resource
    private SysUserService userService;

    @Operation(summary = "用户查询")
    @PostMapping("/getPage")
    public Page<SysUser> getPage(@RequestBody SysUserDto sysUserDto) {
        return userService.getPage(sysUserDto);
    }

    @Operation(summary = "用户新增")
    @PostMapping("/add")
    public Integer add(@RequestBody SysUserDto sysUserDto) {
        return userService.add(sysUserDto);
    }

    @Operation(summary = "用户修改")
    @PostMapping("/update")
    public void update(@RequestBody SysUserDto sysUserDto) {
        userService.update(sysUserDto);
    }

    @Operation(summary = "用户删除")
    @PostMapping("/delete")
    public void delete(@RequestBody SysUserDto sysUserDto) {
        userService.delete(sysUserDto.getId());
    }
}
