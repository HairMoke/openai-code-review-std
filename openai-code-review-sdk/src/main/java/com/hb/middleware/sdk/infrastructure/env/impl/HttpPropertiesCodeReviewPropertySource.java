package com.hb.middleware.sdk.infrastructure.env.impl;

import com.hb.middleware.sdk.infrastructure.env.CodeReviewPropertySource;
import com.hb.middleware.sdk.infrastructure.env.enums.PropertySourceEnums;
import com.hb.middleware.sdk.types.utils.DefaultHttpUtil;
import com.hb.middleware.sdk.types.utils.EnvUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;


/**
 * 基于Http远程地址的实现方式
 */
public class HttpPropertiesCodeReviewPropertySource implements CodeReviewPropertySource {

    private static final Logger logger = LoggerFactory.getLogger(HttpPropertiesCodeReviewPropertySource.class);

    private final Properties defaultProperties = new Properties();

    public HttpPropertiesCodeReviewPropertySource() {
        String httpConfigUrl = EnvUtil.getEnv("HTTP_CONFIG_URL");
        if(httpConfigUrl == null || httpConfigUrl.isEmpty()) {
            throw new RuntimeException("未配置http配置文件地址");
        }

        try(InputStream inputStream = DefaultHttpUtil.getHttpInputStream(httpConfigUrl)){
            // 加载远程的属性文件内容
            defaultProperties.load(inputStream);
            logger.info("成功加载远程配置数据");
        } catch (Exception e) {
            e.printStackTrace();
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
