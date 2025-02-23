package com.hb.middleware.sdk.infrastructure.feishu.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

@Data
public class BotMessageRequestDTO {

    /**
     * 飞书消息类型
     */
    @JSONField(name = "msg_type")
    private String msgType = "text";

    /**
     * 飞书的内容
     */
    @JSONField(name = "content")
    private BotMessageContent content;

    @Data
    public static class BotMessageContent {
        /**
         * 消息内容
         */
        private String text;
    }

}
