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
@ApiModel("角色请求参数")
public class RoleDto extends BaseDto{
    @ApiModelProperty(value = "角色id")
    private Integer id;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色编码")
    private String roleCode;
}
