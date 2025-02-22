package com.hb.middleware.sdk;


import com.hb.middleware.sdk.domain.service.Impl.OpenAiCodeReviewService;
import com.hb.middleware.sdk.infrastructure.git.BaseGitOperation;
import com.hb.middleware.sdk.infrastructure.git.GitCommand;
import com.hb.middleware.sdk.infrastructure.git.impl.GitRestAPIOperation;
import com.hb.middleware.sdk.infrastructure.openai.IOpenAI;
import com.hb.middleware.sdk.infrastructure.openai.impl.ChatGLM;
import com.hb.middleware.sdk.infrastructure.weixin.WeiXin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 问题未解决：
 * 1. jar报方式运行 报错404 下载不到jar包
 * 2. 发送的微信推送没能进入到正确的.md文件
 */
public class OpenAiCodeReview {

    private static final Logger logger = LoggerFactory.getLogger(OpenAiCodeReview.class);

    // 微信配置
    private String weixin_appid = "wx90813c7b140f7d13";
    private String weixin_secret = "eac1ecb9ccaa5d63342beadd7cf3c32b";
    private String weixin_touser = "ojK1R7Ka2ruj8LK4HKU5hBGeEsuc";
    private String weixin_template_id = "rjRXmvxhYbv0mK9UYG-g998qPbP1HsLeFAQtj_qtM7E";

    // ChatGlM配置
    private String chatglm_apiHost = "https://open.bigmodel.cn/api/paas/v4/chat/completions";
    private String chatglm_apiKeySecret = "";


    // Github配置
    private String github_review_log_uri;
    private String github_token;

    // 工程配置 - 自动获取
    private String github_project;
    private String github_branch;
    private String github_author;


    public static void main(String[] args) throws Exception {
        GitCommand gitCommand = new GitCommand(
                getEnv("GITHUB_REVIEW_LOG_URI"),
                getEnv("GITHUB_TOKEN"),
                getEnv("COMMIT_PROJECT"),
                getEnv("COMMIT_BRANCH"),
                getEnv("COMMIT_AUTHOR"),
                getEnv("COMMIT_AUTHOR")
        );

        WeiXin weiXin = new WeiXin(
                getEnv("WEIXIN_APPID"),
                getEnv("WEIXIN_SECRET"),
                getEnv("WEIXIN_TOUSER"),
                getEnv("WEIXIN_TEMPLATE_ID")
        );

        IOpenAI openai = new ChatGLM(
                getEnv("CHATGLM_APIHOST"),
                getEnv("CHATGLM_APIKEYSECRET")
        );

        BaseGitOperation baseGitOperation = new GitRestAPIOperation(
                getEnv("GIT_CHECK_COMMIT_URL"),
                getEnv("GITHUB_TOKEN")
        );


        // 创建OpenAiCodeReviewService实例， 将所有组件组合在一起
        OpenAiCodeReviewService openAiCodeReviewService = new OpenAiCodeReviewService(baseGitOperation, gitCommand, openai, weiXin);

        // 执行代码评审流程
        openAiCodeReviewService.exec();


/**
        OpenAiCodeReviewService openAiCodeReviewService = new OpenAiCodeReviewService(gitCommand, openai, weiXin);
        openAiCodeReviewService.exec();
 */


        logger.info("openai-code-review done! ");

    }

    private static String getEnv(String key) {
        String value = System.getenv(key);
        if(null == value || value.isEmpty()) {
            throw new RuntimeException("get env is null");
        }
        return value;
    }

}