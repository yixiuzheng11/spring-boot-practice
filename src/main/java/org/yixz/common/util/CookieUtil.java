package org.yixz.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {
    public static Cookie generateCookie(String name, String value, HttpServletRequest request) {
        Cookie cookie = new Cookie(name, value);
        // 这个path的写法参考SpringBoot源码写的
        cookie.setPath(getRequestContext(request));
        cookie.setSecure(false);
        cookie.setHttpOnly(true);
        // 设置为-1时，关闭浏览器自动失效，设置为0马上失效
        cookie.setMaxAge(-1);
        return cookie;
    }

    private static String getRequestContext(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        return contextPath.length() > 0 ? contextPath : "/";
    }

}
