package org.yixz.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.yixz.dto.SysUserDto;
import org.yixz.dto.record.UserRecord;
import org.yixz.entity.SysUser;
import org.yixz.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述
 *
 * @author yixiuzheng11
 * @date 2021年11月25日 19:05
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/sys/user")
public class UserController {
    @Resource
    private SysUserService userService;

    @ApiOperation("用户查询")
    @PostMapping("/getPage")
    public Page<SysUser> getPage(@RequestBody SysUserDto sysUserDto) {
        return userService.getPage(sysUserDto);
    }

    @ApiOperation("用户新增")
    @PostMapping("/add")
    public Integer add(@RequestBody SysUserDto sysUserDto) {
        return userService.add(sysUserDto);
    }

    @ApiOperation("用户修改")
    @PostMapping("/update")
    public void update(@RequestBody SysUserDto sysUserDto) {
        userService.update(sysUserDto);
    }

    @ApiOperation("用户删除")
    @PostMapping("/delete")
    public void delete(@RequestBody SysUserDto sysUserDto) {
        userService.delete(sysUserDto.getId());
    }

    @ApiOperation("用户导入")
    @PostMapping("/importExcel")
    public void importExcel(@RequestParam("file") MultipartFile file) {
        ImportParams importParams = new ImportParams();
        //表格头部
        importParams.setHeadRows(1);
        List<UserRecord> list = new ArrayList<>();
        try {
            ExcelImportResult<UserRecord> result = ExcelImportUtil.importExcelMore(file.getInputStream(), UserRecord.class, importParams);
            list = result.getList();
        } catch (Exception e) {

        }
        if(CollectionUtils.isNotEmpty(list)) {

        }
    }
}
