package org.yixz.vo;

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
    private String menuName;

    @Schema(description = "菜单编码")
    private String menuCode;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "菜单路径")
    private String url;

    @Schema(description = "授权")
    private String perms;

    @Schema(description = "菜单类型，0-目录，1-菜单，2-按钮")
    private Integer menuType;

    @Schema(description = "排序")
    private Integer sortNo;

    @Schema(description = "子菜单")
    private List<SysMenuVo> children;

    @Schema(description = "按钮权限")
    private List<SysMenuVo> permList;

    private Date createdDate;
}
