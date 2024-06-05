package org.yixz.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 描述
 *
 * @date 2021年12月23日 9:45
 */
@Data
@Schema(description = "SysDeptVo")
public class SysDeptVo {
    @Schema(description = "id")
    private Integer id;

    @Schema(description = "父id")
    private Integer parentId;

    @Schema(description = "部门名称")
    private String name;

    @Schema(description = "组件路径")
    private Integer status;

    @Schema(description = "排序")
    private Integer sortNo;

    @Schema(description = "子菜单")
    private List<SysDeptVo> children;
}
