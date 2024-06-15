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
@Schema(description = "MenuRouteVo")
public class MenuRouteVo {
    @Schema(description = "菜单id")
    private Integer id;

    @Schema(description = "父Id")
    private Integer parentId;

    @Schema(description = "路由名称")
    private String name;

    @Schema(description = "路由地址")
    private String path;

    @Schema(description = "组件地址")
    private String component;

    /*@Schema(description = "菜单类型，menu-菜单，func-功能，btn-按钮")
    private String type;*/

    @Schema(description = "子菜单")
    private List<MenuRouteVo> children;

    private MenuRouteMeta meta;

    @Data
    public static class MenuRouteMeta {
        @Schema(description = "菜单名称")
        private String title;

        @Schema(description = "菜单图标")
        private String icon;

        @Schema(description = "授权")
        private String permission;

        @Schema(description = "是否隐藏")
        private Boolean hidden = false;

        @Schema(description = "排序")
        private Integer sortNo;
    }
}
