package com.hb.middleware.sdk.types.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.checkerframework.checker.units.qual.A;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BearerTokenUtils {

    // 过期时间，默认30分钟
    private static final long expireMillis = 30*60*1000L;

    // 缓存服务
    public static Cache<String, String> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(expireMillis - (60 * 1000L), TimeUnit.MILLISECONDS).build();

    public static String getToken(String apiKeySecret) {
        String[] split = apiKeySecret.split("\\.");
        return getToken(split[0], split[1]);
    }

    public static String getToken(String apiKey, String spiSecret) {
        // 缓存token
        String token = cache.getIfPresent(apiKey);
        if(null != token) {
            return token;
        }

        // 创建token
        Algorithm algorithm = Algorithm.HMAC256(spiSecret.getBytes(StandardCharsets.UTF_8));
        Map<String, Object> payload = new HashMap<>();
        payload.put("api_key", apiKey);
        payload.put("exp", System.currentTimeMillis() + expireMillis);
        payload.put("timestamp", Calendar.getInstance().getTimeInMillis());
        Map<String, Object> headerClaims = new HashMap<>();
        headerClaims.put("alg", "HS256");
        headerClaims.put("sign_type", "SIGN");
        token = JWT.create().withPayload(payload).withHeader(headerClaims).sign(algorithm);
        cache.put(apiKey, token);
        return token;

    }

}
