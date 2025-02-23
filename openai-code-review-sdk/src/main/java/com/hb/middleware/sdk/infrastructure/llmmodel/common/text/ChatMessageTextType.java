package com.hb.middleware.sdk.infrastructure.llmmodel.common.text;

public enum ChatMessageTextType {

    /**
     * 系统消息
     */
    SYSTEM(SystemMessageText.class),
    /**
     * 用户消息
     */
    USER(UserMessageText.class),
    /**
     * AI反馈的结果消息
     */
    AI(AIMessageText.class);

    private final Class<? extends ChatMessageText> messageClass;

    ChatMessageTextType(Class<? extends ChatMessageText> messageClass) {
        this.messageClass = messageClass;
    }

    public Class<? extends ChatMessageText>messageClass() {return messageClass;}
}
