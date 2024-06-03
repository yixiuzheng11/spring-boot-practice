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
public class RouteVo {
    @Schema(description = "菜单")
    private List<MenuRouteVo> menuList;

    @Schema(description = "按钮权限")
    private List<String> permList;
}
