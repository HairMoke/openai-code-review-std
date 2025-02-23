package com.hb.middleware.sdk.infrastructure.llmmodel.common.input;

import java.util.Map;

/**
 * 提示词模板渲染实现接口
 */
public interface TemplateRender {

    String render(Map<String, Object> variables);

}
