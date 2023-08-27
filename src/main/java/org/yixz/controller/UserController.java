package org.yixz.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.yixz.dto.UserDto;
import org.yixz.dto.record.UserRecord;
import org.yixz.entity.User;
import org.yixz.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述
 *
 * @author YIXIUZHENG741
 * @date 2021年11月25日 19:05
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/sys/user")
public class UserController {
    @Resource
    private UserServiceImpl userService;

    @ApiOperation("用户查询")
    @PostMapping("/getPage")
    public Page<User> getPage(@RequestBody UserDto userDto) {
        return userService.getPage(userDto);
    }

    @ApiOperation("用户新增")
    @PostMapping("/add")
    public Integer add(@RequestBody UserDto userDto) {
        return userService.add(userDto);
    }

    @ApiOperation("用户修改")
    @PostMapping("/update")
    public void update(@RequestBody UserDto userDto) {
        userService.update(userDto);
    }

    @ApiOperation("用户删除")
    @PostMapping("/delete")
    public void delete(@RequestBody UserDto userDto) {
        userService.delete(userDto.getId());
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
