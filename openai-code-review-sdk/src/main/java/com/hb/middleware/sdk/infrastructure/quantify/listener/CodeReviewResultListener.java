package com.hb.middleware.sdk.infrastructure.quantify.listener;

import com.hb.middleware.sdk.infrastructure.quantify.model.CodeReviewResultContext;

/**
 * 代码评审结果的监听器
 */
public interface CodeReviewResultListener {

    /**
     * 代码评审完成的时候调用
     * @param context
     */
    public void onComplete(CodeReviewResultContext context);

}
