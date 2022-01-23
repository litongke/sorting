package com.chufinfo.sorting.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.chufinfo.sorting.exception.RRException;
import com.chufinfo.sorting.model.User;
import com.chufinfo.sorting.response.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * JWT
 *
 * @author maxjcs
 *
 */
@Slf4j
public class AdminTokenManager {
    /**
     * 密钥
     */
    private final static String SECRET = "CHUFINFO_TOKEN_SECRET";

    /**
     * 签名是谁生成
     */
    private static final String ISSUSER = "CHUFINFOISSADMINUSER";

    /**
     * 签名的主题
     */
    private static final String SUBJECT = "admin_token";

    /**
     * 签名的观众
     */
    private static final String AUDIENCE = "XIZHUANAUDIENCE";


    /**
     * 创建token
     *
     * @param userId 用户id
     * @return
     */
    public static String createToken(Long userId, String userName, Integer level) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            Map<String, Object> map = new HashMap<String, Object>();
            Date nowDate = new Date();
            // 过期时间：1个月
            Date expireDate = getAfterDate(nowDate, 0, 1, 0, 0, 0, 0);
            map.put("alg", "HS256");
            map.put("typ", "JWT");
            String token = JWT.create()
                    // 设置头部信息 Header
                    .withHeader(map)
                    // 设置 载荷 Payload
                    .withClaim("userId", userId)
                    .withClaim("userName", userName)
                    .withClaim("level", level)
                    .withIssuer(ISSUSER)
                    .withSubject(SUBJECT)
                    .withAudience(AUDIENCE)
                    // 生成签名的时间
                    .withIssuedAt(nowDate)
                    // 签名过期的时间
                    .withExpiresAt(expireDate)
                    // 签名 Signature
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            log.error("token生成失败", exception);
        }
        return null;
    }

    /**
     * 根据token获取用户id
     *
     * @param token token
     * @return
     */
    public static User verifyTokenAndGetUserId(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUSER)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            Claim claim1 = claims.get("userId");
            Claim claim2 = claims.get("userName");
            Claim claim4 = claims.get("level");
            User sysUser = new User();
            sysUser.setId(claim1.asLong());
            sysUser.setUserName(claim2.asString());
            sysUser.setLevel(claim4.asInt());
            return sysUser;
        } catch (JWTVerificationException exception) {
            throw new RRException(ErrorCode.Login_out.getTip(), ErrorCode.Login_out.getCode());
        }
    }

    /**
     * 获取过期时间
     *
     * @param date   当前时间
     * @param year   过期年
     * @param month  过期月
     * @param day    过期日
     * @param hour   过期时
     * @param minute 过期分
     * @param second 过期秒
     * @return
     */
    private static Date getAfterDate(Date date, int year, int month, int day, int hour, int minute, int second) {
        if (date == null) {
            date = new Date();
        }

        Calendar cal = new GregorianCalendar();

        cal.setTime(date);
        if (year != 0) {
            cal.add(Calendar.YEAR, year);
        }
        if (month != 0) {
            cal.add(Calendar.MONTH, month);
        }
        if (day != 0) {
            cal.add(Calendar.DATE, day);
        }
        if (hour != 0) {
            cal.add(Calendar.HOUR_OF_DAY, hour);
        }
        if (minute != 0) {
            cal.add(Calendar.MINUTE, minute);
        }
        if (second != 0) {
            cal.add(Calendar.SECOND, second);
        }
        return cal.getTime();
    }
}