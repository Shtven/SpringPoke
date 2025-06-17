package com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.request;

public class TweetCommentRequest {
    private String comment;
    private Long tweetId;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getTweetId() {
        return tweetId;
    }

    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }
}
