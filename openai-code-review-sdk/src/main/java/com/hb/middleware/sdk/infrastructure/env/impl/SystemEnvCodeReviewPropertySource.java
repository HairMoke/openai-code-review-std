package com.hb.middleware.sdk.infrastructure.env.impl;

import com.hb.middleware.sdk.infrastructure.env.CodeReviewPropertySource;
import com.hb.middleware.sdk.infrastructure.env.enums.PropertySourceEnums;

import java.util.Map;
import java.util.Properties;

/**
 * 基于环境变量的配置来源
 */
public class SystemEnvCodeReviewPropertySource implements CodeReviewPropertySource {

    private final Map<String, String> env = System.getenv();
    @Override
    public PropertySourceEnums getType() {
        return PropertySourceEnums.SYSTEM;
    }

    @Override
    public String getProperty(String key) {
        if(env.get(key) != null) {
            return env.get(key);
        }
        return null;
    }

    @Override
    public boolean containsKey(String key) {
        return env.containsKey(key);
    }

    @Override
    public Properties asProperties() {
        Properties props = new Properties();
        props.putAll(this.env);
        return props;
    }
}
