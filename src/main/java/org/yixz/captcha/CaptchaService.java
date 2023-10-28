package org.yixz.captcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.yixz.captcha.domain.CaptchaVo;
import org.yixz.common.exception.BizException;
import org.yixz.common.util.RedisUtil;
import org.yixz.entity.dto.LoginDto;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Objects;
import java.util.UUID;

/**
 * 图形验证码 服务
 *
 * @Author 1024创新实验室: 胡克
 * @Date 2021/8/31 20:52
 * @Wechat zhuoda1024
 * @Email lab1024@163.com
 * @Copyright 1024创新实验室 （ https://1024lab.net ）
 */
@Slf4j
@Service
public class CaptchaService {

    /**
     * 过期时间：65秒
     */
    private static final long EXPIRE_SECOND = 60L;

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private RedisUtil redisService;

    private static final String CAPTCHA = "captcha:";

    /**
     * 生成图形验证码
     * 默认 1 分钟有效期
     *
     * @return
     */
    public CaptchaVo generateCaptcha() {
        String captchaText = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(captchaText);

        String base64Code;
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            ImageIO.write(image, "jpg", os);
            base64Code = Base64Utils.encodeToString(os.toByteArray());
        } catch (Exception e) {
            log.error("generateCaptcha error:", e);
            throw new BizException("生成验证码错误");
        }

        /**
         * 返回验证码对象
         * 图片 base64格式
         */
        String uuid = UUID.randomUUID().toString().replace("-", "");

        CaptchaVo captchaVO = new CaptchaVo();
        captchaVO.setCaptchaUuid(uuid);
        captchaVO.setCaptchaImage("data:image/png;base64," + base64Code);
        String redisCaptchaKey = CAPTCHA + uuid;
        redisService.set(redisCaptchaKey, captchaText, EXPIRE_SECOND);
        return captchaVO;
    }

    /**
     * 校验图形验证码
     *
     * @param captchaDto
     * @return
     */
    public boolean checkCaptcha(LoginDto captchaDto) {
        if (StringUtils.isBlank(captchaDto.getCaptchaUuid()) || StringUtils.isBlank(captchaDto.getCaptchaCode())) {
            return false;
        }
        //校验redis里的验证码
        String redisCaptchaKey = CAPTCHA + captchaDto.getCaptchaUuid();
        String redisCaptchaCode = (String) redisService.get(redisCaptchaKey);
        if (StringUtils.isBlank(redisCaptchaCode)) {
            return false;
        }
        if (Objects.equals(redisCaptchaCode, captchaDto.getCaptchaCode())) {
            return true;
        }
        return false;
    }

}
