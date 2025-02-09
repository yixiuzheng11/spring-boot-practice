package org.yixz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yixz.entity.dto.SysDictDto;
import org.yixz.entity.dto.SysRoleDto;
import org.yixz.entity.mysql.SysRole;
import org.yixz.entity.vo.SysDictVo;
import org.yixz.entity.vo.SysRoleVo;
import org.yixz.service.SysRoleService;

import java.util.Optional;

/**
 * 描述
 *
 * @author yixiuzheng11
 * @date 2021年11月25日 19:05
 */
@Tag(name = "角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @Operation(summary = "分页查询角色")
    @PostMapping("/getPage")
    public IPage<SysRoleVo> getPage(@RequestBody SysRoleDto dto) {
        return sysRoleService.getPage(dto);
    }

    @Operation(summary = "根据id查询角色")
    @GetMapping("/getById")
    public SysRoleVo getById(@RequestParam Integer id) {
        SysRole sysRole = sysRoleService.getById(id);
        return Optional.ofNullable(sysRole).map(item->{
            SysRoleVo sysRoleVo = new SysRoleVo();
            BeanUtils.copyProperties(item, sysRoleVo);
            return sysRoleVo;
        }).orElse(null);
    }
}
