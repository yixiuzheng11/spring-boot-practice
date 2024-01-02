package org.yixz.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yixz.common.constant.JwtConst;
import org.yixz.entity.mysql.SysUser;

import java.util.Date;

public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private static final String tokenKey = "tokenKeySaltAbc";

    private static final Integer tokenExpire = 6;

    private static final long HOUR_TIME_MILLI = 60 * 60 * 1000;

    /**
     * 生成Token
     *
     * @param sysUser
     * @return
     */
    public static String generateToken(SysUser sysUser) {
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

    public static SysUser desToken(String token) {
        Claims claims = getClaims(token);
        if(claims==null) {
            return null;
        }
        SysUser sysUser = new SysUser();
        sysUser.setId((Integer)claims.get(JwtConst.CLAIM_ID_KEY));
        sysUser.setUserName((String)claims.get(JwtConst.CLAIM_NAME_KEY));
        return sysUser;
    }

    public static Claims getClaims(String token) {
        Claims claims = null;
        try {
            claims = (Claims) Jwts.parser().setSigningKey(tokenKey).parseClaimsJws(token).getBody();
        }catch (Exception e) {
            logger.warn("jwtToken无效");
        }
        return claims;
    }

    public static void main(String[] args) {
        SysUser sysUser = new SysUser();
        sysUser.setId(1);
        sysUser.setUserName("admin");
        String token = generateToken(sysUser);
        System.out.println(token);
        Claims claims = getClaims(token);
        System.out.println(claims);
    }
}
