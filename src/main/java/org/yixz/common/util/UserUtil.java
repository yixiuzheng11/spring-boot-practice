package org.yixz.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.yixz.common.constant.RequestHeaderConst;
import org.yixz.common.exception.BizException;
import org.yixz.common.response.ResponseCode;
import org.yixz.entity.mysql.SysUser;

import javax.servlet.http.HttpServletRequest;

public class UserUtil {

    public static SysUser getCurrentUser() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String jwtToken = request.getHeader(RequestHeaderConst.TOKEN);
        SysUser sysUser = JwtUtil.desToken(jwtToken);
        if(sysUser==null) {
            throw new BizException(ResponseCode.UNAUTHORIZED.code, ResponseCode.UNAUTHORIZED.msg);
        }
        return sysUser;
    }
}
