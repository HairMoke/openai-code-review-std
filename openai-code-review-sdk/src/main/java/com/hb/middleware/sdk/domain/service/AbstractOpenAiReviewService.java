package com.hb.middleware.sdk.domain.service;

import com.hb.middleware.sdk.infrastructure.openai.IOpenAI;
import com.hb.middleware.sdk.infrastructure.weixin.WeiXin;
import com.hb.middleware.sdk.infrastructure.git.GitCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public abstract class AbstractOpenAiReviewService implements IOpenAiCodeReviewService{

    private final Logger logger = LoggerFactory.getLogger(AbstractOpenAiReviewService.class);

    protected final GitCommand gitCommand;
    protected final IOpenAI openAI;
    protected final WeiXin weiXin;

    public AbstractOpenAiReviewService(GitCommand gitCommand, IOpenAI openAI, WeiXin weiXin) {
        this.gitCommand = gitCommand;
        this.openAI = openAI;
        this.weiXin = weiXin;
    }

    @Override
    public void exec() {
        try {
            // 1. 获取提交代码
            String diffCode = getDiffCode();
            // 2. 开始评审代码
            String recommend = codeReview(diffCode);
            // 3. 记录评审结果，返回日志地址
            String logUrl = recordCodeReview(recommend);
            // 4. 发送消息通知，日志地址，通知的内容
            pushMessage(logUrl);
        } catch (Exception e) {
            logger.error("openai-code-review error", e);
        }
    }

    protected abstract String getDiffCode() throws IOException, InterruptedException;
    protected abstract String codeReview(String diffCode) throws Exception;
    protected abstract String recordCodeReview(String recommend) throws Exception;
    protected abstract void pushMessage(String logUrl) throws Exception;




}
