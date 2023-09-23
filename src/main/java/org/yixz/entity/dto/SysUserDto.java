package org.yixz.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 描述
 *
 * @author yixiuzheng11
 * @date 2021年07月22日 17:04
 */
@Data
@Schema(description = "用户请求参数")
public class SysUserDto extends BaseDto{
    @Schema(description = "用户id")
    private Integer id;

    @Schema(description = "用户名称")
    private String userName;

    @Schema(description = "用户中文名称")
    private String fullName;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "菜单类型")
    private String menuType;

    @Schema(description = "创建日期")
    private LocalDateTime createdDate;

    @Schema(description = "更新日期")
    private LocalDate updatedDate;
}
