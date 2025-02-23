package com.hb.middleware.sdk.infrastructure.message.impl;

import com.hb.middleware.sdk.infrastructure.feishu.Feishu;
import com.hb.middleware.sdk.infrastructure.message.IMessageStrategy;
import com.hb.middleware.sdk.types.utils.EnvUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class FeishuMessageStrategy implements IMessageStrategy {
    @Override
    public String name() {
        return "feishu";
    }

    @Override
    public void sendMessage(String logUrl, Map<String, Map<String, String>> data) {
        try {
            String botWebhook = EnvUtil.getEnv("FEISHU_URL");
            Feishu feishu = new Feishu(botWebhook);

            feishu.sendMessage(logUrl);
        } catch (Exception e){
            log.error(e.getMessage(),e);
        }
    }
}
