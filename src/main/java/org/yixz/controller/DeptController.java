package org.yixz.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yixz.entity.dto.SysDeptDto;
import org.yixz.entity.vo.SysDeptVo;
import org.yixz.service.SysDeptService;
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
@RequestMapping("/dept")
public class DeptController {
    @Resource
    private SysDeptService deptService;

    @Operation(summary = "菜单查询")
    @PostMapping("/getList")
    public List<SysDeptVo> getList(@RequestBody SysDeptDto dto) {
        return deptService.getDeptList(dto);
    }

    @Operation(summary = "菜单新增")
    @PostMapping("/add")
    public Integer add(@RequestBody SysDeptDto dto) {
        return deptService.add(dto);
    }

    @Operation(summary = "菜单修改")
    @PostMapping("/update")
    public void update(@RequestBody SysDeptDto dto) {
        deptService.update(dto);
    }

    @Operation(summary = "菜单删除")
    @PostMapping("/delete")
    public void delete(@RequestBody SysDeptDto dto) {
        deptService.delete(dto.getId());
    }
}
