package org.yixz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

/**
 * 描述
 *
 * @author yixiuzheng11
 * @date 2021年07月22日 17:04
 */
@Data
@Schema(description = "基础参数")
public class BaseDto {
    @Schema(description = "当前页")
    private Integer pageNum = 1;

    @Schema(description = "分页大小")
    private Integer pageSize = 15;
}
