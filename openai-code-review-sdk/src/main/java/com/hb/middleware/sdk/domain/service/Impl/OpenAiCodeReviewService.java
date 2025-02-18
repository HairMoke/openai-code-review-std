package com.hb.middleware.sdk.domain.service.Impl;

import com.hb.middleware.sdk.domain.model.Model;
import com.hb.middleware.sdk.domain.service.AbstractOpenAiReviewService;
import com.hb.middleware.sdk.infrastructure.git.GitCommand;
import com.hb.middleware.sdk.infrastructure.openai.IOpenAI;
import com.hb.middleware.sdk.infrastructure.openai.dto.ChatCompletionRequestDto;
import com.hb.middleware.sdk.infrastructure.openai.dto.ChatCompletionSyncResponseDto;
import com.hb.middleware.sdk.infrastructure.weixin.WeiXin;
import com.hb.middleware.sdk.infrastructure.weixin.dto.TemplateMessageDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OpenAiCodeReviewService extends AbstractOpenAiReviewService {

    public OpenAiCodeReviewService(GitCommand gitCommand, IOpenAI openAI, WeiXin weiXin) {
        super(gitCommand, openAI, weiXin);
    }

    @Override
    protected String getDiffCode() throws IOException, InterruptedException {
        return gitCommand.diff();
    }

    @Override
    protected String codeReview(String diffCode) throws Exception {
        ChatCompletionRequestDto chatCompletionRequest = new ChatCompletionRequestDto();
        chatCompletionRequest.setModel(Model.GLM_4_FLASH.getCode());

        chatCompletionRequest.setMessages(new ArrayList<ChatCompletionRequestDto.Prompt>() {
            private static final long serialVersionUID = -7988151926241837899L;
            {
                add(new ChatCompletionRequestDto.Prompt("user","你是一个高级编程架构师，精通各类场景方案、架构设计和编程语言请，请您根据git diff记录，对代码做出评审。代码为: "));
                add(new ChatCompletionRequestDto.Prompt("user",diffCode));
            }
        });

        ChatCompletionSyncResponseDto completions = openAI.completions(chatCompletionRequest);
        ChatCompletionSyncResponseDto.Message message = completions.getChoices().get(0).getMessage();
        return message.getContent();
    }

    @Override
    protected String recordCodeReview(String recommend) throws Exception {
        return gitCommand.commitAndPush(recommend);
    }

    @Override
    protected void pushMessage(String logUrl) throws Exception {
        Map<String, Map<String, String>> data = new HashMap<>();
        TemplateMessageDto.put(data, TemplateMessageDto.TemplateKey.REPO_NAME, gitCommand.getProject());
        TemplateMessageDto.put(data, TemplateMessageDto.TemplateKey.BRANCH_NAME, gitCommand.getBranch());
        TemplateMessageDto.put(data, TemplateMessageDto.TemplateKey.COMMIT_AUTHOR, gitCommand.getAuthor());
        TemplateMessageDto.put(data, TemplateMessageDto.TemplateKey.COMMIT_MESSAGE, gitCommand.getMessage());
        weiXin.sendTemplateMessage(logUrl,data);

    }
}
