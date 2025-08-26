package org.genntii.mkdir.common.util;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTHeader;
import cn.hutool.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mkdir
 * @since 2025/08/21 14:47
 */
@Component
public class JwtCommonUtil {

    @Value("${jwt.key}")
    private String key;


    public String createJwt(Long id) {
        Map<String, Object> map = new HashMap<>() {
            private static final long serialVersionUID = 1L;

            {
                put("id", id);
                put("expire_time", System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 15);
            }
        };

        return JWTUtil.createToken(map, key.getBytes());
    }


    public Boolean checkJwt(String token) {
        final JWT jwt = JWTUtil.parseToken(token);
        jwt.getHeader(JWTHeader.TYPE);
        if (JWTUtil.verify(token, key.getBytes()) && getExpireTime(jwt) >= System.currentTimeMillis()) {
            return true;
        }
        return false;
    }

    private static long getExpireTime(JWT jwt) {
        return Long.parseLong(jwt.getPayload("expire_time").toString());
    }


    public Long parseJwt(String token) {
        final JWT jwt = JWTUtil.parseToken(token);
        jwt.getHeader(JWTHeader.TYPE);
        return Long.parseLong(jwt.getPayload("id").toString());
    }

}
