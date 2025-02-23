package com.hb.middleware.sdk.infrastructure.llmmodel.common.response;

import com.hb.middleware.sdk.infrastructure.llmmodel.common.message.AssistantChatMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatCompletionChoice {

    private Integer index;
    private AssistantChatMessage message;

    private String finishReason;
}
