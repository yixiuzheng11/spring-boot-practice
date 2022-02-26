package org.yixz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 描述
 *
 * @author YIXIUZHENG741
 * @date 2021年07月22日 17:04
 */
@Data
@ApiModel("基础参数")
public class BaseDto {
    @ApiModelProperty(value = "当前页")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "分页大小")
    private Integer pageSize = 15;
}
