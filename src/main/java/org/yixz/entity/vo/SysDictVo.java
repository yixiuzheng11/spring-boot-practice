package org.yixz.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 描述
 *
 * @date 2021年12月23日 9:45
 */
@Data
@Schema(description = "字典信息")
public class SysDictVo {
    @Schema(description = "类型名称")
    private String name;

    @Schema(description = "类型编码")
    private String type;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "备注")
    private String remark;
}
