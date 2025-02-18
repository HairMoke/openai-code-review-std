package com.hb.middleware.sdk.infrastructure.openai;

import com.hb.middleware.sdk.infrastructure.openai.dto.ChatCompletionRequestDto;
import com.hb.middleware.sdk.infrastructure.openai.dto.ChatCompletionSyncResponseDto;

public interface IOpenAI {
    ChatCompletionSyncResponseDto completions(ChatCompletionRequestDto requestDto) throws Exception;
}
