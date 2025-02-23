package com.hb.middleware.sdk.infrastructure.llmmodel.zhipu;

public enum ZhipuChatCompletionModelEnum {

    GLM_4("glm-4"),
    GLM_4V("glm-4v"),
    GLM_4_0520("glm-4-0520"),
    GLM_4_AIR("glm-4-air"),
    GLM_4_AIRX("glm-4-airx"),
    GLM_4_FLASH("glm-4-flash"),
    GLM_3_TURBO("glm-3-turbo"),
    CHATGLM_TURBO("chatglm-turbo");

    private final String value;

    ZhipuChatCompletionModelEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return this.value;
    }

}
