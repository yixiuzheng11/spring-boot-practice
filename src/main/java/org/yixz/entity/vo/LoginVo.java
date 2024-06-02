package org.yixz.entity.vo;

import lombok.Data;

/**
 * 描述
 *
 * @date 2021年12月15日 19:11
 */
@Data
public class LoginVo {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 账号
     */
    private String username;

    /**
     * 用户名称
     */
    private String nickname;

    /**
     * 图标
     */
    private String avatar = "https://oss.youlai.tech/youlai-boot/2023/05/16/811270ef31f548af9cffc026dfc3777b.gif";

    /**
     * token
     */
    private String accessToken;
}
