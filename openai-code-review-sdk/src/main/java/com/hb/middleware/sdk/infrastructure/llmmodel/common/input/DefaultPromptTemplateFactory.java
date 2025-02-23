package com.hb.middleware.sdk.infrastructure.llmmodel.common.input;

import com.hb.middleware.sdk.infrastructure.llmmodel.common.input.impl.DefaultTemplateRender;

/**
 * 默认提示词模板工厂类
 */
public class DefaultPromptTemplateFactory implements PromptTemplateFactory{


    @Override
    public TemplateRender create(PromptTemplateInput input) {
        return new DefaultTemplateRender(input.getTemplate());
    }
}
