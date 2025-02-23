package com.hb.middleware.sdk.infrastructure.llmmodel.zhipu;


import com.alibaba.fastjson2.JSON;
import com.hb.middleware.sdk.infrastructure.llmmodel.common.request.ChatCompletionRequest;
import com.hb.middleware.sdk.infrastructure.llmmodel.common.response.ChatCompletionResponse;
import com.hb.middleware.sdk.types.utils.DefaultHttpUtil;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Data
@Builder
public class ZhipuAiHttpClient {

    private String baseUrl;

    private String apiKey;

    public ChatCompletionResponse chatCompletion(ChatCompletionRequest request) {
        try{
            // 发送网络请求
            Map<String,String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            headers.put("Authorization", "Bearer " + apiKey);
            String url = this.baseUrl + "/chat/completions";
            log.info(JSON.toJSONString(request));
            String response = DefaultHttpUtil.executePostRequest(url, headers, request);
            ChatCompletionResponse chatCompletionResponse = JSON.parseObject(response, ChatCompletionResponse.class);
            return chatCompletionResponse;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
