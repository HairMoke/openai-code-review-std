package com.hb.middleware.sdk.infrastructure.env.impl;

import com.hb.middleware.sdk.infrastructure.env.CodeReviewPropertySource;
import com.hb.middleware.sdk.infrastructure.env.enums.PropertySourceEnums;

import java.io.FileInputStream;
import java.util.Properties;

import com.hb.middleware.sdk.types.utils.EnvUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 基于本地文件的方式
 */

public class LocalFileCodeReviewPropertySource implements CodeReviewPropertySource {
    private static final Logger logger = LoggerFactory.getLogger(LocalFileCodeReviewPropertySource.class);

    private final Properties defaultProperties = new Properties();
    public LocalFileCodeReviewPropertySource(){
        String configPath = EnvUtil.getEnv("CONFIG_PATH");
        if (configPath == null || configPath.isEmpty()) {
            throw new RuntimeException("未配置本地配置文件");
        }
        try (FileInputStream inputStream = new FileInputStream(configPath)){
            // 加载远程的属性文件内容
            defaultProperties.load(inputStream);
            logger.info("成功加载本地文件的配置数据");
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public PropertySourceEnums getType() {
        return PropertySourceEnums.SYSTEM;
    }

    @Override
    public String getProperty(String key) {
        return defaultProperties.getProperty(key);
    }

    @Override
    public boolean containsKey(String key) {
        return defaultProperties.containsKey(key);
    }

    @Override
    public Properties asProperties() {
        Properties properties = new Properties();
        properties.putAll(defaultProperties);
        return properties;
    }
}
