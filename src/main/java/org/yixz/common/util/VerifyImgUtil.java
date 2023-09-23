package org.yixz.common.util;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.lang.UUID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;
import org.yixz.common.constant.SaTokenConstant;
import org.yixz.entity.dto.LoginDto;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Optional;

@Component
public class VerifyImgUtil {
    @Resource
    private RedisUtil redisUtil;

    /**
     * 生成验证码的字符，去掉了0、1、I、O这样容易混淆的字符
     */
    public static final String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";

    /**
     * 生成线段干扰的验证码
     *
     * @param os        输出流
     * @param tempToken 临时token，作为验证码写入缓存的key
     */
    public void lineCaptcha(OutputStream os, String tempToken) {
        //定义图形验证码的长和宽
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(200, 100);
        // 自定义纯数字的验证码（随机4位字符，可重复）
        RandomGenerator randomGenerator = new RandomGenerator(VERIFY_CODES, 4);
        captcha.setGenerator(randomGenerator);
        putVerifyCode(tempToken, captcha.getCode());
        captcha.write(os);
    }

    /**
     * 生成圆圈干扰的验证码
     *
     * @param os        输出流
     * @param tempToken 临时token，作为验证码写入缓存的key
     */
    public void circleCaptcha(OutputStream os, String tempToken) {
        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);
        // 自定义纯数字的验证码（随机4位字符，可重复）
        RandomGenerator randomGenerator = new RandomGenerator(VERIFY_CODES, 4);
        captcha.setGenerator(randomGenerator);
        putVerifyCode(tempToken, captcha.getCode());
        captcha.write(os);
    }

    /**
     * 生成扭曲干扰的验证码
     *
     * @param os        输出流
     * @param tempToken 临时token，作为验证码写入缓存的key
     */
    public void shearCaptcha(OutputStream os, String tempToken) {
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4);
        // 自定义纯数字的验证码（随机4位字符，可重复）
        RandomGenerator randomGenerator = new RandomGenerator(VERIFY_CODES, 4);
        captcha.setGenerator(randomGenerator);
        putVerifyCode(tempToken, captcha.getCode());
        captcha.write(os);
    }

    /**
     * 将验证码放到缓存里
     *
     * @param tempToken  临时token，作为验证码写入缓存的key
     * @param verifyCode 验证码
     */
    private void putVerifyCode(String tempToken, String verifyCode) {
        redisUtil.set(tempToken, verifyCode, 60);
    }

    /**
     * 校验验证码
     *
     * @param loginDto
     */
    public boolean checkVerifyCode(LoginDto loginDto, HttpServletRequest request) {
        if(StringUtils.isEmpty(loginDto.getVerifyCode())) {
            return false;
        }
        // 从cookie中获取验证码对应的唯一key
        String verifyCodeKey = this.getVerifyCodeKey(request);
        //校验
        String rdsVerifyCode = (String) redisUtil.get(verifyCodeKey);
        if (loginDto.getVerifyCode().equalsIgnoreCase(rdsVerifyCode)) {
            return true;
        }
        return false;
    }

    /**
     * 生成验证码
     * @param request
     * @param response
     * @param os
     */
    public void geneVerifyCode(HttpServletRequest request, HttpServletResponse response, OutputStream os) {
        // 从cookie中获取验证码对应的唯一key
        String verifyCodeKey = this.getVerifyCodeKey(request);
        if (StringUtils.isBlank(verifyCodeKey)) {
            verifyCodeKey = UUID.randomUUID().toString().replace("-", "");
        }
        // 这里每个请求都add新cookie，如果不每次add，则有可能会导致cookie的path发生变化
        response.addCookie(CookieUtil.generateCookie(SaTokenConstant.USER_LOGIN_VERIFY_CODE_COOKIE_NAME, verifyCodeKey ,request));
        this.shearCaptcha(os, verifyCodeKey);
    }

    public String getVerifyCodeKey(HttpServletRequest request) {
        String verifyKey = Optional.ofNullable(WebUtils.getCookie(request, SaTokenConstant.USER_LOGIN_VERIFY_CODE_COOKIE_NAME))
                .map(Cookie::getValue).orElse(null);
        return verifyKey;
    }
}
