package com.hb.middleware.sdk.infrastructure.git.dto;


import lombok.Data;

@Data
public class SingleCommitResponseDTO {

    private String sha;

    private Commit commit;

    private CommitFile[] files;



    @Data
    public static class Commit{
        private String message;
    }

    @Data
    public static class CommitFile{
        private String filename;
        private String raw_url;
        private String patch;
    }

}
