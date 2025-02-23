package com.hb.middleware.sdk.infrastructure.llmmodel.zhipu;

import com.hb.middleware.sdk.infrastructure.llmmodel.common.message.AssistantChatMessage;
import com.hb.middleware.sdk.infrastructure.llmmodel.common.message.ChatMessage;
import com.hb.middleware.sdk.infrastructure.llmmodel.common.message.SystemChatMessage;
import com.hb.middleware.sdk.infrastructure.llmmodel.common.message.UserChatMessage;
import com.hb.middleware.sdk.infrastructure.llmmodel.common.response.ChatCompletionResponse;
import com.hb.middleware.sdk.infrastructure.llmmodel.common.text.AIMessageText;
import com.hb.middleware.sdk.infrastructure.llmmodel.common.text.ChatMessageText;
import com.hb.middleware.sdk.infrastructure.llmmodel.common.text.SystemMessageText;
import com.hb.middleware.sdk.infrastructure.llmmodel.common.text.UserMessageText;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用于将提示词文案对象，转换为聊天消息对象
 */
public class ZhipuAiHelper {


    public static List<ChatMessage> toMessages(List<ChatMessageText> messages) {
        return messages.stream()
                .map(ZhipuAiHelper::toMessages)
                .collect(Collectors.toList());
    }


    public static ChatMessage toMessages(ChatMessageText message) {
        if(message instanceof SystemMessageText){
            return SystemChatMessage.from(((SystemMessageText) message).text());
        }

        if(message instanceof UserMessageText){
            return UserChatMessage.from(((UserMessageText) message).text());
        }

        if(message instanceof AIMessageText){
            return AssistantChatMessage.from(((AIMessageText) message).text());
        }
        throw new IllegalArgumentException("unknown message type: " + message.type());
    }

    static AIMessageText aiMessageFrom(ChatCompletionResponse response) {
        AssistantChatMessage message = response.getChoices().get(0).getMessage();
        return AIMessageText.from(message.getContent());
    }
}
