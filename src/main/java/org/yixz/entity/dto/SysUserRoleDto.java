package org.yixz.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描述
 *
 * @author yixiuzheng11
 * @date 2021年07月22日 17:04
 */
@Data
@Schema(description = "用户角色请求参数")
public class SysUserRoleDto extends BaseDto{
    @Schema(description = "用户角色关联id")
    private Integer id;

    @Schema(description = "用户id")
    private Integer userId;

    @Schema(description = "角色id")
    private Integer roleId;
}
