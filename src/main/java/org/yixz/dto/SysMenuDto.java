package org.yixz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描述
 *
 * @author yixiuzheng11
 * @date 2021年07月22日 17:04
 */
@Data
@Schema(description = "菜单请求参数")
public class SysMenuDto extends BaseDto{
    @Schema(description = "菜单id")
    private Integer id;

    @Schema(description = "菜单名称")
    private String menuName;

    @Schema(description = "父id")
    private Integer parentId;

    @Schema(description = "菜单路径")
    private String url;

    @Schema(description = "菜单类型")
    private String menuType;

    @Schema(description = "权限")
    private String perms;

}
