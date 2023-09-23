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
@Schema(description = "角色菜单请求参数")
public class SysRoleMenuDto extends BaseDto{
    @Schema(description = "角色菜单关联id")
    private Integer id;

    @Schema(description = "角色id")
    private Integer roleId;

    @Schema(description = "菜单id")
    private Integer menuId;

}
