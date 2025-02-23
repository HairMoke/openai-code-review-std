package com.hb.middleware.sdk.infrastructure.llmmodel.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatCompletionResponse {

    private String id;
    private String created;
    private String model;
    private List<ChatCompletionChoice> choices;

}
