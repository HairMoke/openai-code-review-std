package com.hb.middleware.sdk;

import com.google.errorprone.annotations.Var;
import com.hb.middleware.sdk.infrastructure.llmmodel.common.input.impl.DefaultTemplateRender;
import org.junit.Test;

import java.util.HashMap;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class DefaultTemplateRenderTest {

    @Test
    public void testSingleVariReplace() {
        String template = "你是一个{{language}}工程师";
        DefaultTemplateRender render = new DefaultTemplateRender(template);
        HashMap<String, Object> map = new HashMap<>();
        map.put("language", "Java");
        String prompt = render.render(map);
        System.out.println(prompt);
        assertEquals("你是一个Java工程师", prompt);
    }
}
