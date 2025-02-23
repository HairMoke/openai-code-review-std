package com.hb.middleware.sdk.infrastructure.llmmodel.common.text;

import com.hb.middleware.sdk.infrastructure.llmmodel.common.message.AssistantChatMessage;
import lombok.Builder;

/**
 * 用户提示词文本
 */
public class AIMessageText implements ChatMessageText {

    private final String text;

    @Builder
    public AIMessageText(String text){
        this.text = text;
    }

    /**
     * 消息类型
     * @return
     */
    @Override
    public ChatMessageTextType type() {
        return ChatMessageTextType.AI;
    }

    /**
     * 返回文本
     * @return
     */
    @Override
    public String text() {
        return text;
    }

    public static AIMessageText from(String text) {
        return AIMessageText.builder().text(text).build();
    }
}
