package org.yixz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 描述
 *
 * @author YIXIUZHENG741
 * @date 2021年07月22日 17:04
 */
@Data
@ApiModel("用户角色请求参数")
public class UserRoleDto extends BaseDto{
    @ApiModelProperty(value = "用户角色关联id")
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;
}
