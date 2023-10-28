package org.yixz.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yixz.captcha.CaptchaService;
import org.yixz.common.constant.JwtConst;
import org.yixz.common.exception.BizException;
import org.yixz.entity.dto.LoginDto;
import org.yixz.entity.mysql.SysUser;
import javax.annotation.Resource;
import java.util.Date;

@Service
public class LoginService {
    @Resource
    private CaptchaService captchaService;

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
     * @return
     */
    public String doLogin(LoginDto loginDto) {
        SysUser sysUser = checkLogin(loginDto);
        if(sysUser==null) {
            throw new BizException("账号密码错误");
        }
        return generateToken(sysUser);
    }

    /**
     * 验证登录信息
     * @param loginDto
     * @return
     */
    public SysUser checkLogin(LoginDto loginDto) {
        SysUser sysUser = sysUserService.getByUserName(loginDto.getUserName());
        //校验密码
        boolean passwordFlag = checkPassword(loginDto, sysUser);
        //校验验证码
        boolean captchaFlag = captchaService.checkCaptcha(loginDto);
        if(passwordFlag && captchaFlag) {
            return sysUser;
        }
        return null;
    }

    /**
     * 校验密码
     * @param loginDto
     * @param sysUser
     * @return
     */
    public boolean checkPassword(LoginDto loginDto, SysUser sysUser) {
        //校验密码
        if(sysUser!=null && sysUser.getPassword().equals(loginDto.getPassword())) {
            return true;
        }
        return false;
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
