package org.yixz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描述
 *
 * @date 2021年12月15日 19:11
 */
@Data
public class UserRolePermVo {
    @Schema(description="用户id")
    private Integer userId;

    @Schema(description="用户名")
    private String userName;

    @Schema(description="密码")
    private String password;

    @Schema(description="角色编码")
    private String roleCode;

    @Schema(description="权限标识")
    private String perms;
}
