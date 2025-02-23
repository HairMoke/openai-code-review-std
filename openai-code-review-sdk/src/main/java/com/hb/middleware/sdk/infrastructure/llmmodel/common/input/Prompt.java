package com.hb.middleware.sdk.infrastructure.llmmodel.common.input;

/**
 * 提示词类
 */
public class Prompt {

    private final String text;

    public Prompt(String text) {
        this.text = text;
    }

    public String text(){
        return text;
    }

    public static Prompt from(String text){
        return new Prompt(text);
    }

}
