package com.hb.middleware.sdk.infrastructure.llmmodel.common.chat;

import com.hb.middleware.sdk.infrastructure.llmmodel.common.output.Response;
import com.hb.middleware.sdk.infrastructure.llmmodel.common.text.AIMessageText;
import com.hb.middleware.sdk.infrastructure.llmmodel.common.text.ChatMessageText;

import java.util.List;

/**
 * 标准AI大模型的客户端使用的API接口， 咯咯哒模型实现此接口
 */
public interface ChatLanguageModel {

    /**
     *
     * 可变参数接收多个消息提示词，返回Aides相应的结果
     * @param messages
     * @return
     */
    default Response<AIMessageText> generate(ChatMessageText... messages){
        return generate(messages);
    }


    /**
     * 列表结构接受多个消息提示词，返回AI相应的结果
     * @param messages
     * @return
     */
    Response<AIMessageText> generate(List<ChatMessageText> messages);
}
