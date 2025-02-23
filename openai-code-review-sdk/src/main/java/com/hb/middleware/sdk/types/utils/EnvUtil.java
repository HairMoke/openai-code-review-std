package com.hb.middleware.sdk.types.utils;

public class EnvUtil {

    public static String getEnv(String key) {
        String value = System.getenv(key);
        if(null == value || value.isEmpty()) {
            throw new RuntimeException("get env is null");
        }
        return value;
    }
}
