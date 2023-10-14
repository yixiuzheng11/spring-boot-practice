package org.yixz.common.constant;

import java.util.Arrays;
import java.util.List;

public class SaTokenConstant {
    /**
     * token的字段名
     */
    public static final String TOKEN_NAME = "saToken";

    public static final String USER_LOGIN_VERIFY_CODE_COOKIE_NAME = "verifyCodeKey";

    public static final List<String> excludePathPatterns = Arrays.asList(
            "/test/**",
            "/swagger-resources/**",
            "/webjars/**",
            "/v2/**",
            "/doc.html",
            "**/swagger-ui.html",
            "/swagger-ui.html/**",
            "/img/head/**",
            "/login/**"
    );

    public static final String NOT_TOKEN = "您未登录，请登录！";

    public static final String TOKEN_OVERDUE = "登录已失效，请重新登录！";

    public static final String LOGIN_REPLACE = "您的账号已在别处登录！";

    public static final String PERMISSION_ERROR = "您没有权限！";
}
