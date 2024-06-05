package org.yixz.entity.mysql;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author yixz
 * @since 2022-09-24
 */
@Data
@TableName("sys_dept")
public class SysDept {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父菜单id
     */
    private Integer parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 排序
     */
    @Schema(description = "状态，1-有效，0-无效")
    private Integer status;

    /**
     * 排序
     */
    private Integer sortNo;

}
