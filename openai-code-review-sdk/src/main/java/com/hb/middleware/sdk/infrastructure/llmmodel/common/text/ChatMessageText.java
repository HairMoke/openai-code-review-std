package com.hb.middleware.sdk.infrastructure.llmmodel.common.text;

/**
 * 聊天文本接口
 */
public interface ChatMessageText {

    /**
     * 表示聊天消息类型
     * @return
     */
    public ChatMessageTextType type();

    /**
     * 消息的文本对象
     * @return
     */
    public String text();
}
