package com.hb.middleware.sdk.infrastructure.message;

import java.util.Map;

public interface IMessageStrategy {

    /**
     * 类型名称
     * @return
     */
    public String name();

    /**
     * 发送消息接口方法
     * @param logUrl
     * @param data
     */
    public void sendMessage(String logUrl, Map<String, Map<String, String>> data);
}
