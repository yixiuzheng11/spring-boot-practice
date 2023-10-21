package org.yixz.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yixz.common.constant.JwtConst;
import org.yixz.common.exception.BizException;
import org.yixz.common.util.VerifyImgUtil;
import org.yixz.entity.dto.LoginDto;
import org.yixz.entity.mysql.SysUser;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class LoginService {
    @Resource
    private VerifyImgUtil verifyImgUtil;

    @Resource
    private SysUserService sysUserService;

    private static final long HOUR_TIME_MILLI = 60 * 60 * 1000;

    @Value("${token.key:tokenKeySaltAbc}")
    private String tokenKey;

    @Value("${token.expire-hour:6}")
    private Integer tokenExpire;

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
        return generateToken(sysUser);
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

    /**
     * 生成Token
     *
     * @param sysUser
     * @return
     */
    public String generateToken(SysUser sysUser) {
        long nowTimeMilli = System.currentTimeMillis();
        Claims jwtClaims = Jwts.claims();
        jwtClaims.put(JwtConst.CLAIM_ID_KEY, sysUser.getId());
        jwtClaims.put(JwtConst.CLAIM_NAME_KEY, sysUser.getUserName());
        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(jwtClaims)
                .setIssuedAt(new Date(nowTimeMilli))
                .signWith(SignatureAlgorithm.HS512, tokenKey);
        jwtBuilder.setExpiration(new Date(nowTimeMilli + tokenExpire * HOUR_TIME_MILLI));
        String token = jwtBuilder.compact();
        return token;
    }
}
