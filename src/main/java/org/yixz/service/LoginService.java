package org.yixz.service;

import cn.dev33.satoken.stp.StpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;
import org.yixz.common.constant.SaTokenConstant;
import org.yixz.common.exception.BizException;
import org.yixz.common.util.VerifyImgUtil;
import org.yixz.dto.LoginDto;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class LoginService {
    @Resource
    private VerifyImgUtil verifyImgUtil;

    /**
     * 登录
     * @param loginDto
     * @param request
     * @return
     */
    public String doLogin(LoginDto loginDto, HttpServletRequest request) {
        if(!checkLogin(loginDto, request)) {
            throw new BizException("501", "账号或密码错误");
        }
        StpUtil.login(loginDto.getUserName());
        return StpUtil.getTokenValue();
    }

    /**
     * 验证登录信息
     * @param loginDto
     * @param request
     * @return
     */
    public boolean checkLogin(LoginDto loginDto, HttpServletRequest request) {
        if(StringUtils.isEmpty(loginDto.getVerifyCode())) {
            return false;
        }
        // 从cookie中获取验证码对应的唯一key
        String verifyKey = Optional.ofNullable(WebUtils.getCookie(request, SaTokenConstant.USER_LOGIN_VERIFY_CODE_COOKIE_NAME))
                .map(Cookie::getValue).orElse(null);
        if (StringUtils.isBlank(verifyKey)) {
            return false;
        }
        //校验验证码
        boolean verifyCodeFlag = verifyImgUtil.checkVerifyCode(loginDto, verifyKey);
        boolean userFlag = true;
        return verifyCodeFlag && userFlag;
    }
}
