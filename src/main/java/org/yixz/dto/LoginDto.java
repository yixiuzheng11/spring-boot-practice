package org.yixz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 描述
 *
 * @author yixiuzheng11
 * @date 2021年07月22日 17:04
 */
@Data
@ApiModel("登录请求参数")
public class LoginDto {

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "验证码")
    private String verifyCode;
}
