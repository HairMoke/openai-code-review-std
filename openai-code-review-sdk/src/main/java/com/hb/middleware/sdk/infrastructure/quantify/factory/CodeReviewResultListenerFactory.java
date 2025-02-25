package com.hb.middleware.sdk.infrastructure.quantify.factory;

import com.hb.middleware.sdk.infrastructure.quantify.listener.CodeReviewResultListener;
import com.hb.middleware.sdk.infrastructure.quantify.listener.impl.DefaultWebhookCodeReviewListener;
import com.hb.middleware.sdk.infrastructure.quantify.model.CodeReviewResultContext;

import java.util.ArrayList;
import java.util.List;

/**
 * 监听器的工厂类
 */
public class CodeReviewResultListenerFactory {

    public static List<CodeReviewResultListener> LISTENERS = new ArrayList<>();

    static {
        // 初始化监听器
        LISTENERS.add(new DefaultWebhookCodeReviewListener());
    }

    /**
     * 代码评审完成时触发所有完成时候的监听器。
     * @param context
     */
    public static void triggerListenerOnComplete(CodeReviewResultContext context){
        // 触发监听器
        for (CodeReviewResultListener listener : LISTENERS) {
            listener.onComplete(context);
        }
    }
}
