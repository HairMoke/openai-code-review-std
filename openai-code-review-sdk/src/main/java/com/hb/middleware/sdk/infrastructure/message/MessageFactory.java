package com.hb.middleware.sdk.infrastructure.message;

import com.hb.middleware.sdk.infrastructure.message.impl.FeishuMessageStrategy;
import com.hb.middleware.sdk.infrastructure.message.impl.WeixinMessageStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息工厂类
 */
public class MessageFactory {

    private static Map<String, IMessageStrategy> registry = new HashMap<String, IMessageStrategy>();

    static {
        // 初始化2个策略实现
        registry.put("weixin", new WeixinMessageStrategy());
        registry.put("feishu", new FeishuMessageStrategy());
    }

    public static IMessageStrategy getMessageStrategy(String name) {
        return registry.get(name);
    }
}
