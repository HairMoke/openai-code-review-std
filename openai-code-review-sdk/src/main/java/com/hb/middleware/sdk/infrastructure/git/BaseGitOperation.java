package com.hb.middleware.sdk.infrastructure.git;

public interface BaseGitOperation {


    /**
     * 定义一个获取变更内容的方法
     * @return
     * @throws Exception
     */
    public String diff() throws Exception;
}
