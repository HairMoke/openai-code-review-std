package com.hb.middleware.sdk.infrastructure.quantify.model;

import lombok.Data;

/**
 * 代码评审结果上下文的类
 */
@Data
public class CodeReviewResultContext {

    /**
     * 分支名称
     */
    private String branchName;


    /**
     * 评审结果
     */
    private String result;


    /**
     * 作者
     */
    private String author;


    /**
     * 评审文件
     */
    private String fileList;


    /**
     * 需求/任务名称
     */
    private String bizName;
}
