package com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.response;

public class TweetCommentResponse {
    private Long id;
    private String comment;
    private String userAuthor;

    public TweetCommentResponse() {}

    public TweetCommentResponse(Long id, String comment, String userAuthor){
        this.id = id;
        this.comment = comment;
        this.userAuthor = userAuthor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserAuthor() {
        return userAuthor;
    }

    public void setUserAuthor(String userAuthor) {
        this.userAuthor = userAuthor;
    }
}
