package com.hb.middleware.sdk.infrastructure.llmmodel.common.input;

/**
 * 提示词模板输入内容
 */
public interface PromptTemplateInput {

    /**
     * 获得提示词模板字符串
     * @return
     */
    String getTemplate();

    /**
     * 获得提示词模板名称
     * @return
     */
    default String getName(){
        return "template";
    }

}
