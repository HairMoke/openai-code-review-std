package com.hb.middleware.sdk.infrastructure.llmmodel.common.input;

import java.util.Map;

public class PromptTemplate {

    // 这是一个静态工厂，用来创建模板渲染器 TemplateRender 对象。工厂模式的使用使得代码更加灵活，可以很方便地通过不同的工厂实现来调整模板的生成逻辑
    private static final PromptTemplateFactory FACTORY = new DefaultPromptTemplateFactory();
    private final String templateString;
    private final TemplateRender templateRender;

    // 构造函数接受一个模板字符串 template，并通过工厂类创建相应的 TemplateRender 实例，用于后续的模板渲染操作。模板字符串在类中作为 templateString 变量保存，渲染器通过工厂提供的 PromptTemplateInput 接口获取模板内容，保证了模板的输入灵活性和可扩展性。
    PromptTemplate(String template){
        this.templateString = template;
        this.templateRender = FACTORY.create(new PromptTemplateInput(){
           @Override
           public String getTemplate() {
               return template;
           }
        });
    }

    // 这个方法返回模板的原始字符串。它可以用于获取模板内容，可能用于调试或展示。
    public String template() {
        return templateString;
    }

    /**
     * 对外提供的方法，渲染变量为一个提示词对象
     * 这是对外的核心方法，接收一个包含变量名和对应值的 Map<String, Object>，然后通过模板渲染器 TemplateRender 渲染模板，将所有变量替换为实际值，最终生成一个 Prompt 对象。Prompt.from() 方法将渲染后的字符串包装为一个提示词对象，这样调用者可以直接获得完整的提示词。
     * @param variavles
     * @return
     */
    public Prompt apply(Map<String, Object> variavles){
        return Prompt.from(templateRender.render(variavles));
    }


    /**
     * 根据模板创建一个提示词模板
     * 这是一个静态工厂方法，方便用户根据给定的模板字符串创建 PromptTemplate 对象。这样可以通过简单的 PromptTemplate.from("模板内容") 调用创建实例，增强了类的可用性和易读性。
     * @param template
     * @return
     */
    public static PromptTemplate from(String template) {
        return new PromptTemplate(template);
    }
}
