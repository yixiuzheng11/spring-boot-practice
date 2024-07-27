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
@Schema(description = "用户请求参数")
public class SysDictDto extends BaseDto{
    @Schema(description = "关键字")
    private String keywords;
}
