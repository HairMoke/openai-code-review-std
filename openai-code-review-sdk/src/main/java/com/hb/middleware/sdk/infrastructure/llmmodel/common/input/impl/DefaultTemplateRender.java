package com.hb.middleware.sdk.infrastructure.llmmodel.common.input.impl;

import com.hb.middleware.sdk.infrastructure.llmmodel.common.input.TemplateRender;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 默认提示词模板处理代码
 */
public class DefaultTemplateRender implements TemplateRender {


    private static final Pattern VARIABLE_PATTERN = Pattern.compile("\\{\\{(.+?)}}");
    private final String template;
    private final Set<String> allVariables;

    /**
     * 类的构造与初始化
     *
     * DefaultTemplateRender(String template) 构造函数
     * 当你创建一个 DefaultTemplateRender 对象时，首先会传入一个模板字符串 template，模板里包含了你要替换的变量（用 {{变量名}} 的形式标记）。在初始化时，程序会提取模板中的所有变量，并将它们存入一个 Set<String> 集合中，方便后续使用。如果模板为空，则会抛出异常，提醒用户提供模板内容。
     * @param template
     */
    public DefaultTemplateRender(String template) {
        if(template == null || template.trim().length() == 0) {
            throw new RuntimeException("提示词模板不能为空");
        }
        this.template = template;
        this.allVariables = extractVariables(template);
    }


    /**
     * 变量提取
     * 这个方法负责从模板字符串中提取所有变量名。使用正则表达式 {{(.+?)}} 匹配出模板中的变量占位符，并将每个变量名放入一个 Set<String> 集合里，确保变量名是唯一的。这种处理方式让你可以轻松获取模板中所有需要替换的变量。
     * @param template
     * @return
     */
    private static Set<String> extractVariables(String template) {
        Set<String> variables = new HashSet<String>();
        Matcher matcher = VARIABLE_PATTERN.matcher(template);
        while (matcher.find()) {
            variables.add(matcher.group(1));
        }
        return variables;
    }


    /**
     * 模板渲染
     * render(Map<String, Object> variables) 方法
     * 当你想渲染模板时，可以调用这个方法，并传入一个包含变量名和对应值的 Map<String, Object>。方法的工作流程如下：
     * 调用 ensureAllVariablesProvided() 校验传入的变量是否覆盖了模板中所有需要替换的变量。如果有未提供的变量，程序会抛出异常，提醒用户补充。
     * 逐个替换模板中的变量占位符为实际值，并返回替换后的最终字符串。
     * @param variables
     * @return
     */
    @Override
    public String render(Map<String, Object> variables) {
        ensureAllVariablesProvided(variables);
        String result = template;
        for(Map.Entry<String, Object> entry : variables.entrySet()){
            result = replaceAll(result, entry.getKey(), entry.getValue());
        }
        return result;
    }

    /**
     * 这个方法遍历模板中所有提取出来的变量，确保用户传入的 Map 中包含每一个变量。如果某个变量缺失，会抛出异常提示。
     * @param providedVariables
     */
    private void ensureAllVariablesProvided(Map<String, Object> providedVariables) {
        for (String variable : allVariables) {
            if(!providedVariables.containsKey(variable)){
                throw new RuntimeException(String.format("Value for the variable '%s' is null", variable));
            }
        }
    }

    /**
     * 负责执行占位符替换逻辑。通过调用 inDoubleCurlyBrackets() 方法，将形如 {{变量名}} 的占位符替换为实际的值。如果变量值为空，则会抛出异常，确保不会出现空值替换的情况。
     * @param template
     * @param variable
     * @param value
     * @return
     */
    private static String replaceAll(String template, String variable, Object value) {
        if(value == null || value.toString() ==null) {
            throw new RuntimeException(String.format("Value for the variable '%s' is null", variable));
        }
        return template.replace(inDoubleCurlyBrackets(variable), value.toString());
    }

    /**
     * 生成形如 {{变量名}} 的占位符字符串，方便 replaceAll() 方法使用。
     * @param variable
     * @return
     */
    private static String inDoubleCurlyBrackets(String variable) {
        return "{{" + variable + "}}";
    }


}
