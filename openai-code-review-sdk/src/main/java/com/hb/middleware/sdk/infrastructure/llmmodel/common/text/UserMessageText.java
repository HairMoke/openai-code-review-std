package com.hb.middleware.sdk.infrastructure.llmmodel.common.text;

/**
 * 用户提示词文本
 */
public class UserMessageText implements ChatMessageText {

    private final String text;

    public UserMessageText(String text){
        this.text = text;
    }

    /**
     * 消息类型
     * @return
     */
    @Override
    public ChatMessageTextType type() {
        return ChatMessageTextType.USER;
    }

    /**
     * 返回文本
     * @return
     */
    @Override
    public String text() {
        return text;
    }
}
