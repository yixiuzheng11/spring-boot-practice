package org.yixz.common.util;

/**
 * 描述
 *
 * @author YIXIUZHENG741
 * @date 2021年12月20日 18:14
 */
public class SysConstants {
    //生成token时的salt
    public static String TOKEN_SALT = "spring-cloud-practice";

    //记住我时生成cookie的salt
    public static String REMEMBER_ME_SALT = "yixiuzheng";

    //验证码在session中的属性名称
    public static String VERIFY_CODE_PARAM = "verifyCode";

    //token在请求header中的名称
    public static String AUTHORIZATION_HEADER= "Authorization";

    //用户权限信息在redis中的key前缀
    public static final String USER_PERMS_KEY_PREFIX = "USER_PERMS_";
}
