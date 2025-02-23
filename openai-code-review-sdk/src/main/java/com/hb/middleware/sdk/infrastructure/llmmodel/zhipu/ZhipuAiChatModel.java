package com.hb.middleware.sdk.infrastructure.llmmodel.zhipu;

import com.hb.middleware.sdk.infrastructure.llmmodel.common.chat.ChatLanguageModel;
import com.hb.middleware.sdk.infrastructure.llmmodel.common.output.Response;
import com.hb.middleware.sdk.infrastructure.llmmodel.common.request.ChatCompletionRequest;
import com.hb.middleware.sdk.infrastructure.llmmodel.common.response.ChatCompletionResponse;
import com.hb.middleware.sdk.infrastructure.llmmodel.common.text.AIMessageText;
import com.hb.middleware.sdk.infrastructure.llmmodel.common.text.ChatMessageText;
import com.hb.middleware.sdk.types.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;

import java.util.List;

@Slf4j
public class ZhipuAiChatModel implements ChatLanguageModel {

    private final String model;
    private final ZhipuAiHttpClient client;

    @Builder
    public ZhipuAiChatModel (String baseUrl,
                             String apiKey,
                             String model){
        this.model = Utils.getOrDefault(model, ZhipuChatCompletionModelEnum.GLM_4_FLASH.toString());
        this.client = ZhipuAiHttpClient.builder()
                .baseUrl(Utils.getOrDefault(baseUrl, "https://open.bigmodel.cn/"))
                .apiKey(apiKey)
                .build();
    }



    @Override
    public Response<AIMessageText> generate(List<ChatMessageText> messages) {
        ChatCompletionRequest.ChatCompletionRequestBuilder builder = ChatCompletionRequest.builder().model(this.model)
                .messages(ZhipuAiHelper.toMessages(messages));
        ChatCompletionRequest request = builder.build();
        ChatCompletionResponse response = client.chatCompletion(request);
        Response<AIMessageText> messageResponse = Response.from(ZhipuAiHelper.aiMessageFrom(response));
        return messageResponse;
    }
}
