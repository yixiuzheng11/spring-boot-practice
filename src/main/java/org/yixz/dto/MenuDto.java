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
@ApiModel("菜单请求参数")
public class MenuDto extends BaseDto{
    @ApiModelProperty(value = "菜单id")
    private Integer id;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "父id")
    private Integer pid;

    @ApiModelProperty(value = "菜单路径")
    private String url;

    @ApiModelProperty(value = "菜单类型")
    private String type;

    @ApiModelProperty(value = "授权")
    private String perm;

}
