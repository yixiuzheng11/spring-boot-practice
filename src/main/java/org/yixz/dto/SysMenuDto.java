package org.yixz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 描述
 *
 * @author yixiuzheng11
 * @date 2021年07月22日 17:04
 */
@Data
@ApiModel("菜单请求参数")
public class SysMenuDto {
    @ApiModelProperty(value = "菜单id")
    private Integer id;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "父id")
    private Integer parentId;

    @ApiModelProperty(value = "菜单路径")
    private String url;

    @ApiModelProperty(value = "菜单类型")
    private String menuType;

    @ApiModelProperty(value = "权限")
    private String perms;

}
