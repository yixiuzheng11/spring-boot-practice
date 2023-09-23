package org.yixz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描述
 *
 * @date 2021年12月23日 9:45
 */
@Data
@Schema(description = "用户信息")
public class SysUserVo {
    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String fullName;
}
