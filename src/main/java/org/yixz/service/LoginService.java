package org.yixz.service;

import cn.dev33.satoken.stp.StpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.yixz.common.exception.BizException;
import org.yixz.common.util.VerifyImgUtil;
import org.yixz.entity.dto.LoginDto;
import org.yixz.entity.mysql.SysUser;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class LoginService {
    @Resource
    private VerifyImgUtil verifyImgUtil;

    @Resource
    private SysUserService sysUserService;

    /**
     * 登录
     * @param loginDto
     * @param request
     * @return
     */
    public String doLogin(LoginDto loginDto, HttpServletRequest request) {
        SysUser sysUser = checkLogin(loginDto, request);
        if(sysUser==null) {
            throw new BizException("账号或密码错误");
        }
        StpUtil.login(sysUser.getId());
        return StpUtil.getTokenValue();
    }

    /**
     * 验证登录信息
     * @param loginDto
     * @param request
     * @return
     */
    public SysUser checkLogin(LoginDto loginDto, HttpServletRequest request) {
        SysUser sysUser = sysUserService.getByUserName(loginDto.getUserName());
        if(sysUser==null) {
            return null;
        }
        //校验密码
        if(!sysUser.getPassword().equals(loginDto.getPassword())) {
            return null;
        }
        if(StringUtils.isEmpty(loginDto.getVerifyCode())) {
            return null;
        }
        //校验验证码
        boolean verifyCodeFlag = verifyImgUtil.checkVerifyCode(loginDto, request);
        if(verifyCodeFlag) {
            return sysUser;
        }
        return null;
    }
}
