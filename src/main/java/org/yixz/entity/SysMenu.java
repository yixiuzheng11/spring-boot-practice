package org.yixz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单类型，button-按钮，directory-目录，menu-菜单
     */
    private String menuType;

    /**
     * 父菜单id
     */
    private Integer parentId;

    /**
     * 图标
     */
    private String icon;

    /**
     * 路由
     */
    private String url;

    /**
     * 权限
     */
    private String perms;

    /**
     * 排序
     */
    private Integer sortNo;

    /**
     * 状态，1-有效，0-无效
     */
    private Integer dataStatus;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdDate;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedDate;

    /**
     * 创建人员
     */
    @TableField(fill = FieldFill.INSERT)
    private String createdBy;

    /**
     * 更新人员
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatedBy;

}
