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
@Schema(description = "菜单请求参数")
public class SysDeptDto extends BaseDto{
    @Schema(description = "id")
    private Integer id;

    @Schema(description = "部门名称")
    private String name;

    @Schema(description = "父id")
    private Integer parentId;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "排序")
    private Integer sortNo;
}
