package org.yixz.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

/**
 * 描述
 *
 * @date 2021年12月23日 9:45
 */
@Data
@Schema(description = "SysMenuVo")
public class SysMenuVo {
    @Schema(description = "菜单id")
    private Integer id;

    @Schema(description = "父id")
    private Integer parentId;

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "路由")
    private String path;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "组件路径")
    private String component;

    @Schema(description = "授权")
    private String permission;

    @Schema(description = "菜单类型，CATALOG-目录，MENU-菜单，BTN-按钮")
    private String type;

    @Schema(description = "排序")
    private Integer sortNo;

    @Schema(description = "1-可见，0-隐藏")
    private Integer visible = 1;

    @Schema(description = "子菜单")
    private List<SysMenuVo> children;
}
