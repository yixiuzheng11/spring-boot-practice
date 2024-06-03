package org.yixz.entity.mysql;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author yixz
 * @since 2022-09-24
 */
@Data
@TableName("sys_menu")
public class SysMenu {
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
     * 菜单类型，button-按钮，directory-目录，menu-菜单
     */
    private String type;

    @Schema(description = "路由地址")
    private String route;

    /**
     * 路由
     */
    @Schema(description = "组件地址")
    private String url;

    /**
     * 图标
     */
    private String icon;

    /**
     * 权限
     */
    private String permission;

    /**
     * 排序
     */
    private Integer sortNo;

}
