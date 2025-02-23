package com.hb.middleware.sdk.infrastructure.env;

import com.hb.middleware.sdk.infrastructure.env.enums.PropertySourceEnums;

import java.util.Properties;

/**
 * 代码评审配置数据来源接口
 */
public interface CodeReviewPropertySource {


    /**
     * 获取类型枚举
     * @return
     */
    public PropertySourceEnums getType();

    /**
     * 获得属性
     * @param key
     * @return
     */
    public String getProperty(String key);

    /**
     * 判断是否包含属性
     * @param key
     * @return
     */
    public boolean containsKey(String key);

    /**
     * 转换为属性对象
     * @return
     */
    public Properties asProperties();
}
