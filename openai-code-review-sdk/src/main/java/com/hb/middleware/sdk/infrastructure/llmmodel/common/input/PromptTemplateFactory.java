package com.hb.middleware.sdk.infrastructure.llmmodel.common.input;

/**
 * 提示词模板工厂接口
 */
public interface PromptTemplateFactory {

    /**
     * 创建一个提示词模板
     * @param input
     * @return
     */
    TemplateRender create(PromptTemplateInput input);

}
