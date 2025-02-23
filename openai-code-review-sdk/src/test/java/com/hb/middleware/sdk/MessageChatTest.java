package com.hb.middleware.sdk;

import com.hb.middleware.sdk.infrastructure.llmmodel.common.output.Response;
import com.hb.middleware.sdk.infrastructure.llmmodel.common.text.AIMessageText;
import com.hb.middleware.sdk.infrastructure.llmmodel.common.text.SystemMessageText;
import com.hb.middleware.sdk.infrastructure.llmmodel.common.text.UserMessageText;
import com.hb.middleware.sdk.infrastructure.llmmodel.zhipu.ZhipuAiChatModel;
import com.hb.middleware.sdk.infrastructure.llmmodel.zhipu.ZhipuChatCompletionModelEnum;
import org.junit.Test;

public class MessageChatTest {

    private static final String apiKey = System.getenv("ZHIPU_API_KEY");
    private static final String baseUrl = System.getenv("ZHIPU_ZPI_URL");


    @Test
    public void test1(){
        // 系统提示词
        SystemMessageText systemMessageText = new SystemMessageText(
                "你是一个10年经验的Java代码审查专家， 请帮助用户进行代码审查");
        // 用户提示词
        UserMessageText userMessageText = new UserMessageText("这个是我的代码评审内容： System.out.println('hello, world');");

        // 智普大模型
        ZhipuAiChatModel chatModel = ZhipuAiChatModel.builder()
                .apiKey(apiKey)
                .baseUrl(baseUrl)
                .model(ZhipuChatCompletionModelEnum.GLM_4_FLASH.toString())
                .build();

        // 获得响应
        Response<AIMessageText> response = chatModel.generate(systemMessageText, userMessageText);
        System.out.println(response.content().text());
    }
}
