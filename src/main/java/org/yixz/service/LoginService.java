package org.yixz.service;

import cn.dev33.satoken.stp.StpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.yixz.common.exception.BizException;
import org.yixz.common.util.VerifyImgUtil;
import org.yixz.entity.dto.LoginDto;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
        //校验验证码
        boolean verifyCodeFlag = verifyImgUtil.checkVerifyCode(loginDto, request);
        boolean userFlag = true;
        return verifyCodeFlag && userFlag;
    }
}
