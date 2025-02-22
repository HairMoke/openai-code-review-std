package com.hb.middleware.sdk.infrastructure.git.impl;

import com.alibaba.fastjson2.JSON;
import com.hb.middleware.sdk.infrastructure.git.BaseGitOperation;
import com.hb.middleware.sdk.infrastructure.git.dto.SingleCommitResponseDTO;
import com.hb.middleware.sdk.types.utils.DefaultHttpUtil;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.HashMap;
import java.util.Map;


public class GitRestAPIOperation implements BaseGitOperation {

    private final Logger logger = LoggerFactory.getLogger(GitRestAPIOperation.class);

    private final String githubRepoUrl;

    private final String githubToekn;

    public GitRestAPIOperation(String githubRepoUrl, String githubToekn) {
        this.githubRepoUrl = githubRepoUrl;
        this.githubToekn = githubToekn;
    }

    @Override
    public String diff() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("X-GitHub-Api-Version","2022-11-28");
        params.put("Authorization","Bearer " + githubToekn);
        params.put("Accept","application/vnd.github+json");
        String result = DefaultHttpUtil.executeGetRequest(this.githubRepoUrl, params);
        SingleCommitResponseDTO singleCommitResponseDTO = JSON.parseObject(result, SingleCommitResponseDTO.class);
        SingleCommitResponseDTO.CommitFile[] files = singleCommitResponseDTO.getFiles();
        StringBuilder sb = new StringBuilder();
        for (SingleCommitResponseDTO.CommitFile file : files) {
            sb.append("待评审文件名称：").append(file.getFilename()).append("\n");
            sb.append("该文件变更代码：").append(file.getPatch()).append("\n");
        }
        return sb.toString();
    }
}
