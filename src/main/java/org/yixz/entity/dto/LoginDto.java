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
@Schema(description = "登录请求参数")
public class LoginDto {

    @Schema(description = "用户名称")
    private String userName;

    @Schema(description = "验证码")
    private String verifyCode;
}
