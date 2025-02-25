package com.hb.middleware.sdk.infrastructure.quantify.listener.impl;

import com.hb.middleware.sdk.infrastructure.env.CodeReviewPropertySource;
import com.hb.middleware.sdk.infrastructure.quantify.listener.CodeReviewResultListener;
import com.hb.middleware.sdk.infrastructure.quantify.model.CodeReviewQuantifyResult;
import com.hb.middleware.sdk.infrastructure.quantify.model.CodeReviewResultContext;

/**
 * 默认的webhook的方式通知的
 */
public class DefaultWebhookCodeReviewListener implements CodeReviewResultListener
{
    @Override
    public void onComplete(CodeReviewResultContext context) {
        // 这里可以将当前的数据通过系统配置的webhook url传递出去，提供扩展口
        CodeReviewPropertySource configProperty = CodeReviewPropertySourceFactory.getDefault();
        String webhookUrl = configProperty.getProperty("WEB_HOOK_URL");
        if(webhookUrl != null && webhookUrl.trim().length() > 0) {
            // 可以发送个HTTP请求，将代码发送出去，比如搞个服务端及逆行上报结果
            CodeReviewQuantifyResult result = new CodeReviewQuantifyResult();
        }
    }
}
