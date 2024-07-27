package org.yixz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yixz.entity.dto.SysDictDto;
import org.yixz.entity.vo.SysDictVo;

/**
 * 描述
 *
 * @author yixiuzheng11
 * @date 2021年11月25日 19:05
 */
@Tag(name = "字典管理")
@RestController
@RequestMapping("/dict")
public class DictController {
    @Operation(summary = "用户查询")
    @PostMapping("/getPage")
    public Page<SysDictVo> getPage(@RequestBody SysDictDto dto) {
        return new Page<>();
    }
}
