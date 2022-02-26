package org.yixz.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

/**
 * 描述
 *
 * @author YIXIUZHENG741
 * @date 2021年12月23日 9:45
 */
@Data
public class MenuVo {
    @ApiModelProperty(value = "菜单id")
    private Integer id;

    @ApiModelProperty(value = "父id")
    private Integer pid;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "菜单路径")
    private String url;

    @ApiModelProperty(value = "授权")
    private String perm;

    @ApiModelProperty(value = "菜单类型，1-目录，2-菜单，3-按钮")
    private Integer type;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "子菜单")
    private List<MenuVo> children;

    @ApiModelProperty(value = "按钮权限")
    private List<MenuVo> permList;
}
