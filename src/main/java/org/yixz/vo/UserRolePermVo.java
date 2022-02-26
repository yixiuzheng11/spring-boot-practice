package org.yixz.vo;

import lombok.Data;

/**
 * 描述
 *
 * @author YIXIUZHENG741
 * @date 2021年12月15日 19:11
 */
@Data
public class UserRolePermVo {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色
     */
    private String roleCode;

    /**
     * 权限标识
     */
    private String perm;
}
