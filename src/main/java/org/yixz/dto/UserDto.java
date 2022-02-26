package org.yixz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 描述
 *
 * @author YIXIUZHENG741
 * @date 2021年07月22日 17:04
 */
@Data
@ApiModel("用户请求参数")
public class UserDto extends BaseDto{
    @ApiModelProperty(value = "用户id")
    private Integer id;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "用户中文名称")
    private String fullName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "菜单类型")
    private String menuType;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createdDate;

    @ApiModelProperty(value = "更新日期")
    private LocalDate updatedDate;
}
