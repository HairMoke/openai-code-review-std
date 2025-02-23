package com.hb.middleware.sdk.infrastructure.llmmodel.common.message;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserChatMessage implements ChatMessage{

    @Builder.Default
    private String role = Role.ASSISTANT.toString().toLowerCase();
    private String content;
    private String name;

    public static UserChatMessage from(String content) {
        return UserChatMessage.builder().content(content).build();
    }

}
