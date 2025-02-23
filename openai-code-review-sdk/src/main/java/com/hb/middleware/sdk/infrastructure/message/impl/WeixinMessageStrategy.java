package com.hb.middleware.sdk.infrastructure.message.impl;

import com.hb.middleware.sdk.infrastructure.message.IMessageStrategy;
import com.hb.middleware.sdk.infrastructure.weixin.WeiXin;
import com.hb.middleware.sdk.types.utils.EnvUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class WeixinMessageStrategy implements IMessageStrategy {
    @Override
    public String name() {
        return "weixin";
    }

    @Override
    public void sendMessage(String logUrl, Map<String, Map<String, String>> data) {
        try{
            // 创建weixin实例，用于发送微信消息，同样需要配置信息
            WeiXin weiXin = new WeiXin(
                    EnvUtil.getEnv("WEIXIN_APPID"),
                    EnvUtil.getEnv("WEIXIN_SECRET"),
                    EnvUtil.getEnv("WEIXIN_TOUSER"),
                    EnvUtil.getEnv("WEIXIN_TEMPLATE_ID")
            );

            weiXin.sendTemplateMessage(logUrl, data);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
