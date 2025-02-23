package com.hb.middleware.sdk.infrastructure.env;


import com.hb.middleware.sdk.infrastructure.env.impl.HttpPropertiesCodeReviewPropertySource;
import com.hb.middleware.sdk.infrastructure.env.impl.LocalFileCodeReviewPropertySource;
import com.hb.middleware.sdk.infrastructure.env.impl.SystemEnvCodeReviewPropertySource;

import java.util.Properties;

/**
 * 代码评审的配置属性对外公开的操作的类
 */
public class CodeReviewConfigPropertitsFacade {

    private CodeReviewPropertySource codeReviewPropertySource;

    public CodeReviewConfigPropertitsFacade() {
        String httpConfigUrl = System.getenv("HTTP_CONFIG_URL");
        String configPath = System.getenv("CONFIG_PATH");
        if(httpConfigUrl != null && !httpConfigUrl.isEmpty()) {
            codeReviewPropertySource = new HttpPropertiesCodeReviewPropertySource();
        } else if(configPath != null && !configPath.isEmpty()) {
            codeReviewPropertySource  = new LocalFileCodeReviewPropertySource();
        } else {
            codeReviewPropertySource = new SystemEnvCodeReviewPropertySource();
        }
    }

    public String getProperty(String key) {
        return codeReviewPropertySource.getProperty(key);
    }

    public Properties asProperties() {
        return codeReviewPropertySource.asProperties();
    }
}
