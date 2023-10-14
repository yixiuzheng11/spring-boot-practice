package org.yixz.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.Date;
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

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "菜单路径")
    private String url;

    @Schema(description = "授权")
    private String perm;

    @Schema(description = "菜单类型，node-目录，menu-菜单，btn-按钮")
    private String menuType;

    @Schema(description = "排序")
    private Integer sortNo;

    @Schema(description = "子菜单")
    private List<SysMenuVo> children;

    private Date createdDate;
}
