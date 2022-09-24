package org.yixz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 描述
 *
 * @author YIXIUZHENG741
 * @date 2021年12月23日 9:45
 */
@Data
@ApiModel(value = "用户信息")
public class UserVo {
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String fullName;
}
